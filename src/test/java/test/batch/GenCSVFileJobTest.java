package test.batch;


import org.apache.log4j.Logger;
import org.junit.Test;
import test.batch.springbatchjob.BatchJobTest;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/4/21.
 */
public class GenCSVFileJobTest extends BatchJobTest {

    private Logger logger = Logger.getLogger(GenCSVFileJobTest.class);


    @Test
    public void testGenCSVFileJobTest() {

        long startTime = System.currentTimeMillis();   //获取开始时间
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("jenData.file", "file:d:/tmp/jenData.csv");
        hm.put("factorCode", "lz_gpa_indxwgt_wpct_000300");
        hm.put("dataBeginDate", "2015-01-01");
//        hm.put("codes",str1);
        hm.put("dataEndDate", "2016-03-03");
        String jobid = "generateJenFileItemJob";
        super.runJob(jobid, hm);
        logger.info("结束");
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("运行时间： " + (endTime - startTime) + "ms");
    }

   /* @Override
    public void runJob() {
    }
*/

}
