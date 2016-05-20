package com.lzjf.core.base;

import java.util.List;

public class ValidateException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private List<ValidateMessage> unvalidateList;
    public ValidateException() {
        super();
    }
    
    public ValidateException(String msg) {
        super(msg);
    }

    public ValidateException(List<ValidateMessage> unvalidateList) {
    	setUnvalidateList(unvalidateList);
    }
    public ValidateException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public ValidateException(Throwable cause) {
        super(cause);
    }

	public List<ValidateMessage> getUnvalidateList() {
		return unvalidateList;
	}

	public void setUnvalidateList(List<ValidateMessage> unvalidateList) {
		this.unvalidateList = unvalidateList;
	}

	
    
}
