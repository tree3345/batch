package com.lzjf.security.service;


import com.lzjf.security.ErrorResponse;

public interface IErrorResponseService {
	
	public ErrorResponse getErrorResponse(String code);

	public ErrorResponse getErrorResponse(String code, String msg);

	public ErrorResponse getErrorResponse(String code, String msg, String subCode, String subMsg);

}
