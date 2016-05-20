package com.lzjf.util;

import java.util.HashMap;
import java.util.Map;


public class LogInfoMap{
	private Map<Object, Object> map = new HashMap<Object, Object>();
	
	public LogInfoMap put(Object key, Object value){
		map.put(key, value);
		return this;
	}
	
	/**
	 * @return 将map转成字符串
	 */
	public String mapToJson(){
		return JacksonJsonUtil.beanToJson(map);
	}
}