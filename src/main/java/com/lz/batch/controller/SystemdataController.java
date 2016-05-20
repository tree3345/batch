package com.lz.batch.controller;

import com.lz.batch.controller.utils.DateTools;
import com.lz.batch.job.springbatchjob.SpringBatchJob;
import com.lz.batch.log.MyLogger;
import com.lz.batch.request.BatchRequest;
import com.lz.batch.response.BatchResponse;
import com.lz.batch.service.BatchJobService;
import com.lz.batch.service.impl.QuartzJobAddServiceImpl;
import com.lz.batch.utils.BatchConstants;
import com.lz.batch.utils.SpringBeanUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
@Scope("prototype")
@RequestMapping("statistics")
public class SystemdataController {

    Logger log = Logger.getLogger("biz");

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("do")
    @ResponseBody
    public BatchResponse execute(@RequestBody BatchRequest batchRequest) {

        HashMap<String, String> requestHm = batchRequest.getRequestHm();

        BatchResponse response = new BatchResponse();
        String ip = request.getRemoteAddr();

        MyLogger.put("sys1", ip);
        log.info("调用batch开始");

        String method = requestHm.get(BatchConstants.SYS_PARAM_METHOD);

        // 找到调用的方法
        if (method != null) {
            BatchJobService batchJobService = (BatchJobService) SpringBeanUtils.getBean(method);

            response = batchJobService.doJob(requestHm);
        }

        log.info("调用batch结束");
        // 清除log参数
        MDC.clear();
        return response;
    }

    @RequestMapping("add")
    @ResponseBody
    public BatchResponse add(@RequestBody BatchRequest batchRequest) {

        // 参数设置
        HashMap<String, String> requestHm = batchRequest.getRequestHm();
        // service声明
        QuartzJobAddServiceImpl quartzJobAddServiceImpl = new QuartzJobAddServiceImpl();

        log.info("追加$$$--qzJob--$$$开始！");
        BatchResponse response = quartzJobAddServiceImpl.doJob(requestHm);
        log.info("调用$$$--qzJob--$$$结束！");
        return response;
    }

    @RequestMapping("batchRun")
    @ResponseBody
    public BatchResponse batchRun(String startDate, String endDate) {

        HashMap<String, String> hm = new HashMap<String, String>();

        Date start = DateTools.str2date(startDate, "yyyy-MM-dd");
        Date end = DateTools.str2date(endDate, "yyyy-MM-dd");

        hm.put("currentDate", startDate);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean("user_batch");
        job.setBatchResponse(new BatchResponse());
        do {
            try {
                job.runBatchJob(true, hm);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 天数加1
            start = DateTools.addDate(start);
            // 参数修正
            hm.put("currentDate", DateTools.date2Str(start, "yyyy-MM-dd"));

        } while (DateTools.compareTime(start, end) >= 0);

        System.out.println(job.getBatchResponse().getRtn());
        return null;
    }

    @RequestMapping("financeBatchRun")
    @ResponseBody
    public BatchResponse financeBatchRun(String startDate, String endDate) {

        HashMap<String, String> hm = new HashMap<String, String>();

        Date start = DateTools.str2date(startDate, "yyyy-MM-dd");
        Date end = DateTools.str2date(endDate, "yyyy-MM-dd");

        hm.put("currentDate", startDate);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean("finance_batch");
        job.setBatchResponse(new BatchResponse());
        do {
            try {
                job.runBatchJob(true, hm);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 天数加1
            start = DateTools.addDate(start);
            // 参数修正
            hm.put("currentDate", DateTools.date2Str(start, "yyyy-MM-dd"));

        } while (DateTools.compareTime(start, end) >= 0);

        System.out.println(job.getBatchResponse().getRtn());
        return null;
    }

    /**
     * 投资人batch处理
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("investBatchRun")
    @ResponseBody
    public BatchResponse investBatchRun() {

        System.out.println("投资人查询批处理开始：￥￥￥￥￥￥￥￥");
        // 参数声明
        HashMap<String, String> hm = new HashMap<String, String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        hm.put("currentTime", dateStr);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean("invest_batch");
        job.setBatchResponse(new BatchResponse());
        try {
            job.runBatchJob(true, hm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(job.getBatchResponse().getRtn());
        System.out.println("投资人查询批处理结束：￥￥￥￥￥￥￥￥");
        return null;
    }

    /**
     * 投资人历史数据batch处理
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("investHistoryBatchRun")
    @ResponseBody
    public BatchResponse investHistoryBatchRun() {

        System.out.println("投资人查询历史数据批处理开始：￥￥￥￥￥￥￥￥");
        // 参数声明
        HashMap<String, String> hm = new HashMap<String, String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        hm.put("currentTime", dateStr);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean("invest_history_batch");
        job.setBatchResponse(new BatchResponse());
        try {
            job.runBatchJob(true, hm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(job.getBatchResponse().getRtn());
        System.out.println("投资人查询历史数据批处理结束：￥￥￥￥￥￥￥￥");
        return null;
    }

    /**
     * 投资人历史数据batch处理
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("investPatchBatchRun")
    @ResponseBody
    public BatchResponse investPatchBatchRun() {

        System.out.println("投资人查询补丁批处理开始：￥￥￥￥￥￥￥￥");
        // 参数声明
        HashMap<String, String> hm = new HashMap<String, String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        hm.put("currentTime", dateStr);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean("invest_patch_batch");
        job.setBatchResponse(new BatchResponse());
        try {
            job.runBatchJob(true, hm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(job.getBatchResponse().getRtn());
        System.out.println("投资人查询补丁批处理结束：￥￥￥￥￥￥￥￥");
        return null;
    }

    /**
     * 注册用户数据batch处理
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("p2pTdUserBatchRun")
    @ResponseBody
    public BatchResponse p2pTdUserBatchRun() {

        System.out.println("投资人查询补丁批处理开始：￥￥￥￥￥￥￥￥");
        // 参数声明
        HashMap<String, String> hm = new HashMap<String, String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        hm.put("currentTime", dateStr);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean("p2p_td_user_batch");
        job.setBatchResponse(new BatchResponse());
        try {
            job.runBatchJob(true, hm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(job.getBatchResponse().getRtn());
        System.out.println("注册用户批处理结束：￥￥￥￥￥￥￥￥");
        return null;
    }

    /**
     * 注册来源追踪参数batch处理
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("p2pTdRegSrcParamBatchRun")
    @ResponseBody
    public BatchResponse p2pTdRegSrcParamBatchRun() {

        System.out.println("注册来源追踪参数批处理开始：￥￥￥￥￥￥￥￥");
        // 参数声明
        HashMap<String, String> hm = new HashMap<String, String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        hm.put("currentTime", dateStr);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean("reg_src_param_batch");
        job.setBatchResponse(new BatchResponse());
        try {
            job.runBatchJob(true, hm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(job.getBatchResponse().getRtn());
        System.out.println("注册来源追踪参数批处理结束：￥￥￥￥￥￥￥￥");
        return null;
    }
    
    /**
     * 注册来源追踪参数batch处理
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("batchRunByBatchName")
    @ResponseBody
    public BatchResponse batchRunByBatchName(String batchName, String date) {

        System.out.println("批处理开始：￥￥￥￥￥￥￥￥");
        // 参数声明
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("currentDate", date);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean(batchName);
        job.setBatchResponse(new BatchResponse());
        try {
            job.runBatchJob(true, hm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(job.getBatchResponse().getRtn());
        System.out.println("批处理结束：￥￥￥￥￥￥￥￥");
        return null;
    }
    
    /**
     * 注册来源追踪参数batch处理
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("batchRunByBatchNameNoDate")
    @ResponseBody
    public BatchResponse batchRunByBatchNameNoDate(String batchName) {

        System.out.println("批处理开始：￥￥￥￥￥￥￥￥");
     // 取得当前的系统时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dt = dateFormat.format(date);
        // 参数声明
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("currentTime", dt);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean(batchName);
        job.setBatchResponse(new BatchResponse());
        try {
            job.runBatchJob(true, hm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(job.getBatchResponse().getRtn());
        System.out.println("批处理结束：￥￥￥￥￥￥￥￥");
        return null;
    }

    /**
     * 注册来源追踪参数batch处理
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping("p2pTdRegSrcTrackBatchRun")
    @ResponseBody
    public BatchResponse p2pTdRegSrcTrackBatchRun() {

        System.out.println("注册来源追踪参数批处理开始：￥￥￥￥￥￥￥￥");
        // 参数声明
        HashMap<String, String> hm = new HashMap<String, String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        hm.put("currentTime", dateStr);
        SpringBatchJob job = (SpringBatchJob) SpringBeanUtils.getBean("reg_src_track_batch");
        job.setBatchResponse(new BatchResponse());
        try {
            job.runBatchJob(true, hm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(job.getBatchResponse().getRtn());
        System.out.println("注册来源追踪参数批处理结束：￥￥￥￥￥￥￥￥");
        return null;
    }
}
