package com.lz.stock.step;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/20.
 */
public class IfengStockDataReqProcessor implements ItemProcessor<Date,String> {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String process(Date date) throws Exception {
        String url = "http://app.finance.ifeng.com/list/stock.php?f=chg_pct&o=desc&t=";
//        String categories="ha,sa,hb,sb,zxb,cyb,zs,jj,etf,zq";
        String categories="ha";
        for(String flag:categories.split(",")){
            int i=0;
            while(true){
                System.out.println("url:"+url+flag+"&p="+(++i));

                StringBuffer sbuffer = getStringBuffer(url);
                Document doc = Jsoup.parse(sbuffer.toString());
                Element masthead = doc.select("div.tab01").first();
                Element last = masthead.select("a").last();

                System.out.println(last.getAllElements().toString());
                if(i==1)break;
            }
        }

        return null;
    }

    private StringBuffer getStringBuffer(String url) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {};

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