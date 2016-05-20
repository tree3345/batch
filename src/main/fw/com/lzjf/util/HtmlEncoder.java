package com.lzjf.util;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class HtmlEncoder {
	private static final String[] HTML_CODE = new String[256];
	private static final Set<String> NEWLINETAGS;

	public static String encode(String string) {
		if(StringUtils.isEmpty(string)) {
			return string;
		}
		int n = string.length();

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < n; i++) {
			char character = string.charAt(i);

			if (character < 'Ā') {
				buffer.append(HTML_CODE[character]);
			} else {
				buffer.append(character);
			}
		}
		return buffer.toString();
	}

	static {
		for (int i = 0; i < 10; i++) {
			HTML_CODE[i] = ("&#00" + i + ";");
		}

		for (int i = 10; i < 32; i++) {
			HTML_CODE[i] = ("&#0" + i + ";");
		}

		for (int i = 32; i < 128; i++) {
			HTML_CODE[i] = String.valueOf((char) i);
		}

		HTML_CODE[9] = "\t";
		HTML_CODE[10] = "<br />\n";
		HTML_CODE[34] = "&quot;";
		HTML_CODE[38] = "&amp;";
		HTML_CODE[60] = "&lt;";
		HTML_CODE[62] = "&gt;";

		for (int i = 128; i < 256; i++) {
			HTML_CODE[i] = ("&#" + i + ";");
		}

		NEWLINETAGS = new HashSet<String>();

		NEWLINETAGS.add("p");
		NEWLINETAGS.add("blockquote");
		NEWLINETAGS.add("br");
	}

}