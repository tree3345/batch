package com.lz.batch.service.impl;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;

import com.lz.batch.job.qzjob.QzJob;
import com.lz.batch.response.BatchResponse;
import com.lz.batch.service.BatchJobService;
import com.lz.batch.utils.SpringBeanUtils;

public class QuartzJobAddServiceImpl implements BatchJobService {

    private Logger log = Logger.getLogger("biz");

    /**
     * Quartz Bean Factory
     */
    private StdScheduler scheduler;

    @Override
    public BatchResponse doJob(HashMap<String, String> paramMap) {

    	BatchResponse rtn = new BatchResponse();
        boolean flag = verifyMapForAddJob(paramMap);
        if (!flag) {
            log.error("未找到job,执行job参数不正确！");
            rtn.setSuccess(false);
            rtn.setErrorMessage("未找到job,执行job参数不正确！");
            return rtn;
        }
        
        String jobname = paramMap.get("jobname");
        String jobgroup = paramMap.get("jobgroup");
        String jobid = paramMap.get("jobid");
        String cron = paramMap.get("cron");

        @SuppressWarnings("unchecked")
        Class<QzJob> jobclass = SpringBeanUtils.getType(jobid + "_class");
        try {
            JobDetail job = JobBuilder.newJob(jobclass).withIdentity(jobname, jobgroup).build();

            CronTrigger cronTrigger =
                    newTrigger().withIdentity(jobname, jobgroup).withSchedule(cronSchedule(cron).withMisfireHandlingInstructionDoNothing()).build();
            Date ft = addScheduleJob(job, cronTrigger);
            if (ft == null) {
                log.info("job已经存在");
                rtn.setSuccess(false);
                rtn.setErrorMessage("job已经存在！");
            }else{
            	log.info("**调用BATCH JOB成功");
            	rtn.setSuccess(true);
            	rtn.setErrorMessage(null);
            }
        } catch (SchedulerException e) {
        	log.error("发生异常");
        	rtn.setSuccess(false);
            rtn.setRtn("发生异常");
            e.printStackTrace();
        }
        return rtn;
    }

    private boolean verifyMapForAddJob(HashMap<String, String> paramMap) {

    	if(null==paramMap.get("jobid") || "".equals(paramMap.get("jobid")) || null==paramMap.get("cron") || "".equals(paramMap.get("cron")) 
    			|| null==paramMap.get("jobname") || "".equals(paramMap.get("jobname")) || null==paramMap.get("jobgroup") || "".equals(paramMap.get("jobgroup"))){
    		return false;
    	}
        
        return true;
    }

    /**
     * 如果job不存在 加入job和时间
     * 
     * @param job
     * @param trigger
     * @return
     * @throws SchedulerException
     */
    public Date addScheduleJob(JobDetail job, CronTrigger trigger) throws SchedulerException {

        Date ft = null;
        if (!scheduler.checkExists(job.getKey())) {
            ft = scheduler.scheduleJob(job, trigger);
        }
        return ft;
    }

    /**
     * @return the scheduler
     */
    public StdScheduler getScheduler() {

        return scheduler;
    }

    /**
     * @param scheduler the scheduler to set
     */
    public void setScheduler(StdScheduler scheduler) {

        this.scheduler = scheduler;
    }

}
