package com.lz.stock.step;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lz.stock.model.StockCodeBase;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
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
public class BkStockDataReqProcessor implements ItemProcessor<Date,String> {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String process(Date date) throws Exception {
        String url = "http://vip.stock.finance.sina.com.cn/q/view/newSinaHy.php";
        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=GBK");
//        headers.setContentType(type);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {};
        List<HttpMessageConverter<?>> list=restTemplate.getMessageConverters();
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(Charset.forName("GBK"));
        list.removeAll(list);
        list.add(messageConverter);
        restTemplate.setMessageConverters(list);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, typeRef);
        String myResponse = responseEntity.getBody();
        String json=myResponse.replace("var S_Finance_bankuai_sinaindustry = ","");
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {});
        StringBuffer sbuffer = new StringBuffer();
        for(String key:map.keySet()){
            System.out.println(map.get(key));
            sbuffer.append(map.get(key)+System.getProperty("line.separator"));
        }
        return sbuffer.toString();
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