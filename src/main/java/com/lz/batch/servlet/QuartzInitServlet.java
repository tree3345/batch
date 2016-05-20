package com.lz.batch.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.DisposableBean;

public class QuartzInitServlet extends HttpServlet implements DisposableBean {

    private static final long serialVersionUID = -2981672344643340101L;

//    private static Log log = LogFactory.getFactory().getInstance(QuartzInitServlet.class);

    public void init(ServletConfig config) throws ServletException {

        // spring batch 配置文件启动
//        ApplicationInfo.getApplicationContext();
        // 启动任务调度
//        Scheduler scheduler = (Scheduler) SpringBeanUtils.getBean("scheduleReportFactory");
//        try {
//            scheduler.start();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void destroy() {

//        Scheduler sched = QuartzSchedule.getInstanceScheduler();
//        try {
//            log.info("quartz job清除");
//            sched.clear();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }
}
