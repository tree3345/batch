package com.lzjf.util;

import java.math.BigDecimal;

/**
 * Created by stonecoldhu on 2015/7/21.
 */
public class BigDecimalUtils {

    /**
     * 元转化为分
     * @param yuan
     * @return
     */
    public static long yuanToFen(BigDecimal yuan){
        if (yuan==null){
            return 0;
        }
        return yuan.multiply(new BigDecimal("100")).longValue();
    }

    /**
     * 分变为元
     * @param fen
     * @return
     */
    public static BigDecimal fenToYuan(String fen){
        // 不是数字
        if(!fen.matches("\\d+")){
            return null;
        }
        BigDecimal bigFen = new BigDecimal(fen);
        bigFen = bigFen.divide(new BigDecimal("100"));
        return  bigFen;
    }
    
    public static void main(String[] args) {

        System.out.println(fenToYuan("222222"));
    }
}
