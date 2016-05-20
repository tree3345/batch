package com.lz.batch.job.springbatchjob.commonJob;

import com.lz.batch.job.springbatchjob.SpringBatchJob;
import com.lz.batch.response.BatchResponse;
import com.lz.batch.utils.SpringBeanUtils;
import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;

import java.util.HashMap;
import java.util.Map;

public class CommonBatchJob extends SpringBatchJob {

    private Logger log = Logger.getLogger("biz");


    private Map<String,String> parameterMap;

    private Job job;
    @Override
    public BatchResponse runBatchJob(boolean ifNeedNewParam, HashMap<String, String> paramhm) throws Exception {


        // job 运行
        String jobEntry = paramhm.get("jobEntry");
        log.info("==StringBatchJob==>" + jobEntry + "开始");
        job = (Job) SpringBeanUtils.getBean(jobEntry);

        BatchResponse batchResponse = super.jobStart(ifNeedNewParam,  paramhm);
        log.info("==StringBatchJob==>" + jobEntry + "结束");
        return batchResponse;
    }


    @Override
    public Job getJob() {
        return job;
    }

    @Override
    public void setJob(Job job) {
        this.job = job;
    }
}
