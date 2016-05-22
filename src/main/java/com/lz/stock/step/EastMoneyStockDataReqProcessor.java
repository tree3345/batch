package com.lz.stock.step;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lz.constant.LzjfConstants;
import com.lz.stock.model.StockCodeBase;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ldh on 2016/5/21.
 */
public class EastMoneyStockDataReqProcessor implements ItemProcessor<Date,String>{
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private RestTemplate restTemplate;
    private String reqStyle;
    private String reqPageSize;

    @Override
    public String process(Date date) throws Exception {

        StringBuffer multiLine = new StringBuffer();
            StringBuffer res = null;
            try {
                res = getStringBuffer(reqStyle, reqPageSize, 1);
                String jsonStr = res.toString().replaceAll("var js=", "");
                Map<String,Object> map = JSON.parseObject(jsonStr);
                List<String> list = (List) map.get("rank");
                for(String str:list){
                    multiLine.append(str+LzjfConstants.LINE_SEPARATOR);
                }
            } catch (Exception e) {
                multiLine.append(33+LzjfConstants.LINE_SEPARATOR);
            }
        return multiLine.toString();
    }

    private StringBuffer getStringBuffer(String style,String pageSize,int pageNo) {
        String url="http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/index.aspx?type=s&sortType=C&sortRule=-1&pageSize="+pageSize+"&page="+pageNo+"&style="+style;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {};
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, typeRef);
        String myResponse = responseEntity.getBody();
        StringBuffer sbuffer = new StringBuffer(myResponse);
        return sbuffer;
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

    public void setReqStyle(String reqStyle) {
        this.reqStyle = reqStyle;
    }

    public String getReqStyle() {
        return reqStyle;
    }

    public void setReqPageSize(String reqPageSize) {
        this.reqPageSize = reqPageSize;
    }

    public String getReqPageSize() {
        return reqPageSize;
    }

}
