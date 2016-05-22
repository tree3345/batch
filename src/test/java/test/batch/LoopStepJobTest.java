package test.batch;


import org.apache.log4j.Logger;
import org.junit.Test;
import test.batch.springbatchjob.BatchJobTest;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/4/21.
 */
public class LoopStepJobTest extends BatchJobTest {

    private Logger logger = Logger.getLogger(LoopStepJobTest.class);


    @Test
    public void testLoopStepJobTest() {

        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<String, String>();

        String jobid = "partitionJdbcJob";
        super.runJob(jobid, hm);
        logger.info("结束");
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("运行时间： " + (endTime - startTime) + "ms");
    }

    /*@Override
    public void runJob() {
    }*/


}
