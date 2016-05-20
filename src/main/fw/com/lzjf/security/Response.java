package com.lzjf.security;


public abstract class Response {

	private boolean callResult = true;

	public boolean getCallResult() {
		return callResult;
	}

	public void setCallResult(boolean callResult) {
		this.callResult = callResult;
	}

}
