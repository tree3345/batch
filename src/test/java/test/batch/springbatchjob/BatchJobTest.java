package test.batch.springbatchjob;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public abstract class BatchJobTest {

    private Logger logger = Logger.getLogger(BatchJobTest.class);
    

    public void runJob(String jobid, HashMap<String, String> hm) {

        ApplicationContext c = ApplicationInfo.getApplicationContext();
        JobLauncher launcher = (JobLauncher) c.getBean("jobLauncher");
        Job job = (Job) c.getBean(jobid);

        SimpleDateFormat dateFormatnow = new SimpleDateFormat("yyyy 年 MM 月 dd 日  HH 时 mm 分 ss 秒");
        String nowtime = dateFormatnow.format(new Date());
        // 参数
        JobParametersBuilder jobParameter = new JobParametersBuilder();
        for (String key : hm.keySet()) {
            jobParameter.addString(key, hm.get(key));
        }
        jobParameter.addString("nowtime", nowtime);
        try {
            JobParameters jobParameters = jobParameter.toJobParameters();
            logger.info("==>参数:" + jobParameters.toString());
            launcher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
