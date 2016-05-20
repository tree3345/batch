package com.lz.batch.service.impl;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;

import com.lz.batch.response.BatchResponse;
import com.lz.batch.service.BatchJobService;

public class QuartzJobRemoveServiceImpl implements BatchJobService {

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
        	 //TODO
            log.error("未找到job,执行job参数不正确！");
            rtn.setSuccess(false);
            rtn.setErrorMessage("未找到job,执行job参数不正确！");
            return rtn;
        }
        
        String jobname = paramMap.get("jobname");
        String jobgroup = paramMap.get("jobgroup");
        try {
            boolean ft = deleteScheduleJob(jobname,jobgroup);
            if (ft == false) {
                log.info("job不存在，不允许进行删除操作！");
                rtn.setSuccess(false);
                rtn.setErrorMessage("job不存在，不允许进行删除操作！");
            }else{
            	log.info("**删除BATCH JOB成功");
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

    	if(null==paramMap.get("jobid") || "".equals(paramMap.get("jobid")) ||
    			 null==paramMap.get("jobname") || "".equals(paramMap.get("jobname")) || null==paramMap.get("jobgroup") || "".equals(paramMap.get("jobgroup"))){
    		return false;
    	}
        
        return true;
    }

    /**
     * 如果job存在则删除job
     * 
     * @param jobname
     * @param jobgroup
     * @return
     * @throws SchedulerException
     */
    public boolean deleteScheduleJob(String jobname,String jobgroup) throws SchedulerException {

        boolean ft = true;
        JobKey jobKey = new JobKey(jobname, jobgroup);
      	TriggerKey triggerKey = TriggerKey.triggerKey(jobname, jobgroup);
        if (scheduler.checkExists(jobKey)){
            //scheduler.unscheduleJob(triggerKey);
            scheduler.pauseTrigger(triggerKey);// 停止触发器  
            ft = scheduler.deleteJob(jobKey);
        }else{
        	ft = false;
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
