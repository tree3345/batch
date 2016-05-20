package com.lz.job;

import org.apache.log4j.Logger;
import org.junit.Test;
import test.batch.springbatchjob.BatchJobTest;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/20.
 */
public class StockTest extends BatchJobTest {
    Logger logger = Logger.getLogger(this.getClass());

    @Test
    public void testStockCodeInitJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("pageSize_", "100");
        String jobid = "stockCodeInitJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("stock初始化运行时间： " + (endTime - startTime) + "ms");
    }

    @Test
    public void testStockCodeSaveJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<>();
        hm.put("pageSize_", "6");
        hm.put("stock.file","E:\\stock.csv");
        String jobid = "stockSaveJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("stock保存运行时间：" + (endTime - startTime) + "ms");
    }

    @Test
    public void testBkStockCodeSaveJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<>();
        hm.put("pageSize_", "6");
        hm.put("stock.file", "E:\\bkstock.csv");
        String jobid = "bkStockSaveJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("bkstock保存运行时间： " + (endTime - startTime) + "ms");
    }

    @Test
    public void testifengStockCodeSaveJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<>();
        hm.put("stock.file", "E:\\ifengstock.csv");
        String jobid = "ifengStockSaveJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("bkstock保存运行时间： " + (endTime - startTime) + "ms");
    }

    @Override
    public void runJob() {

    }
    /**
     * QRTZ_BLOB_TRIGGERS
     * QRTZ_CALENDARS
     * QRTZ_CRON_TRIGGERS
     * QRTZ_FIRED_TRIGGERS
     * QRTZ_JOB_DETAILS
     * QRTZ_LOCKS
     * QRTZ_PAUSED_TRIGGER_GRPS
     * QRTZ_SCHEDULER_STATE
     * QRTZ_SIMPLE_TRIGGERS
     * QRTZ_SIMPROP_TRIGGERS
     * QRTZ_TRIGGERS
     */
}
