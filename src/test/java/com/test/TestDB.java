package com.test;

import com.lz.batch.utils.MyDateUtil;
import com.lz.mapper.FactorTableMapper;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2016/3/24.
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext({"classpath*:conf/spring.xml"})
public class TestDB {

    Logger logger = Logger.getLogger(TestDB.class);

    @SpringBean("factorTableMapper")
    private FactorTableMapper factorTableMapper;

    @SpringBean("memcachedClient")
    private MemcachedClient memcachedClient;



    @Test
    public void testInsertCal() throws Exception{
        String startDate = "1990-01-01";
        String endDate = "2099-12-31";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (sdf.parse(startDate).compareTo(sdf.parse(endDate))!=1) {
            factorTableMapper.insertCalendar(sdf.parse(startDate));
            startDate = MyDateUtil.getSpecifiedDayAfter(startDate,"yyyy-MM-dd");
        }
    }

    @Test
    public void testMemCached(){

        try {
            memcachedClient.set("spring", 0, "3.0.0.RELEASE");

            memcachedClient.replace("spring", 0, "4.0.0.RELEASE is coming");

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

    }
}
