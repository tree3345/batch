package com.lzjf.security.service.impl;

import com.lzjf.security.ErrorCodeConstants;
import com.lzjf.security.Response;
import com.lzjf.security.SecurityConstants;
import com.lzjf.security.SecuritySignUtils;
import com.lzjf.security.service.IErrorResponseService;
import com.lzjf.security.service.ISecurityService;
import com.lzjf.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class SecurityServiceImpl implements ISecurityService {

	private static final String APP_KEY = "APP_KEY";
	
	private static final String APP_SECRET = "APP_SECRET";
	@Resource(name="userProperties")
	private Properties userProperties;
	@Autowired
	private IErrorResponseService errorResponseService;
	
	public Properties getUserProperties() {
		return userProperties;
	}

	public void setUserProperties(Properties userProperties) {
		this.userProperties = userProperties;
	}

	public IErrorResponseService getErrorResponseService() {
		return errorResponseService;
	}

	public void setErrorResponseService(IErrorResponseService errorResponseService) {
		this.errorResponseService = errorResponseService;
	}

	@Override
	public Response verifyRequest(Map<String, String[]> requestParams){

		Response response = null;

		try {
			// 如果请求中不包含任何参数
			if(this.hasNoParam(requestParams)){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S1);
			}
			
			// 如果没有传递timestamp
			if(!this.hasParam(requestParams, SecurityConstants.SYS_PARAM_TIMESTAMP)){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S8);
			}
			
			// 验证timestamp是否有效
			if(!this.checkTimestamp(requestParams.get(SecurityConstants.SYS_PARAM_TIMESTAMP)[0])){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S10);
			}

			// 如果没有传递app_key
			if(!this.hasParam(requestParams, SecurityConstants.SYS_PARAM_APP_KEY)){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S2);
			}

			// 如果app_key不存在
			if(!this.verifyAppKey(requestParams.get(SecurityConstants.SYS_PARAM_APP_KEY)[0])){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S3);
			}

			// 如果没有传递method
			if(!this.hasParam(requestParams, SecurityConstants.SYS_PARAM_METHOD)){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S4);
			}

			// 如果没有传递format
			if(!this.hasParam(requestParams, SecurityConstants.SYS_PARAM_FORMAT)){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S5);
			}

			// 如果没有传递sign
			if(!this.hasParam(requestParams, SecurityConstants.SYS_PARAM_SIGN)){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S7);
			}
			
			// 如果签名验证无效
			String secret = userProperties.getProperty(APP_SECRET);
			if(!this.checkSign(requestParams, secret)){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S11);
			}
		} catch (IOException e) {
			e.printStackTrace();
			response = errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S0);
		}

		return response;
	}
	
	@Override
	public Response verifyRequest_md5(Map<String, String[]> requestParams) {
		Response response = null;
		try {
			// 如果请求中不包含任何参数
			if(this.hasNoParam(requestParams)){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S1);
			}
			// 如果没有传递tp_systime
			if(!this.hasParam(requestParams, "tp_systime")){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S14);
			}
			// 如果没有传递tp_mode
			if(!this.hasParam(requestParams, "tp_mode")){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S14);
			}
			// 如果没有传递tp_key
			if(!this.hasParam(requestParams, "tp_key")){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S14);
			}
			// 验证加密MD5_KEY
			if(!this.checkMD5Key(requestParams)){
				return errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S14);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = errorResponseService.getErrorResponse(ErrorCodeConstants.SYS_ERROR_CODE_S0);
		}
		return response;
	}
	/**
	 * 判断请求中是否包含参数
	 * 
	 * @param requestParams
	 * @return
	 */
	private boolean hasNoParam(Map<String, String[]> requestParams){

		boolean checkFlag = false;

		// 请求中没有包含任何参数
		if(requestParams == null || requestParams.isEmpty()){
			checkFlag = true;
		}

		return checkFlag;
	}

	/**
	 * 验证签名是否有效
	 * 
	 * @param requestParams
	 * @param secret
	 * @return
	 * @throws IOException
	 */
	private boolean checkSign(Map<String, String[]> requestParams, String secret) throws IOException{
		
		boolean checkFlag = false;
		
		// 取得请求中的签名
		String requestSign = requestParams.get(SecurityConstants.SYS_PARAM_SIGN)[0];

		// 将去除sign的请求参数签名
		Map<String, String[]> signParams = new HashMap<String, String[]>();
		signParams.putAll(requestParams);
		signParams.remove(SecurityConstants.SYS_PARAM_SIGN);
		
		String checkSign = SecuritySignUtils.signRopRequest(signParams, secret);
		
		// 如果签名结果与请求中的签名相等，则验证通过
		if(requestSign.equals(checkSign)){
			checkFlag = true;
		}

		return checkFlag;
	}
	
	/**
	 * 根据key检查请求中是否包含某特定参数
	 * 
	 * @param requestParams
	 * @param key
	 * @return
	 */
	private boolean hasParam(Map<String, String[]> requestParams, String key){

		boolean checkFlag = false;

		if(requestParams.containsKey(key) && requestParams.get(key) != null && !"".equals(requestParams.get(key))){
			checkFlag = true;
		}

		return checkFlag;
	}
	
	/**
	 * 验证APP_KEY是否有效
	 * 
	 * @param appKey
	 * @return
	 */
	private boolean verifyAppKey(String appKey){

		boolean checkFlag = false;
		
		String sysAppKey = userProperties.getProperty(APP_KEY);

		if(sysAppKey != null && sysAppKey.equals(appKey)){
			checkFlag = true;
		}

		return checkFlag;
	}
	/**
	 * 判断Timestamp是否有效10分钟
	 * @param timestamp
	 * @return
	 * @throws IOException
	 */
	private boolean checkTimestamp(String timestamp) throws IOException{
		long intervalTime = 1000 * 60 * 10; //10分钟
		boolean checkFlag = false;
		long currentTime = System.currentTimeMillis();
		long startTime = Long.valueOf(timestamp);
		if ((currentTime - startTime ) <= intervalTime) {
			 checkFlag = true;
		}
		return checkFlag;
	}
	/**
	 * 判断MD5签名是否正确
	 * @param timestamp
	 * @return
	 * @throws IOException
	 */
	private boolean checkMD5Key(Map<String, String[]> requestParams) throws IOException{
		boolean checkFlag = false;
		// 是否在有效时间内
		String tp_systime = requestParams.get("tp_systime")[0];
		if (!checkTimestamp(tp_systime)) {
			return checkFlag;
		}
		// 是否是third part 验证方式
		String tp_mode = requestParams.get("tp_mode")[0];
		if (!userProperties.getProperty("TP_MODE").equals(tp_mode)) {
			return checkFlag;
		}
		String tp_key =  requestParams.get("tp_key")[0];
		String tp_secret = userProperties.getProperty("TP_SECRET");
		//req_md5_key =MD5(tp_mode+tp_systime+tp_secret)
		StringBuffer sb = new StringBuffer();
		sb.append(tp_mode).append(tp_systime).append(tp_secret);
		String req_md5_key = MD5Util.md5(sb.toString());
		if (tp_key.equals(req_md5_key)) {
			checkFlag = true;
		}
		return checkFlag;
	}
	
	
}
