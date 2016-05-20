package com.lz.batch.controller.utils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>描述：日期工具类，日期的转换计算等公共方法</p>
 * @author wangzg
 *         <p> ------------------------------------------------------ </p>
 *         <p>修改历史</p>
 *         <p>序号	日期	修改人		修改原因 </p>
 */
public class DateTools {
    /**
     * 日期格式常量定义
     */
    public final static String DEFALT_FORMAT="yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT="yyyy-MM-dd";
    public final static String TIME_FORMAT="HH:mm:ss";
    /**
     * 日期格式工具
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat();
    private static final SimpleDateFormat formatShort = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 获取当前时间
     * @return Date
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 将长整形转换为日期
     * @param time 日期
     * @return Date
     */
    public static Date long2date(long time){
        return new Date(time);
    }
    /**
     * 将当前日期转换为字符串，默认格式的字符串
     * @return String 时间字符串
     */
    public static String date2Str(){
        return formatLong.format(new Date());
    }

    /**
     * 将当前日期转换为指定格式的字符串
     * @param format 格式
     * @return String
     */
    public static String date2Str(String format){
        dateFormat.applyPattern(format);
        return dateFormat.format(new Date());
    }

    /**
     * 将指定日期转换为默认字符串格式
     * @param date 日期
     * @return String
     */
    public static String date2Str(Date date){
        return formatLong.format(date);
    }

    /**
     * 将指定日期转换为指定字符串格式
     * @param date 日期
     * @param format 格式
     * @return String
     */
    public static String date2Str(Date date,String format){
        dateFormat.applyPattern(format);
        return dateFormat.format(date);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为日期
     * @param dateStr 日期字符串
     * @return Date
     */
    public static Date str2date(String dateStr){
        try {
            return formatLong.parse(dateStr);
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * 将日期字符串按指定的格式转换为日期
     * @param dateStr 日期字符串
     * @param format 格式
     * @return Date
     */
    public static Date str2date(String dateStr,String format){
        try {
            dateFormat.applyPattern(format);
            return dateFormat.parse(dateStr);
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * 获取当前时间的日历对象
     * @return Calendar
     */
    public static Calendar calendar() {
        return calendar(new Date());
    }

    /**
     * 获取指定时间的日历对象
     * @param date 时间
     * @return Calendar
     */
    public static Calendar calendar(Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        return cal;
    }
    /**
     * 获取当前时间的日历信息，日期域如下值：:Calendar.YEAR，年；Calendar.MONTH，月；Calendar.DAY_OF_MONTH，日；
     *      Calendar.HOUR_OF_DAY，小时；Calendar.MINUTE，分钟；Calendar.SECOND，秒；Calendar.DAY_OF_WEEK，周中的第几日；
     *      Calendar.DAY_OF_YEAR，年中的第几日；Calendar.WEEK_OF_MONTH，月中的第几周；Calendar.WEEK_OF_YEAR，年中的第几周
     * @param field 日期域
     * @return int
     */
    public static int get(int field) {
        return calendar().get(field);
    }

    /**
     * 获取指定时间的日历信息
     * @param date 指定的时间
     * @param field 日历域，参数参见 get(int field)
     * @return int
     */
    public static int get(Date date,int field){
        return calendar(date).get(field);
    }

    /**
     * 在当前日期加指定天数
     * @param amount 天数,可为负数，表示减少
     * @return Date
     */
    public static Date addDays(int amount){
        return DateUtils.addDays(now(),amount);
    }

    /**
     * 在指定日期上加指定天数
     * @param date 日期
     * @param amount 天数,可为负数，表示减少
     * @return Date
     */
    public static Date addDays(Date date,int amount){
        return DateUtils.addDays(date,amount);
    }

    /**
     * 在当前时间上加指定小时
     * @param amount 指定小时,可为负数，表示减少
     * @return Date
     */
    public static Date addHours(int amount){
        return DateUtils.addHours(now(),amount);
    }

    /**
     * 在指定时间上加指定小时
     * @param date 指定日期
     * @param amount 小时数,可为负数，表示减少
     * @return Date
     */
    public static Date addHours(Date date,int amount){
        return DateUtils.addHours(date,amount);
    }

    /**
     * 比较当前时间与参数指定时间的大小
     * @param anotherDate 指定时间
     * @return 时间差:anotherDate在当前时间之前返回1，相等返回0，在当前时间之后返回-1
     */
    public static int compare(Date anotherDate){
        return now().compareTo(anotherDate);
    }

    /**
     * 比较两个时间的大小
     * @param date1 时间1
     * @param date2 时间2
     * @return 时间差:date2在date1之前返回1，相等返回0，在date1之后返回-1
     */
    public static int compare(Date date1,Date date2){
        return date1.compareTo(date2);
    }

    /**
     * 指定时间与当前时间的差，毫秒数
     * @param date 指定时间
     * @return 差值 date-now
     */
    public static long compareTime(Date date){
        return compareTime(date,now());
    }

    /**
     * 两个指定时间的差值
     * @param date1 时间1
     * @param date2 时间2
     * @return 差值，毫秒date2-date1
     */
    public static long compareTime(Date date1,Date date2){
        return (date2.getTime()-date1.getTime());
    }
    /**
     * 判断某个日期是否在某个日期范围
     * @param beginDate 日期范围开始
     * @param endDate 日期范围结束
     * @param src 需要判断的日期
     * @return boolean
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }

    /**
     * 获取当前月最大的天数
     * @return int 天数
     */
    public static int lastDayOfMonth(){
        return calendar().getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定时间所在月最大的天数
     * @param date 指定时间
     * @return int 天数
     */
    public static int lastDayOfMonth(Date date){
        return calendar(date).getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    /** 
    * 获得指定日期的后一天 
    * @param specifiedDay 
    * @return 
     * @throws java.text.ParseException 
    */ 
    public static String getSpecifiedDayAfter(String specifiedDay) throws java.text.ParseException{ 
	    Calendar c = Calendar.getInstance(); 
	    Date date=null; 
	    date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
	    c.setTime(date); 
	    int day=c.get(Calendar.DATE); 
	    c.set(Calendar.DATE,day+1); 
	
	    String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
	    return dayAfter; 
    }
    
    /**
     * 指定日期加一天
     * @param dt
     * @return
     */
    public static Date addDate(Date dt) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dt);
		calendar.add(calendar.DATE, 1);
		
		return calendar.getTime();
		
	}
}
