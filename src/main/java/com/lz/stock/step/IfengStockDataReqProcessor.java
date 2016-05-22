package com.lz.stock.step;

import com.lz.constant.LzjfConstants;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/20.
 */
public class IfengStockDataReqProcessor implements ItemProcessor<String, String> {
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private RestTemplate restTemplate;

    private ExecutionContext executionContext;
    @BeforeStep
    public void beforStep(StepExecution stepExecution){
        this.executionContext = stepExecution.getExecutionContext();
    }
    @Override
    public String process(String category) throws Exception {
        String url = "http://app.finance.ifeng.com/list/stock.php?f=chg_pct&o=desc&t=";
        int n = 0;
        StringBuffer sbuff = new StringBuffer();
        List errorUrls=new ArrayList();
        while (true) {
            String requrl = url + category + "&p=" + (++n);
            logger.info("url:" + requrl);
            StringBuffer sbufferRes = null;
            try {
                sbufferRes = getStringBuffer(requrl);
            } catch (Exception e) {
                logger.error("request url:"+requrl);
                errorUrls.add(requrl.replace(url,""));
                continue;
            }
            Document doc = Jsoup.parse(sbufferRes.toString());
            Element tab01 = doc.select("div.tab01").first();
            Element tbody = tab01.getElementsByTag("tbody").first();
            Elements trs = tbody.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                if (tds.size() < 10)
                    continue;
                StringBuffer linebuffer = new StringBuffer(category);
                for (Element td : tds) {
                    linebuffer.append(","+td.text());
                }
                linebuffer.append(LzjfConstants.LINE_SEPARATOR);
                sbuff.append(linebuffer);
            }
            Element last = tbody.getElementsByTag("a").last();
            if (!last.text().equals("下一页") || last.text() == null) {
                break;
            }
        }
        executionContext.put("errorUrls",errorUrls);
        return sbuff.toString();
    }

    private StringBuffer getStringBuffer(String url) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, typeRef);
        String myResponse = responseEntity.getBody();
        StringBuffer sbuffer = new StringBuffer(myResponse);
        return sbuffer;
    }

    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}