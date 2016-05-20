package test.batch;


import org.apache.log4j.Logger;
import org.junit.Test;
import test.batch.springbatchjob.BatchJobTest;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/4/21.
 */
public class WithDrawCashJobTest extends BatchJobTest {


    private Logger logger = Logger.getLogger(WithDrawCashJobTest.class);


    /**
     * 如果提现失败 单跑
     * 需要满足状态为810的
     */
    @Test
    public void testwithDrawCashJob() {
        HashMap<String, String> hm = new HashMap<String, String>();

        logger.info("开始");
        String jobid = "withDrawCashJob";
        super.runJob(jobid, hm);
        logger.info("结束");
    }



    @Override
    public void runJob() {

    }



}
