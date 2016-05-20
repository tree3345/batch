package test.batch;


import org.apache.log4j.Logger;
import org.junit.Test;
import test.batch.springbatchjob.BatchJobTest;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/4/21.
 */
public class UserHbJobTest extends BatchJobTest {

    private Logger logger = Logger.getLogger(UserHbJobTest.class);

    /**
     * 赎回提现批处理
     */
    @Test
    public void testUserHb() {
        HashMap<String, String> hm = new HashMap<String, String>();

        logger.info("开始");
        hm.put("currentDate","2015-12-10");
        String jobid = "userHbJob";
        super.runJob(jobid, hm);
        logger.info("结束");
    }



    @Override
    public void runJob() {

    }



}
