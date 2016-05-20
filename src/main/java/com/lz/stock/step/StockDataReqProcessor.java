package com.lz.stock.step;

import com.lz.stock.model.StockCodeBase;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/20.
 */
public class StockDataReqProcessor implements ItemProcessor<StockCodeBase,String> {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String process(StockCodeBase stockCodeBase) throws Exception {
        String url="http://hq.sinajs.cn/rn=j5usz&list="+stockCodeBase.getStockCode();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {};
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, typeRef);
        String myResponse = responseEntity.getBody();
        String multiLine = myResponse.replaceAll("var hq_str_", "").replaceAll("=", ",").replaceAll("\"", "");
        multiLine=replaceBlank(multiLine);
        multiLine = multiLine.replaceAll(";", System.getProperty("line.separator"));
        return multiLine;
    }
    public  String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
