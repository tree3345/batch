package com.lz.batch.utils;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;

public class QuartzUtils {
	
	 /**
     * 如果job存在则删除job
     * 
     * @param jobname
     * @param jobgroup
     * @return
     * @throws SchedulerException
     */
    public boolean deleteScheduleJob(StdScheduler scheduler,String jobname,String jobgroup) throws SchedulerException {

        boolean ft = true;
        JobKey jobKey = new JobKey(jobname, jobgroup);
      	TriggerKey triggerKey = TriggerKey.triggerKey(jobname, jobgroup);
      	
        scheduler.pauseTrigger(triggerKey);// 停止触发器  
        scheduler.unscheduleJob(triggerKey);
        if (scheduler.checkExists(jobKey)){
            ft = scheduler.deleteJob(jobKey);
        }else{
        	ft = false;
        }
        return ft;
    }
    
    /**
     * 如果job不存在加入job和时间
     * 
     * @param job
     * @param trigger
     * @return
     * @throws SchedulerException
     */
    public Date addScheduleJob(StdScheduler scheduler,JobDetail job, CronTrigger trigger) throws SchedulerException {

        Date ft = null;
        if (!scheduler.checkExists(job.getKey())) {
            ft = scheduler.scheduleJob(job, trigger);
        }
        return ft;
    }

}
