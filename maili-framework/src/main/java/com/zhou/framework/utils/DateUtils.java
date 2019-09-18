package com.zhou.framework.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author bone
 * @version 2017-07-27
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy年MM月dd日 HH:mm", "yyyy.MM",
            "yyyyMMdd", "yyyy", "HH:mm:ss", "yyyy年MM月",
            "MM月dd日", "yyyy年MM月dd日", "yyyyMMdd HH:mm:ss", "MM-dd HH:mm"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }


    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }


    public static String formatDate(Date date, int index) {
        return DateFormatUtils.format(date, parsePatterns[index]);
    }


    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek(Date date) {
        Locale localeCN = Locale.CHINA;
        return formatDate(date, "E", localeCN);
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString().trim(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long hour = (timeMillis / (60 * 60 * 1000));
        long min = ((timeMillis / (60 * 1000)) - hour * 60);
        long second = (timeMillis / 1000 - hour * 60 * 60 - min * 60);


        String houStr = (hour >= 10) ? hour + "" : "0" + hour;
        String minStr = (min >= 10) ? min + "" : "0" + min;
        String secondStr = (second >= 10) ? second + "" : "0" + second;
        return houStr + "&" + minStr + "&" + secondStr;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static String getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60) + "";
    }

    /**
     * 是否闰年
     *
     * @param year 年
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year  年
     * @param month 月
     * @return 最后一天
     */
    public static String getLastDayOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return "31";
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return "30";
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return "29";
            } else {
                return "28";
            }
        }
        return "0";
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 返回一个date
     *
     * @param dateStr
     * @return
     * @throws Exception
     */
    public static Date paseerDateformat(String dateStr, String foramt) {
        SimpleDateFormat sdf = new SimpleDateFormat(foramt);
        Date d = null;
        try {
            d = sdf.parse(dateStr);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return d;
    }

    /**
     * 比较两个时间大小
     * d1小于d2 为true
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean dateContrast(Date d1, Date d2) {
        return d1.before(d2);
    }

    /**
     * 每次加18个月
     *
     * @return
     */
    public static String yearLaterDate() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();
        return DateUtils.formatDate(date, 13);
    }

    /**
     * 每次加7天
     *
     * @return
     */
    public static Date getBetweenDates(int day) {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    public static List<String> getBetweenDates() {
        List<String> result = new ArrayList<>();
        Date begin = new Date();
        Date end = getBetweenDates(6);
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
        while (begin.getTime() <= end.getTime()) {
            result.add(formatDate(tempStart.getTime(), 16) + " " + getWeek(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }

    public static List<Integer> getH() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                result.add(Integer.parseInt("0" + i));
            } else {
                result.add(Integer.parseInt(i + ""));
            }
        }
        return result;
    }

    public static List<String> getM() {
        List<String> result = new ArrayList<>();
        result.add("00");
        result.add("30");
        return result;
    }


    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int belongCalendar(String nowTime, String beginTime, String endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(paseerDateformat(nowTime, "HH:mm:ss"));

        Calendar begin = Calendar.getInstance();
        begin.setTime(paseerDateformat(beginTime, "HH:mm:ss"));

        Calendar end = Calendar.getInstance();
        end.setTime(paseerDateformat(endTime, "HH:mm:ss"));

        if (date.after(begin) && date.before(end)) {
            return 0;
        }
        if (date.before(begin)) {
            return 1;
        }
        if (date.after(end)) {
            return 2;
        }
        return -1;
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(LocalDateTime.now());
    }
}
