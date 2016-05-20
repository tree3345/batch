package com.lz.batch.job.springbatchjob;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import com.lz.batch.response.BatchResponse;

/**
 * Spring Batch Job
 * 
 * @author snow
 */
public abstract class SpringBatchJob {

    /** DB Log */
    private Logger log = Logger.getLogger("biz");

    /** JobLauncher */
    private JobLauncher launcher;

    /** 执行的job */
    private Job job;
    
    /**
     * 返回结果
     */
    private BatchResponse batchResponse;

    /**
     * 接受job参数
     * 
     * @param ifNeedNewParam
     * @param paramhm
     * @throws Exception
     */
    public abstract BatchResponse runBatchJob(boolean ifNeedNewParam, HashMap<String, String> paramhm) throws Exception;

    /**
     * 根据整理好的各自job参数运行该job
     * 
     * @param ifNeedNewParam
     * @param jobParamsHm
     * @throws Exception
     */
    public BatchResponse jobStart(boolean ifNeedNewParam, HashMap<String, String> jobParamsHm) {

        // 参数设定
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        for (String key : jobParamsHm.keySet()) {
            jobParametersBuilder.addString(key, jobParamsHm.get(key));
        }
        // 需要时间参数，完全新job启动
        if (ifNeedNewParam) {
            Date date = new Date();
            String dateStr = DateFormatUtils.format(date, "yyyy 年 MM 月 dd 日  HH 时 mm 分 ss 秒");
            jobParametersBuilder.addString("runtime", dateStr);
        }
        // 执行参数
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        String paramStr = jobParameters.toString();
        if (paramStr != null && paramStr.length() > 200) {
            paramStr = paramStr.substring(0, 200);
        }
        log.info("==>BatchJob执行参数:" + paramStr);
        batchResponse.setParam(jobParameters.toString());

        // 执行job
        JobExecution jobExecution;
        try {
            jobExecution = getLauncher().run(getJob(), jobParameters);
            String errorMessage = (String) jobExecution.getExecutionContext().get("errorMessage");
            if(null!=jobExecution.getExecutionContext().get("exception") && null==errorMessage)
            {
                errorMessage = jobExecution.getExecutionContext().get("exception").toString();
            }
            
            System.out.println("errorMessage*****************" + errorMessage);
            if (errorMessage != null) {
                batchResponse.setErrorMessage(errorMessage);
                batchResponse.setSuccess(false);
            }

            // 执行不成功
            if (!jobExecution.getExitStatus().getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
                batchResponse.setSuccess(false);
                batchResponse.setErrorMessage(jobExecution.getAllFailureExceptions().toString());
            }
            
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            System.out.println(e.getMessage());
            batchResponse.setSuccess(false);
            batchResponse.setErrorMessage(e.getMessage());
            log.error("==>BatchJob执行异常结束");
        }
        log.info("==>BatchJob执行结束");
        return batchResponse;
    }

    /**
     * @return the launcher
     */
    public JobLauncher getLauncher() {

        return launcher;
    }

    /**
     * @param launcher the launcher to set
     */
    public void setLauncher(JobLauncher launcher) {

        this.launcher = launcher;
    }

    /**
     * @return the job
     */
    public Job getJob() {

        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {

        this.job = job;
    }

    /**
     * @return the batchResponse
     */
    public BatchResponse getBatchResponse() {

        return batchResponse;
    }

    /**
     * @param batchResponse the batchResponse to set
     */
    public void setBatchResponse(BatchResponse batchResponse) {

        this.batchResponse = batchResponse;
    }
}
