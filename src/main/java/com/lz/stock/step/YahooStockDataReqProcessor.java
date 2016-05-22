package com.lz.stock.step;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ldh on 2016/5/21.
 */
public class YahooStockDataReqProcessor implements ItemProcessor<StockCodeBase,String>{
    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private RestTemplate restTemplate;
    private String startDate;
    private String endDate;

    @Override
    public String process(StockCodeBase stockCodeBase) throws Exception {

        String[] codeArr=stockCodeBase.getStockCode().split(",");
        StringBuffer multiLine = new StringBuffer();
        for(String code:codeArr) {
            code=code.substring(2,code.length())+"."+code.substring(0,2);
            StringBuffer res = null;
            try {
                res = getStringBuffer(code, startDate, endDate);
                multiLine.append(code + "," + replaceBlank(res.substring(res.indexOf("\n")))+ LzjfConstants.LINE_SEPARATOR);
            } catch (Exception e) {
                multiLine.append(code+LzjfConstants.LINE_SEPARATOR);
            }
        }
        return multiLine.toString();
    }


    private  Integer[] getDateNums(String dateStr) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return new Integer[]{cal.get(Calendar.MONTH ),cal.get(Calendar.DATE),cal.get(Calendar.YEAR)};
        } catch (ParseException e) {
            return null;
        }
    }

    private StringBuffer getStringBuffer(String code,String startDate,String endDate) {
        String url="http://table.finance.yahoo.com/table.csv?s="+code+"&g=d&ignore=.csv";
        Integer[] startDateNums = getDateNums(startDate);
        Integer[] endDateNums = getDateNums(endDate);
        if(startDateNums!=null&&endDateNums!=null)
        url+="&d="+endDateNums[0]+"&e="+endDateNums[1]+"&f="+endDateNums[2]+"&a="+startDateNums[0]+"&b="+startDateNums[1]+"&c="+startDateNums[2];
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };

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

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
