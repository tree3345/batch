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
    public void stockCodeInitJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("pageSize_", "100");
        String jobid = "stockCodeInitJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("stock初始化运行时间： " + (endTime - startTime) + "ms");
    }

    @Test
    public void stockCodeSaveJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<>();
        hm.put("pageSize_", "6");
        hm.put("stock.file","F:\\stock\\sina.csv");
        String jobid = "stockSaveJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("stock保存运行时间：" + (endTime - startTime) + "ms");
    }

    @Test
    public void bkStockCodeSaveJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<>();
        hm.put("pageSize_", "6");
        hm.put("stock.file", "F:\\stock\\bkstock.csv");
        String jobid = "bkStockSaveJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("bkstock保存运行时间： " + (endTime - startTime) + "ms");
    }

    @Test
    public void ifengStockCodeSaveJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<>();
        hm.put("stock.file", "F:\\stock\\ifeng.csv");
        String jobid = "ifengStockSaveJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("bkstock保存运行时间： " + (endTime - startTime) + "ms");
    }
    @Test
    public void yahooStockCodeSaveJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<>();
        hm.put("startDate", "2016-05-20");
        hm.put("endDate", "2016-05-20");
        hm.put("stock.file", "F:\\stock\\yahoo.csv");
        String jobid = "yahooStockSaveJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("bkstock保存运行时间： " + (endTime - startTime) + "ms");
    }
    @Test
    public void eastMoneyStockCodeSaveJob() {
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<>();
        hm.put("reqStyle","33");
        hm.put("reqPageSize","3000");
        hm.put("stock.file", "F:\\stock\\esstMoney.csv");
        String jobid = "eastMoneyStockSaveJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("bkstock保存运行时间： " + (endTime - startTime) + "ms");
    }

    @Test
    public void eastMoneyStockTopJob() {
        /**
         * TradeDetail 龙虎榜    0：全部 1：沪市 2：深市
         * StockStatistic 个股上榜统计
         * DailyStockListStatistics 机构买卖每日统计 0：全部 1：沪市 2：深市
         * JgStatistic 机构席位追踪
         * ActiveStatistics 每日活跃营业部
         * BusinessRanking 营业部排行
         * TraderStatistic 营业部统计
         */
        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<>();
        hm.put("reqPageSize","3000");
        hm.put("topType","TradeDetail");
        hm.put("startDate","2016-05-20");
        hm.put("endDate","2016-05-20");
        hm.put("stock.file", "F:\\stock\\TradeDetail.csv");
        String jobid = "eastMoneyStockTopJob";
        super.runJob(jobid, hm);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("bkstock保存运行时间： " + (endTime - startTime) + "ms");
    }
}
