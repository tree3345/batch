package com.lz.batch.job.qzjob.businessQzJob;

import com.lz.batch.job.qzjob.QzJob;
import com.lzjf.util.DateUtils;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

import java.util.HashMap;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class UserHbQzJob extends QzJob {
    private Logger log = Logger.getLogger(this.getClass());


    @Override
    public void runJob(JobExecutionContext context) {
        HashMap<String, String> paramhm = new HashMap<String, String>();
        boolean ifNeedNewParam = false;
        try {
            // 红包状态更新
            paramhm.put("jobEntry", "userHbJob");
            paramhm.put("currentDate", DateUtils.dateFormat(0));
            super.excuteJob("common_batch", ifNeedNewParam, paramhm);
        } catch (Exception e) {
            log.error("******调用统计返回异常-******");
        }
    }

}
