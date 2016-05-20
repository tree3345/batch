package test.batch;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/13.
 */
public class TestMain {

    public static void main(String[] args) {
        long time = 1460525018155l;
        Date date = new Date(time);
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date));
    }
}
