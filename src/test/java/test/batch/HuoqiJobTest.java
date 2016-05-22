package test.batch;


import org.apache.log4j.Logger;
import org.junit.Test;
import test.batch.springbatchjob.BatchJobTest;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/4/21.
 */
public class HuoqiJobTest extends BatchJobTest {

    private Logger logger = Logger.getLogger(HuoqiJobTest.class);

    /**
     * 赎回提现批处理
     */
    @Test
    public void testTTopTradeAccount() {
        HashMap<String, String> hm = new HashMap<String, String>();
        String[] dates =new String[]{"2015-12-21"};

        for (int i = 0;i<dates.length;i++) {
            hm.put("redeemType", "0");
            hm.put("currentDate",dates[i]);
            String jobid = "lllsgRollOutJob";
            super.runJob(jobid, hm);
            logger.info("结束");
        }
    }

    /*@Override
    public void runJob() {
    }
*/


}
