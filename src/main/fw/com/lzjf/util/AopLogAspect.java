package com.lzjf.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面
 *
 */
public class AopLogAspect {
	
	Logger logger = Logger.getLogger(AopLogAspect.class);
	
	
	/**
	 * 前置通知：在某连接点之前执行的通知，但这个通知不能阻止连接点前的执行
	 * @param jp 连接点：程序执行过程中的某一行为，例如，AServiceImpl.barA()的调用或者抛出的异常行为
	 */
	public void doBefore(JoinPoint jp) {
		StringBuffer strLog = new StringBuffer();
		strLog.append("前: "
				+ jp.getTarget().getClass().getName() + "."
				+ jp.getSignature().getName());
		 Object[] args = jp.getArgs();
		 if (null == args || args.length == 0) {
			   strLog.append(",无参数[]");
			   logger.info(strLog.toString());
				return;
			}
	 	 strLog .append(",一共"+args.length+"参数:[");
			for (int i = 0; i < args.length; i++) {
				try {
					String className = null;
					if (args[i] != null) {
						 className = args[i].getClass().getName();  
			            className = className.substring(className.lastIndexOf(".") + 1);
					}
					strLog.append("类型(" + className + ")="+JacksonJsonUtil.beanToJson(args[i])+",");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			removeLastCharacter(strLog);
		  strLog.append("]");
		  logger.info(strLog.toString());
	}
    /**
     * 环绕通知：包围一个连接点的通知，可以在方法的调用前后完成自定义的行为，也可以选择不执行
     * 类似Web中Servlet规范中的Filter的doFilter方法。
     * @param pjp 当前进程中的连接点
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        time = System.currentTimeMillis() - time;
        logger.info("执行中: "+pjp.getSignature().getName()+",消耗时间: " + time + " 毫秒");
        return retVal;
    }
    /**
     * 方法后通知 ： 在方法抛出异常退出时执行的通知。
     * @param jp 连接点：程序执行过程中的某一行为，例如，AServiceImpl.barA()的调用或者抛出的异常行为
     */
    public void doAfter(JoinPoint jp) {
    	StringBuffer strLog = new StringBuffer();
    	strLog.append("执行后: "
    			+ jp.getTarget().getClass().getName() + "."
    			+ jp.getSignature().getName()); 
    	logger.info(strLog.toString());
    }
    
    /**
     * 抛出异常后通知 ： 在方法抛出异常执行的通知。
     * @param jp 连接点：程序执行过程中的某一行为，例如，AServiceImpl.barA()的调用或者抛出的异常行为
     */
    public void doThrowing(JoinPoint jp, Throwable ex) {  
    	StringBuffer strLog = new StringBuffer();
    	strLog.append("异常： " + jp.getTarget().getClass().getName()  
                + "." + jp.getSignature().getName() + " 抛出异常.");  
    	strLog.append("\n方法异常内容："+ex.getMessage());  
    	logger.info(strLog.toString());
    }  
    
   
    
    private void removeLastCharacter(StringBuffer buff) {
		int len = buff.length();
		buff.replace(len - 1, len, StringUtils.EMPTY);
	}
}