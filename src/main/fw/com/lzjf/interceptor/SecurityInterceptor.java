package com.lzjf.interceptor;

import com.lzjf.core.base.ValidateException;
import com.lzjf.core.base.ValidateMessage;
import com.lzjf.security.ErrorResponse;
import com.lzjf.security.Response;
import com.lzjf.security.service.ISecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 省略import
public class SecurityInterceptor extends HandlerInterceptorAdapter {// 此处一般继承HandlerInterceptorAdapter适配器即可

    Logger logger = Logger.getLogger(getClass());
    
    @Autowired
    private ISecurityService securityService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	   @SuppressWarnings("unchecked")
           Map<String, String[]> requestParams = request.getParameterMap();
           //验证模式
           String tp_mode = request.getParameter("tp_mode");
           Response errorResponse = null;
           if (tp_mode != null && !tp_mode.isEmpty()) {
        	   // 1. 验证request参数 third part
			   errorResponse = securityService.verifyRequest_md5(requestParams);
		   }else{
			   // 1. 验证request参数 
			   errorResponse = securityService.verifyRequest(requestParams);
		   }
            
          if (null != errorResponse ) {
        	  ErrorResponse error = (ErrorResponse)errorResponse;
        	  List<ValidateMessage> list = new ArrayList<ValidateMessage>();
        	  if (false == errorResponse.getCallResult() ) {
				   ValidateMessage vm  = new ValidateMessage();
				   vm.setFieldCode(error.getCallResult()+"");
				   vm.setFieldName(error.getCode());
				   vm.setFieldErrorMessage(error.getMsg());
				   list.add(vm);
				}
			   throw new ValidateException(list);
          }
          return true;
    }

    


}