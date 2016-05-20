package com.lzjf.security.service;

import com.lzjf.security.Response;

import java.util.Map;

public interface ISecurityService {

	public Response verifyRequest(Map<String, String[]> requestParams);
	public Response verifyRequest_md5(Map<String, String[]> requestParams);
}
