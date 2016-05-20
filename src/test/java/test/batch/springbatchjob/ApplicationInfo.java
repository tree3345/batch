package test.batch.springbatchjob;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationInfo {

    private ApplicationContext c = new ClassPathXmlApplicationContext(new String[]{"conf/spring.xml"});
    
    private ApplicationInfo(){};
    
    private static ApplicationInfo thiz;

    public static ApplicationContext getApplicationContext() {

        if (thiz == null) {
            thiz = new ApplicationInfo();
            return thiz.c;
        } else {
            return thiz.c;
        }
    }
}