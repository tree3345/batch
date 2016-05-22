package com.lz.stock.step;

import com.alibaba.fastjson.JSON;
import com.lz.constant.LzjfConstants;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ldh on 2016/5/21.
 */
public class EastMoneyStockTopDataReqProcessor implements ItemProcessor<Date, String> {
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private RestTemplate restTemplate;
    private String topType;
    private String reqPageSize;
    private String startDate;
    private String endDate;

    @Override
    public String process(Date date) throws Exception {

        StringBuffer multiLine = new StringBuffer();
        StringBuffer res = null;
        try {
            res = getStringBuffer(1);
            String jsonStr = res.toString().replaceAll("var data_tab=", "");
            Map<String, Object> mapData = JSON.parseObject(jsonStr);
            List<Map<String, String>> list = (List) mapData.get("data");
            for (Map<String, String> item : list) {
                StringBuffer line = new StringBuffer();
                for (String key : item.keySet()) {
                    line.append("," + item.get(key));
                }
                multiLine.append(line.substring(1) + LzjfConstants.LINE_SEPARATOR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            multiLine.append(33 + LzjfConstants.LINE_SEPARATOR);
        }
        return multiLine.toString();
    }

    private StringBuffer getStringBuffer(Integer page) {
        String url = "http://data.eastmoney.com/DataCenter_V3/stock2016/" + topType + "/";
        String params = "pagesize=" + reqPageSize + ",page=" + page + ",sortRule=-1,sortType=PBuy,startDate=" + startDate + ",endDate=" + endDate + ",gpfw=0,js=.html";

        // TODO: 2016/5/22 DailyStockListStatistics/pagesize=50,page=1,sortRule=-1,sortType=PBuy,startDate=2016-05-20,endDate=2016-05-20,gpfw=0,js=var%20data_tab.html
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange(url + params, HttpMethod.GET, entity, typeRef);
        String myResponse = responseEntity.getBody();
        StringBuffer sbuffer = new StringBuffer(myResponse);
        System.out.println(url + params);
        return sbuffer;
    }

    public String getTopType() {
        return topType;
    }

    public void setTopType(String topType) {
        this.topType = topType;
    }

    public String getReqPageSize() {
        return reqPageSize;
    }

    public void setReqPageSize(String reqPageSize) {
        this.reqPageSize = reqPageSize;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
