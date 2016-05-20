package com.lzjf.util;

public class NumberFormat implements Format {
	private String pattern = null;

	public NumberFormat(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public Object format(Object value) {
		return NumberUtils.format(value, pattern);
	}
}
