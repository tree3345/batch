package com.lz.generation.step;


import org.springframework.batch.core.ItemProcessListener;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ShareDataListener implements ItemProcessListener<Map<String,String>,String> {


   /* @Override
    public void beforeProcess(String s) {
        System.out.println("beforeProcess():"+s);
    }

    @Override
    public void afterProcess(String s, String s2) {
        System.out.println("afterProcess()1:"+s);
        System.out.println("afterProcess()2:"+s2);
    }

    @Override
    public void onProcessError(String s, Exception e) {

    }*/

    @Override
    public void beforeProcess(Map<String, String> stringStringMap) {
        System.out.println("beforeProcess():"+stringStringMap);
    }

    @Override
    public void afterProcess(Map<String, String> stringStringMap, String s) {
        System.out.println("afterProcess()1:"+stringStringMap);
        System.out.println("afterProcess()2:"+s);
    }

    @Override
    public void onProcessError(Map<String, String> stringStringMap, Exception e) {

    }
}
