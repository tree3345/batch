package com.lzjf.core.base;

public class ValidateMessage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fieldName;
	private String fieldCode;
	private String fieldErrorMessage;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getFieldErrorMessage() {
		return fieldErrorMessage;
	}

	public void setFieldErrorMessage(String fieldErrorMessage) {
		this.fieldErrorMessage = fieldErrorMessage;
	}
	
	
}
