package com.lz.batch.controller;

import com.lz.batch.log.MyLogger;
import com.lz.batch.request.BatchRequest;
import com.lz.batch.response.BatchResponse;
import com.lz.batch.service.BatchJobService;
import com.lz.batch.utils.BatchConstants;
import com.lz.batch.utils.MyUtil;
import com.lz.batch.utils.SpringBeanUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@Scope("prototype")
@RequestMapping("batch")
public class BatchController {
    Logger log = Logger.getLogger("biz");

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("run")
    @ResponseBody
    public BatchResponse execute(@RequestBody BatchRequest batchRequest) {
        
        HashMap<String, String> requestHm = batchRequest.getRequestHm();

        BatchResponse response = new BatchResponse();
        String ip = request.getRemoteAddr();
        
        //验证url
        StringBuffer signStr = new StringBuffer();
        String timestamp = requestHm.get("timestamp");
        signStr.append(requestHm.get("sign"));
        signStr.append("&timestamp="+timestamp);
        String rtns = MyUtil.validateUrl(signStr.toString(), timestamp);
        if(rtns.contains("false")){
            response.setErrorMessage(rtns.substring(rtns.indexOf("&")+1));
            response.setSuccess(false);
            return response;
        }
        // log
        MyLogger.put("pre3", ip);
        log.info("调用batch开始");
        
        //
//        HashMap<String, String> urlparams = getParameterMap(request);

        String method = requestHm.get(BatchConstants.SYS_PARAM_METHOD);

        if (method != null) {
            BatchJobService batchJobService = (BatchJobService) SpringBeanUtils.getBean(method);
            response = batchJobService.doJob(requestHm);
        }

        log.info("调用batch结束");
        // 清除log参数
        MDC.clear();
        return response;
    }

}
