package com.lz.batch.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class MyDateUtil {

    private static Map<String, DateFormat> formaters = null;

    private static DateFormat getDateFormater(String format) {

        if (formaters == null) {
            formaters = new HashMap<String, DateFormat>();
        }
        DateFormat formater = formaters.get(format);
        if (formater == null) {
            formater = new SimpleDateFormat(format);
            formaters.put(format, formater);
        }
        return formater;
    }

    public static Date parse(String strDate, String format) {

        try {
            if(null!=strDate && !"".equals(strDate))
            {
                return getDateFormater(format).parse(strDate);
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Date date, String format) {

        if (date == null) {
            return StringUtils.EMPTY;
        }
        return getDateFormater(format).format(date);
    }

    public static String format(String strDate, String srcFormat, String destFormat) {

        Date date = parse(strDate, srcFormat);
        return format(date, destFormat);
    }

    public static java.sql.Date createSqlDate(int year, int month, int date) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    public static Date createSqlDate(Date date) {

        return new java.sql.Date(date.getTime());
    }

    public static Date toStartOfDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date toEndOfDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static boolean compareDate(Date date1, Date date2) {

        int n = date1.compareTo(date2);
        if (n < 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Date addDays(Date date, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    /**
     * 计算两个时间相差的月份，根据天数>=15天，四舍五入
     * 
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    public static double getMonth(Date start, Date end) {

        if (start == null) {
            start = new Date();
        }
        if (end == null) {
            end = new Date();
        }
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        int day = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH);

        double result = 0.0d;
        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            result = year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
            result = year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
            result = year * 12 + month;
        } else {
            result = (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }

        if (day >= 15) {
            result = result + 1;
        } else if (day > 0 && day < 15) {
            result = result + 0.5;
        }
        return result;
    }

    /**
     * 计算两个时间相差的年，
     * 
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    public static int dateDiffYear(Date start, Date end) {

        if (start == null) {
            start = new Date();
        }
        if (end == null) {
            end = new Date();
        }
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);

        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);

        return year + 1;
    }

    /**
     * 计算日期相差天数，小时，分
     * 
     * @param startTime 0时0分0秒
     * @param endTime 23时59分59秒
     * @return endTime后一天的0时0分0秒与startTime的天数差
     */
    public static int getDiffDays(Date startTime, Date endTime) {

        int result = 0;
        startTime = toStartOfDay(startTime);
        endTime = toStartOfDay(DateUtils.addDays(endTime, 1));
        long nd = 1000 * 24 * 60 * 60; // 一天的毫秒数
        long diff;
        try {
            diff = endTime.getTime() - startTime.getTime();
            long day = diff / nd; // 计算差多少天
            result = (int) day;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 计算日期相差天数，小时，分
     * 
     * @param startTime
     * @param endTime
     * @param format
     * @return
     */
    public static String dateDiffDay(String startTime, String endTime, String format) {

        String result = "";
        if (StringUtils.isEmpty(endTime)) {
            return "截至日期待定";
        }
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
//		long ns = 1000;// 一秒钟的毫秒数
        long diff;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
//			long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            result = day + "天" + hour + "小时" + min + "分";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 计算日期相差小时
     * 
     * @param startTime
     * @param endTime
     * @param format
     * @return
     */
    public static Long dateDiffDayOfHour(String startTime, String endTime, String format) {

        long result = 0;

        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
//		long nm = 1000 * 60;// 一分钟的毫秒数
//		long ns = 1000;// 一秒钟的毫秒数
        long diff;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long hour = diff % nd / nh;// 计算差多少小时
            result = hour;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date createDate(int year) {

        return createDate(year, 1);
    }

    public static Date createDate(int year, int month) {

        return createDate(year, month, 1);
    }

    public static Date createDate(int year, int month, int day) {

        return createDate(year, month, day, 0);
    }

    public static Date createDate(int year, int month, int day, int hour) {

        return createDate(year, month, day, hour, 0);
    }

    public static Date createDate(int year, int month, int day, int hour, int minute) {

        return createDate(year, month, day, hour, minute, 0);
    }

    public static Date createDate(int year, int month, int day, int hour, int minute, int second) {

        return createDate(year, month, day, hour, minute, second, 0);
    }

    public static Date createDate(int year, int month, int day, int hour, int minute, int second, int milliSecond) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);

        return calendar.getTime();
    }

    /**
     * 取得当前月的开始时间
     * 
     * @return 当前月的开始时间
     */
    public static Date getBeginDateOfCurrentMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 取得当前月的结束时间
     * 
     * @return 当前月的结束时间
     */
    public static Date getEndDateOfCurrentMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    /**
     * 取得某个月的结束时间
     * 
     * @return 指定月月的结束时间
     */
    public static Calendar getEndDateOfMonth(Calendar cl) {

        Calendar calendar = DateUtils.toCalendar(cl.getTime());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar;
    }

    /**
     * 取得下一个月的开始时间
     * 
     * @return 下一个月的开始时间
     */
    public static Date getBeginDateOfNextMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 取得下一个月的结束时间
     * 
     * @return 下一个月的结束时间
     */
    public static Date getEndDateOfNextMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    public static Date getBeginDateOfCurrentYear() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getEndDateOfCurrentYear() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    public static List<String> getYearMonthByParam(int count) {

        List<String> list = new ArrayList<String>();
        SimpleDateFormat matter = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        list.add(matter.format(calendar.getTime()));
        for (int i = 1; i < count; i++) {
            calendar.add(Calendar.MONTH, -1);
            list.add(matter.format(calendar.getTime()));
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 后一天
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay,String format) {
        return getcommDay(specifiedDay, 1,format);
    }

    /**
     * 前一天
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayBefore(String specifiedDay,String format) {
        return getcommDay(specifiedDay, -1,format);
    }

    private static String getcommDay(String specifiedDay, int n,String format) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + n);

        String dayAfter = new SimpleDateFormat(format).format(c.getTime());
        return dayAfter;
    }

    public static void main(String[] args) {

        System.out.println(format(getBeginDateOfCurrentMonth(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println(format(getEndDateOfCurrentMonth(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println(format(getBeginDateOfNextMonth(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println(format(getEndDateOfNextMonth(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println(format(getBeginDateOfCurrentYear(), "yyyy-MM-dd HH:mm:ss SSS"));
        System.out.println(format(getEndDateOfCurrentYear(), "yyyy-MM-dd HH:mm:ss SSS"));
    }
}
