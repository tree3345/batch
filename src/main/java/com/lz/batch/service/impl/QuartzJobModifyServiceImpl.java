package com.lz.batch.service.impl;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

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
import com.lz.batch.utils.QuartzUtils;
import com.lz.batch.utils.SpringBeanUtils;

public class QuartzJobModifyServiceImpl implements BatchJobService {

    private Logger log = Logger.getLogger("biz");

    private QuartzUtils quartzUtils;

    public void setQuartzUtils(QuartzUtils quartzUtils) {

        this.quartzUtils = quartzUtils;
    }

    /**
     * Quartz Bean Factory
     */
    private StdScheduler scheduler;

    @Override
    public BatchResponse doJob(HashMap<String, String> paramMap) {

        BatchResponse rtn = new BatchResponse();
        boolean flag = verifyMapForAddJob(paramMap);
        if (!flag) {
            //TODO
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
            boolean ft = modifyScheduleJob(scheduler, jobname, jobgroup, cron, jobclass);
            if (ft == false) {
                log.info("job不存在，不允许进行修改操作！");
                rtn.setSuccess(false);
                rtn.setErrorMessage("job不存在，不允许进行修改操作！");
            } else {
                log.info("**修改BATCH JOB成功");
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

        if (null == paramMap.get("jobid") || "".equals(paramMap.get("jobid")) || null == paramMap.get("cron") || "".equals(paramMap.get("cron"))
                || null == paramMap.get("jobname") || "".equals(paramMap.get("jobname")) || null == paramMap.get("jobgroup")
                || "".equals(paramMap.get("jobgroup"))) {
            return false;
        }

        return true;
    }

    /**
     * 修改job
     * 
     * @param jobname
     * @param jobgroup
     * @param cron
     * @param jobclass
     * @return
     * @throws SchedulerException
     */
    private boolean modifyScheduleJob(StdScheduler scheduler, String jobname, String jobgroup, String cron, Class<QzJob> jobclass) throws SchedulerException {

        boolean ft = true;
        try {
            quartzUtils.deleteScheduleJob(scheduler, jobname, jobgroup);
            JobDetail job = JobBuilder.newJob(jobclass).withIdentity(jobname, jobgroup).build();
            CronTrigger cronTrigger =
                    newTrigger().withIdentity(jobname, jobgroup).withSchedule(cronSchedule(cron).withMisfireHandlingInstructionDoNothing()).build();
            quartzUtils.addScheduleJob(scheduler, job, cronTrigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
