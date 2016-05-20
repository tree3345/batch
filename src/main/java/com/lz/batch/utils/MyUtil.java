package com.lz.batch.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;


/**
 * 常用工具类
 * 
 * @author mao_siyu
 */
public class MyUtil {

    /** 一万元. */
    public static final double DIVISOR = 10000;

    /** 小时. */
    public static final int HOU = 23;

    /** 分钟. */
    public static final int MIN = 59;

    /** 秒. */
    public static final int SEC = 59;

    /** 毫秒. */
    public static final int MILSEC = 0;

    private MyUtil() {

    }

    /**
     * null 转为 "" 空串
     * 
     * @param strAttr
     * @return String
     */
    public static String convertNulltoBlank(String strAttr) {

        strAttr = (StringUtils.isEmpty(strAttr)) ? "" : strAttr;

        return strAttr;
    }

    /**
     * 计算 金额万元 页面对象属性转换为数据 乘法计算。
     * 
     * @param amount
     * @return
     */
    public static double convertCalculation(String amount) {

        if (StringUtils.isEmpty(amount)) {
            return 0D;
        }

        return Double.valueOf(amount.replaceAll(",", "")) * DIVISOR;
    }

    /**
     * 计算 金额万元 数据转换为页面对象属性 除法计算。
     * 
     * @param amount
     * @return
     */
    public static String convertCalculation(Double amount) {

        if (null == amount) {
            return "";
        }
        return NumberUtils.format(amount / DIVISOR, "###,###,###.##");
        // 因为 easyUI的 冲突问题 暂时不对datagrid的显示内容进行格式化。
        // return NumberUtils.format(amount / DIVISOR, ConstantsP2P.AMOUNT);
    }

    /**
     * 计算 金额万元 数据转换为页面对象属性 除法计算 六位小数。(丰年标的编辑使用)
     * 
     * @param amount
     * @return
     */
    public static String fnConvertCalculation(Double amount) {

        if (null == amount) {
            return "";
        }
        return NumberUtils.format(amount / DIVISOR, "###,###,###.######");
    }

    /**
     * 为日期 追加 23.59.59
     * 
     * @param inputDate
     * @return
     */
    public static Date convertCalendar(Date inputDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        if (null != inputDate) {
            calendar.set(Calendar.HOUR_OF_DAY, HOU);
            calendar.set(Calendar.MINUTE, MIN);
            calendar.set(Calendar.SECOND, SEC);
            calendar.set(Calendar.MILLISECOND, MILSEC);
        }
        return calendar.getTime();
    }

    /**
     * MD5 加密.
     * 
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {

        String rtnStr = "";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] byteArray = messageDigest.digest();
            StringBuffer md5StrBuff = new StringBuffer();

            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }

            rtnStr = md5StrBuff.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return rtnStr;
    }

    /**
     * 中文转换为&#x开头的UTF-8编码的形式
     * 
     * @param inStr
     * @return
     */
    public static String convertToUtf8(String inStr) {

        char[] myBuffer = inStr.toCharArray();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
            UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
            if (ub == UnicodeBlock.BASIC_LATIN) {
                // 英文及数字等
                sb.append(myBuffer[i]);

            } else {
                // 全角
                int s = (int) myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "&#x" + hexS.toUpperCase() + ";";
                sb.append(unicode);
            }
        }

        return sb.toString();
    }

    /**
     * 取得签名.
     * 
     * @param appkey
     * @param account
     * @param mdlName
     * @return
     */
    public static String getSignature(Map<String, String> maps) {

        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append(maps.get("prefixAppsecret"));
        sbuffer.append("appkey");
        sbuffer.append(maps.get("appkey"));
        sbuffer.append("modelName");
        sbuffer.append(maps.get("modelName"));
        sbuffer.append("timestamp");
        sbuffer.append(maps.get("dateStr"));
        sbuffer.append("userAccount");
        sbuffer.append(maps.get("account"));
        sbuffer.append(maps.get("suffixAppsecret"));
        String sign = getMD5Str(sbuffer.toString());

        return sign;
    }

    /**
     * 拼接URL.
     * 
     * @return
     */
    public static String spliceURL(String account, String modelName) {

        Properties props = getProperties("conf/singleSignOnConfig.properties");
        long date = new Date().getTime();
        String dateStr = String.valueOf(date);
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("appkey", props.getProperty("sig.appkey"));
        maps.put("prefixAppsecret", props.getProperty("sig.prefixAppsecret"));
        maps.put("suffixAppsecret", props.getProperty("sig.suffixAppsecret"));
        maps.put("appurl", props.getProperty("sig.appurl"));
        maps.put("dateStr", dateStr);
        maps.put("account", account);
        maps.put("modelName", modelName);

        String sign = getSignature(maps);

        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append(maps.get("appurl"));
        urlBuffer.append(sign);
        urlBuffer.append("&userAccount=");
        urlBuffer.append(account);
        urlBuffer.append("&modelName=");
        urlBuffer.append(modelName);
        urlBuffer.append("&timestamp=");
        urlBuffer.append(dateStr);

        String successUrl = convertToUtf8(urlBuffer.toString());

        return successUrl;
    }

    /**
     * getProperties
     */
    public static Properties getProperties(String propertie) {

        Properties props = new Properties();
        InputStream inStream;
        String path = MyUtil.class.getClassLoader().getResource(propertie).getPath();

        try {

            path = URLDecoder.decode(path, "UTF-8");
            inStream = new BufferedInputStream(new FileInputStream(path));
            props.load(inStream);

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }

        return props;
    }
    
    public static String validateUrl(String signStr,String timestamp){
        
        long date = new Date().getTime();
        String signStrNew = "";
        if(null!=timestamp && null!=signStr){
            //String dateStr = signStr.substring(signStr.indexOf(signStr));
            long dateStub = date-Long.parseLong(timestamp)-5*60*1000;
            if(dateStub>0){
                return "false&"+"访问过时！";
            }else{
                signStrNew = MyBatchUtil.spliceURL(timestamp);
                if(null!=signStrNew&&signStrNew.equals(signStr)){
                }else{
                    return "false&"+"url不正确";
                }
            }
            
        }else{
            return "false&"+"url不正确";
        }
        return "true&"+"";
    }
}
