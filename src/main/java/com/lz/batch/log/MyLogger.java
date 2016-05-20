package com.lz.batch.log;

import org.apache.log4j.MDC;

public class MyLogger {

    private static MyLogger myLogger;

    public static MyLogger put(String key, String value) {
        MDC.put(key, value);
        return myLogger;
    }
}
