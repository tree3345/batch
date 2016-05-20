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
public class SgRollOutQzJob extends QzJob {
    private Logger log = Logger.getLogger(this.getClass());


    @Override
    public void runJob(JobExecutionContext context) {
        HashMap<String, String> paramhm = new HashMap<String, String>();
        boolean ifNeedNewParam = false;
        try {
            // 本金赎回
            paramhm.put("jobEntry","lllsgRollOutJob");
            paramhm.put("redeemType","0");
            paramhm.put("currentDate", DateUtils.dateFormat(0));

            super.excuteJob("common_batch", ifNeedNewParam, paramhm);
            //利息赎回
            paramhm.put("redeemType","1");
            super.excuteJob("common_batch", ifNeedNewParam, paramhm);

        } catch (Exception e) {
            log.error("******调用统计返回异常-******");
        }
    }

}
