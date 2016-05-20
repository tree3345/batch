package com.lzjf.security;

import java.util.List;

public class ErrorResponse extends Response{

	// 错误代码
	private String code;
	// 错误信息
	private String msg;
	// 子错误代码
	private String subCode;
	// 子错误信息
	private String subMsg;
	// 参数列表
	private List<String> args;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSubMsg() {
		return subMsg;
	}
	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}
	public List<String> getArgs() {
		return args;
	}
	public void setArgs(List<String> args) {
		this.args = args;
	}
}
