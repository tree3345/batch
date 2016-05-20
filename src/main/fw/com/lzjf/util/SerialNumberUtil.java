package com.lzjf.util;
import java.util.Date;

public class SerialNumberUtil {
	public static String getOrdId() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = String.valueOf(UUID.randomNumberUUID(6));
        return timestamp.concat(random);
    }
	public static String getOrdDate() {
		String orderDate = DateUtils.format(new Date(), "yyyyMMdd");
		return orderDate;
	}
}
