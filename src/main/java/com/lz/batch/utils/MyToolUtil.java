package com.lz.batch.utils;

public class MyToolUtil {

    public static String transf(String str){

        String[] strs=str.toLowerCase().split("_");
        str ="";
        for(String s:strs)
            str+=s.replaceFirst(s.substring(0, 1), s.substring(0, 1).toUpperCase());
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toLowerCase());
    }
    public static String nextBatch(String batch){
        Integer x = Integer.parseInt(batch.replace("csv", "")) + 1;
        String x1 = "csv";
        for(int i=0;i<3-x.toString().length();i++)
            x1+="0";
        return x1+x;
    }

    public static void main(String[] args) {

        System.out.println(nextBatch("csv001"));
    }
}
