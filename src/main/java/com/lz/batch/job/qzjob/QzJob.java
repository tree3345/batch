package com.lz.batch.job.qzjob;

import com.lz.batch.job.springbatchjob.SpringBatchJob;
import com.lz.batch.log.MyLogger;
import com.lz.batch.response.BatchResponse;
import com.lz.batch.utils.SpringBeanUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.HashMap;

public abstract class QzJob implements Job {

    private Logger log1 = Logger.getLogger("biz");

    private Logger log2 = Logger.getLogger("biz2");


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        runJob(context);
    }

    public abstract void runJob(JobExecutionContext context);

    // XX

    public boolean excuteJob(String jobId, boolean ifNeedNewParam, HashMap<String, String> paramhm) {

        boolean flag = false;
        MyLogger.put("pre1", "quartz定时任务");
        MyLogger.put("pre2", jobId);
        BatchResponse batchResponse = new BatchResponse();
        SpringBatchJob springBatchJob = (SpringBatchJob) SpringBeanUtils.getBean(jobId);
        springBatchJob.setBatchResponse(batchResponse);
        log1.info(jobId + ":运行开始");
        try {
            batchResponse = springBatchJob.runBatchJob(ifNeedNewParam, paramhm);
             flag = batchResponse.isSuccess();
        } catch (Exception e) {
            flag = false;
            batchResponse.setSuccess(false);
            batchResponse.setRtn(jobId + ":运行失败");
            batchResponse.setErrorMessage(e.getMessage());
            log1.error(jobId + ":运行失败");
        }
        log1.info(jobId + ":运行结束");

        MyLogger.put("JOB_ID", jobId);
        MyLogger.put("START_TYPE", "J1004001");
        if(batchResponse.getParam()!=null)
        MyLogger.put("PARAM_INFO", batchResponse.getParam());
        if (batchResponse.isSuccess() == true) {
        	MyLogger.put("EXECUTE_HAPPEN", "执行成功" + "");
            MyLogger.put("EXECUTE_RESULT", "成功" + "");
        }else{
        	MyLogger.put("EXECUTE_HAPPEN", "执行异常" + "");
            MyLogger.put("EXECUTE_RESULT", "失败" + "");
            
            if(null != batchResponse.getErrorMessage()){
            	if(batchResponse.getErrorMessage().contains("无返回的urlkey==null")){
                	MyLogger.put("EXECUTE_HAPPEN", "执行成功");
                	MyLogger.put("EXECUTE_RESULT", "符合下载的文件为空");
                    batchResponse.setSuccess(true);
                    batchResponse.setErrorMessage(null);
                }else if(batchResponse.getErrorMessage().contains("通知账户授信结果已经上传失败")){
                	MyLogger.put("EXECUTE_HAPPEN", "执行成功");
                	MyLogger.put("EXECUTE_RESULT", "通知账户授信结果已经上传 失败！");
                    batchResponse.setSuccess(true);
                    if(batchResponse.getErrorMessage().contains("false:")){
                    	batchResponse.setErrorMessage(batchResponse.getErrorMessage().substring(batchResponse.getErrorMessage().indexOf("false:")+6));
                    }
                }else{
              	   if(batchResponse.getErrorMessage().contains("exception:")){
	               		int firstIndex = batchResponse.getErrorMessage().indexOf("exception:");
	               		int secondIndex = batchResponse.getErrorMessage().indexOf("exceptionJob:");
	               		MyLogger.put("EXECUTE_RESULT", batchResponse.getErrorMessage().substring(secondIndex));
	   	                if(batchResponse.getErrorMessage().substring(firstIndex+10,secondIndex).length()>255){
	   	                	batchResponse.setErrorMessage(batchResponse.getErrorMessage().substring(firstIndex+10,secondIndex).substring(0,254));//出错位置step
	   	                }else{
	   	                	batchResponse.setErrorMessage(batchResponse.getErrorMessage().substring(firstIndex+10,secondIndex));
	   	                }
              	   }
               }
               if(null!=batchResponse.getErrorMessage()){
	            	if(batchResponse.getErrorMessage().length()>255){
	            	MyLogger.put("RETURN_MESSAGE", batchResponse.getErrorMessage().substring(0,254) + "");
	            	}else{
	            		MyLogger.put("RETURN_MESSAGE", batchResponse.getErrorMessage() + "");
	            	}
            	}
            }else{
	            MyLogger.put("EXECUTE_HAPPEN", "执行异常");
	            MyLogger.put("EXECUTE_RESULT", "失败");
	        }
        }

        log2.info("quartz job-->batchjob:" + jobId);
        MDC.clear();
        return flag;
    }

}
