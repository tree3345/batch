package com.lzjf.security.service.impl;

import com.lzjf.security.ErrorResponse;
import com.lzjf.security.service.IErrorResponseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Properties;

@Service
public class ErrorResponseServiceImpl implements IErrorResponseService {
	@Resource(name="errorCodeProperties")
	private Properties errorCodeProperties;

	public Properties getErrorCodeProperties() {
		return errorCodeProperties;
	}

	public void setErrorCodeProperties(Properties errorCodeProperties) {
		this.errorCodeProperties = errorCodeProperties;
	}

	@Override
	public ErrorResponse getErrorResponse(String code) {
		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setCallResult(false);

		errorResponse.setCode(code);
		errorResponse.setMsg(errorCodeProperties.getProperty(code));

		return errorResponse;
	}

	@Override
	public ErrorResponse getErrorResponse(String code, String msg) {
		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setCallResult(false);
		
		errorResponse.setCode(code);
		errorResponse.setMsg(msg);
		
		return errorResponse;
	}

	@Override
	public ErrorResponse getErrorResponse(String code, String msg,
			String subCode, String subMsg) {
		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setCallResult(false);

		errorResponse.setCode(code);
		errorResponse.setMsg(msg);
		errorResponse.setSubCode(subCode);
		errorResponse.setSubMsg(subMsg);

		return errorResponse;
	}

}
