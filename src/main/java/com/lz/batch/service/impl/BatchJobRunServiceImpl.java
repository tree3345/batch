package com.lz.batch.service.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.lz.batch.job.springbatchjob.SpringBatchJob;
import com.lz.batch.log.MyLogger;
import com.lz.batch.response.BatchResponse;
import com.lz.batch.service.BatchJobService;
import com.lz.batch.utils.SpringBeanUtils;

public class BatchJobRunServiceImpl implements BatchJobService {

    private Logger log = Logger.getLogger("biz");

    @Override
    public BatchResponse doJob(HashMap<String, String> paramMap) {

        MyLogger.put("pre1", "自定参数手动运行job");
        BatchResponse rtn = new BatchResponse();
        //验证job参数
        boolean flag = verifyMapForRunJob(paramMap);
        if (!flag) {
            //TODO
            log.error("未找到job,执行job参数不正确！");
            rtn.setSuccess(false);
            rtn.setErrorMessage("未找到job,执行job参数不正确！");
            return rtn;
        }
        // jobBean id
        String jobid = paramMap.get("jobid");
        MyLogger.put("pre2", jobid);
        // 获得jobBean
        SpringBatchJob springjob = (SpringBatchJob) SpringBeanUtils.getBean(jobid);
        // 启动job是否需要增加执行时间参数
        String timeparam = paramMap.get("paramMap");
        boolean ifNeedNewParam = timeparam.toLowerCase().equals("true") ? true : false;
        // 调用
        try {
            log.info("**调用BATCH JOB开始");
            springjob.setBatchResponse(rtn);
            springjob.runBatchJob(ifNeedNewParam, paramMap);
            if (rtn.isSuccess()) {
                rtn.setRtn(jobid + "完成");
            } else {
                rtn.setRtn(jobid + "失败");
            }
            log.info("**调用BATCH JOB结束");
        } catch (Exception e) {
            rtn.setSuccess(false);
            rtn.setRtn("发生异常");
            rtn.setErrorMessage(e.getMessage());
            log.error("发生异常");
            e.printStackTrace();
        }
        return rtn;
    }

    private boolean verifyMapForRunJob(HashMap<String, String> paramMap) {
    	if(null==paramMap.get("jobid") || "".equals(paramMap.get("jobid")) || null==paramMap.get("paramMap") || "".equals(paramMap.get("paramMap")) ){
    		return false;
    	}
        return true;
    }

}
