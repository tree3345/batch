package com.lzjf.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 异常信息管理类
 * 
 * @author ydd676
 */
public class CustomHandlerExceptionResolver extends SimpleMappingExceptionResolver {

    private String getFileKB(long byteFile) {

        if (byteFile == 0)
            return "0KB";
        long kb = 1024;
        return "" + byteFile / kb + "KB";
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // TODO 2014/12/12 mao_siyu del begin-->
        Logger logger = Logger.getLogger(CustomHandlerExceptionResolver.class);
        logger.error("\n=====================================\n");
        logger.error(ex.toString());
        logger.error("\n=====================================\n");
        for (StackTraceElement e : ex.getStackTrace()) {

            logger.error(e.toString());
        }
        // TODO 2014/12/12 mao_siyu del end-->
       
        /**
         * JSON 视图异常通用解析
         */
      
 
        
        // Expose ModelAndView for chosen error view.
        String viewName = determineViewName(ex, request);
        if (viewName != null) {
            Integer statusCode = determineStatusCode(request, viewName);
            if (statusCode != null) {
                applyStatusCodeIfPossible(request, response, statusCode);
            }
            ModelAndView mv = getModelAndView(viewName, ex, request);

            if (ex instanceof MaxUploadSizeExceededException) {
                mv.addObject("errorOnfo", "文件应不大于 " + getFileKB(((MaxUploadSizeExceededException) ex).getMaxUploadSize()));
            }

            // TODO 2014/12/12 mao_siyu del begin-->
            mv.addObject("ex", ex);
            // TODO 2014/12/12 mao_siyu del end-->

            return mv;
        } else {
            return null;
        }
        // if (viewName != null) {// JSP格式返回
        // if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null &&
        // request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
        // // 如果不是异步请求
        // // Apply HTTP status code for error views, if specified.
        // // Only apply it if we're processing a top-level request.
        // Integer statusCode = determineStatusCode(request, viewName);
        // if (statusCode != null) {
        // applyStatusCodeIfPossible(request, response, statusCode);
        // }
        // return getModelAndView(viewName, ex, request);
        // } else {// JSON格式返回
        // try {
        // PrintWriter writer = response.getWriter();
        // writer.write(ex.getMessage());
        // writer.flush();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // return null;
        //
        // }
        // } else {
        // return null;
        // }
    }
}