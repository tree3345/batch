package com.lzjf.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 省略import
public class LogInterceptor extends HandlerInterceptorAdapter {// 此处一般继承HandlerInterceptorAdapter适配器即可

    Logger logger = Logger.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String className = handlerMethod.getBean().getClass().getName();
        String methodName = handlerMethod.getMethod().getName();
        MethodParameter[] parameters = handlerMethod.getMethodParameters();

        StringBuffer logBuffer = new StringBuffer();
        logBuffer.append(currentDataTime());
        logBuffer.append(" : StartController--->");
        logBuffer.append(createLogger(className, methodName, parameters));
        logger.info(logBuffer.toString());

        RequestMapping requestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);

        String resuest = request.getRequestURI();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String className = handlerMethod.getBean().getClass().getName();
        String methodName = handlerMethod.getMethod().getName();
        MethodParameter[] parameters = handlerMethod.getMethodParameters();

        StringBuffer logBuffer = new StringBuffer();
        logBuffer.append(currentDataTime());
        logBuffer.append(" : EndController--->");
        logBuffer.append(createLogger(className, methodName, parameters));
        logger.info(logBuffer.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String className = handlerMethod.getBean().getClass().getName();
        String methodName = handlerMethod.getMethod().getName();
        MethodParameter[] parameters = handlerMethod.getMethodParameters();

        StringBuffer logBuffer = new StringBuffer();
        logBuffer.append(currentDataTime());
        logBuffer.append(" : ReturnJsp--->");
        logBuffer.append(createLogger(className, methodName, parameters));
        logger.info(logBuffer.toString());
    }

    private String createLogger(String className, String methodName, MethodParameter[] parameters) {

        StringBuffer logBuffer = new StringBuffer();
        logBuffer.append("{[Class : ");
        logBuffer.append(className);
        logBuffer.append("],[Method : ");
        logBuffer.append(methodName);
        logBuffer.append("],[Paramaters : ");
        for (int i = 0; i < parameters.length; i++) {
            MethodParameter parameter = parameters[i];
            logBuffer.append(parameter.getParameterType().getName());
            if (i != parameters.length - 1) {
                logBuffer.append(", ");
            }
        }
        logBuffer.append("]}");

        return logBuffer.toString();
    }

    private String currentDataTime() {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(date);
    }


}