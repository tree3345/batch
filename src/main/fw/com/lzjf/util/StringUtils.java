package com.lzjf.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	public static List<String> toList(String valueString) {
		List<String> values = new ArrayList<String>();
		if (StringUtils.isNotEmpty(valueString)) {
			String[] tokens = valueString.split(",");
			if (tokens != null) {
				for (String token : tokens) {
					values.add(token);
				}
			}
		}

		return values;
	}

	public static ListMap<String, String> toListMap(String valueString) {
		ListMap<String, String> listMap = new ListMap<String, String>();
		String[] tokens = valueString.split(",");
		if (tokens != null) {
			for (String token : tokens) {
				String[] subTokens = token.split("=");
				if (subTokens.length == 1) {
					listMap.put(subTokens[0], subTokens[0]);
				} else if (subTokens.length == 2) {
					listMap.put(subTokens[0], subTokens[1]);
				}
			}
		}
		return listMap;
	}

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		// 全角的unicode 12288
		str = str.replace((char) 12288, ' ');
		if ("".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static String star(String str, int startOffset, int endOffset) {
		int length = str.length();
		if (startOffset + endOffset >= length) {
			return str;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(str.substring(0, startOffset));
		int starCount = length - startOffset - endOffset;
		for (int i = 0; i < starCount; i++) {
			sb.append('*');
		}
		sb.append(str.substring(length - endOffset));

		return sb.toString();
	}
	
	public static String starByNum(String str, int startOffset, int endOffset,int num) {
        int length = str.length();
        StringBuffer sb = new StringBuffer();
        sb.append(str.substring(0, startOffset));
        for (int i = 0; i < num; i++) {
            sb.append('*');
        }
        sb.append(str.substring(length - endOffset));
        return sb.toString();
    }
    
	public static String nullToSpace(String value) {
		if (value == null) {
			return "";
		} else {
			return value;
		}
	}
	/**
	 * 截取中文英文字符串,过多的用省略号显示
	 */
	public static String subStringProjectCode(final String str, final int maxLength) {
		if (str == null) {
			return str;
		}
		String suffix = "...";
		int suffixLen = suffix.length();
		
		final StringBuffer sbuffer = new StringBuffer();
		final char[] chr = str.trim().toCharArray();
		int len = 0;
		for (int i = 0; i < chr.length; i++) {
			
			if (chr[i] >= 0xa1) {
				len += 2;
			} else {
				len++;
			}
		}
		
		if(len<=maxLength){
			return str;
		}
		
		len = 0;
		for (int i = 0; i < chr.length; i++) {
 
			if (chr[i] >= 0xa1) {
				len += 2;
				if (len + suffixLen > maxLength) {
					break;
				}else {
					sbuffer.append(chr[i]);
				}
			} else {
				len++;
				if (len + suffixLen > maxLength) {
					break;
				}else {
					sbuffer.append(chr[i]);
				}
			}
		}
		sbuffer.append(suffix);
		return sbuffer.toString();
	}
	/**
	 * 合同身份证号加*处理
	 * @param idnumber
	 * @return
	 */
	   public static String paseIdnuber(String idnumber) {
	        if (idnumber == null && "".equals(idnumber)) {
	            return "";
	        }
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < idnumber.length(); i++) {
	            if (i < 6 || i > idnumber.length() - 5) {
	                sb.append(idnumber.charAt(i));
	            } else {
	                sb.append("*");
	            }
	        }
	        return sb.toString();
	    }
}
