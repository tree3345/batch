package com.lzjf.util;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;

public class NumberUtils {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;
	private static Map<String, DecimalFormat> formaters = null;
	
	private final static String NUM_CHAR = "0123456789";
    private static int charLen = NUM_CHAR.length();

	private static DecimalFormat getDecimalFormat(String pattern) {
		if (formaters == null) {
			formaters = new HashMap<String, DecimalFormat>();
		}
		DecimalFormat formater = formaters.get(pattern);
		if (formater == null) {
			formater = (DecimalFormat) DecimalFormat.getCurrencyInstance();
			formater.applyPattern(pattern);
			formaters.put(pattern, formater);
		}
		return formater;
	}

	public static String format(Object value, String pattern) {
		if (value != null) {
			if (value instanceof BigDecimal) {
				return format((BigDecimal) value, pattern);
			} else if (value instanceof Integer) {
				return format((Integer) value, pattern);
			} else if (value instanceof Double) {
				return format((Double) value, pattern);
			}
		}

		return StringUtils.EMPTY;
	}

	public static String format(BigDecimal value, String pattern) {
		return format(value.doubleValue(), pattern);
	}

	public static String format(Double value, String pattern) {
		return format(value.doubleValue(), pattern);
	}

	public static String format(double value, String pattern) {
		return getDecimalFormat(pattern).format(value);
	}

	public static String format(Integer value, String pattern) {
		return format(value.intValue(), pattern);
	}

	public static String format(int value, String pattern) {
		return getDecimalFormat(pattern).format(value);
	}

	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	public static Number parseNumber(String value, String pattern) {
		try {
			return getDecimalFormat(pattern).parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String toBigNumber(double n) {
		return BigNumberUtils.toNumber(n);
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1 被加数
	 * @param v2 加数
	 * @param v3 加数
	 * @return 三个参数的和
	 */
	public static double add(double v1, double v2, double v3) {
		double sum = add(v1, v2);
		return add(sum, v3);
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的类型转换(Float)
	 * 
	 * @param v 需要被转换的数字
	 * @return 返回转换结果
	 */
	public static float toFloat(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.floatValue();
	}

	/**
	 * 提供精确的类型转换(Int)不进行四舍五入
	 * 
	 * @param v 需要被转换的数字
	 * @return 返回转换结果
	 */
	public static int toInt(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.intValue();
	}

	/**
	 * 提供精确的类型转换(Long)
	 * 
	 * @param v 需要被转换的数字
	 * @return 返回转换结果
	 */
	public static long toLong(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.longValue();
	}

	/**
	 * 返回两个数中大的一个值
	 * 
	 * @param v1 ???要被对比的第一个数
	 * @param v2 需要被对比的第二个数
	 * @return 返回两个数中大的一个值
	 */
	public static double max(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.max(b2).doubleValue();
	}

	/**
	 * 返回两个数中小的一个值
	 * 
	 * @param v1 需要被对比的第一个数
	 * @param v2 需要被对比的第二个数
	 * @return 返回两个数中小的一个值
	 */
	public static double min(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.min(b2).doubleValue();
	}

	/**
	 * 精确对比两个数字
	 * 
	 * @param v1 需要被对比的第一个数
	 * @param v2 需要被对比的第二个数
	 * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
	 */
	public static int compareTo(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.compareTo(b2);
	}
	
	/**
	 * 将字符串类型转换成double类型
	 * 
	 * @param v 需要被转换成数字的字符串
	 * @return 返回转换结果
	 */
	public static double toDouble(String v){
		if (StringUtils.isEmpty(v)) {
			return 0.0d;
		} 
		return Double.valueOf(v);
	}
	
    public static double mul(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        
        if(scale > 0){
        	return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        
		return b1.multiply(b2).doubleValue();
    }
    
    /**
     * 根据系统时间获得指定位数的随机数
     * 
     * @param randomNumberDigit
     *            随机数的位数
     * @return 获得的随机数
     */
    public static String getRandomNumber(int randomNumberDigit) {
        // 获得系统时间，作为生成随机数的种子
        long seed = System.currentTimeMillis() + RandomUtils.nextInt();
        
        StringBuffer sb = new StringBuffer();// 装载生成的随机数
        Random random = new Random(seed);// 调用种子生成随机数
        for (int i = 0; i < randomNumberDigit; i++) {
            sb.append(NUM_CHAR.charAt(random.nextInt(charLen)));
        }

        return sb.toString();
    }
}