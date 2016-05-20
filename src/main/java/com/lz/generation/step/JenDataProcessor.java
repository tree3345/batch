package com.lz.generation.step;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/24.
 */
public class JenDataProcessor implements ItemProcessor<Map<String, String>, String> {

    private Logger logger = Logger.getLogger("biz");

    private String codes;

    private String dataBeginDate;

    private String factorCode;

    private String preRowdata;


    @Override
    public String process(Map<String, String> map) throws Exception {
        Date dbDate = new SimpleDateFormat("yyyyMMdd").parse(map.get("date").toString());
        Date beginDate = new SimpleDateFormat("yyyy-MM-dd").parse(dataBeginDate);
        StringBuffer sb = new StringBuffer();
        if (dbDate.compareTo(beginDate) == -1) {
            return factorCode + "," + codes;
        } else {
            sb.append(map.get("date"));
            String[] codeArr = codes.split(",");

            //向下填充
            downFill(map, sb, codeArr);
            //不填充
            noneFill(map,sb,codeArr);
            //有code，但value为空向下填充，否则NaN
            optionalDownFill(map,sb,codeArr);
            return sb.toString();

        }
    }

    //不填充
    private void noneFill(Map<String, String> map, StringBuffer sb, String[] codeArr) {

    }
    //有选择的向下填充，即有code，但value为空向下填充，否则NaN
    private void optionalDownFill(Map<String, String> map, StringBuffer sb, String[] codeArr) {

    }

    private void downFill(Map<String, String> map, StringBuffer sb, String[] codeArr) {
        if (map.get("code") != null) {
            HashMap<String, String> codevalueMap = getCodeValueHashMap(map);
            Map<String, String> precodeValueMap = getPreCodeValueMap(codeArr);
            for (String code : codeArr) {
                sb.append(",");
                sb.append(codevalueMap.get(code) != null ? codevalueMap.get(code) : (precodeValueMap.get(code) != null ? precodeValueMap.get(code) : "NaN"));
            }
        } else {
            if (preRowdata.equals("") || preRowdata == null) {
                for (String s : codeArr) {
                    sb.append("," + "NaN");
                }
            } else {
                sb.append("," + preRowdata);
            }
        }
        preRowdata = sb.toString().substring(sb.indexOf(",") + 1);
    }

    private Map<String, String> getPreCodeValueMap(String[] codeArr) {
        Map<String, String> precodeValueMap = new HashMap<>();
        String[] rowdata = preRowdata.split(",");
        if (preRowdata!=null&&!preRowdata.equals("")) {
            for (int i = 0; i < codeArr.length; i++) {
                precodeValueMap.put(codeArr[i], rowdata[i]);
            }
        }
        return precodeValueMap;
    }

    private HashMap<String, String> getCodeValueHashMap(Map<String, String> map) {
        String[] mapcodes = map.get("code").split(",");
        String[] mapvalues = map.get("value").split(",");
        if (mapvalues.length != mapcodes.length) {
            logger.error(factorCode + "**********数据异常********mapvalues:" + mapvalues.length + "****mapcodes:" + mapcodes.length);
        }
        HashMap<String, String> codevalueMap = new HashMap<>();
        for (int i = 0; i < mapcodes.length; i++) {
                codevalueMap.put(mapcodes[i], mapvalues[i]);
        }
        return codevalueMap;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getCodes() {
        return codes;
    }

    public void setDataBeginDate(String dataBeginDate) {
        this.dataBeginDate = dataBeginDate;
    }

    public String getDataBeginDate() {
        return dataBeginDate;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setPreRowdata(String preRowdata) {
        this.preRowdata = preRowdata;
    }

    public String getPreRowdata() {
        return preRowdata;
    }
}
