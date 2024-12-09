package com.tfcamerademo.utils;


import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.tfcamerademo.utils.ConstUtils.*;
import static com.tfcamerademo.utils.ConstUtils.DAY;
import static com.tfcamerademo.utils.ConstUtils.HOUR;
import static com.tfcamerademo.utils.ConstUtils.MIN;
import static com.tfcamerademo.utils.ConstUtils.MSEC;
import static com.tfcamerademo.utils.ConstUtils.SEC;


/**
 * 时间工具类
 * @author yxt 2016/8/26
 *
 * 将时间戳转为时间字符串 milliseconds2String
 * 将时间字符串转为时间戳 string2Milliseconds
 * 将时间字符串转为Date类型 string2Date
 * 将Date类型转为时间字符串 date2String
 * 将Date类型转为时间戳 date2Milliseconds
 * 将时间戳转为Date类型 milliseconds2Date
 * 毫秒时间戳单位转换（单位：unit） milliseconds2Unit
 * 获取两个时间差（单位：unit） getIntervalTime
 * 获取当前时间 getCurTimeMills、getCurTimeString、getCurTimeDate
 * 获取与当前时间的差（单位：unit） getIntervalByNow
 * 判断闰年 isLeapYear
 */
public class TimeUtils {

    private TimeUtils() {
        throw new UnsupportedOperationException("UnsupportedOperationException");
    }

    public static final DateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static String milliseconds2String(long milliseconds) {
        return milliseconds2String(milliseconds, DEFAULT_SDF);
    }

    public static String milliseconds2String(long milliseconds, DateFormat format) {
        return format.format(new Date(milliseconds));
    }

    public static long string2Milliseconds(String time) {
        return string2Milliseconds(time, DEFAULT_SDF);
    }

    public static long string2Milliseconds(String time, DateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_SDF);
    }

    public static Date string2Date(String time, DateFormat format) {
        return new Date(string2Milliseconds(time, format));
    }

    public static String date2String(Date time) {
        return date2String(time, DEFAULT_SDF);
    }

    public static String date2String(Date time, DateFormat format) {
        return format.format(time);
    }

    public static long date2Milliseconds(Date time) {
        return time.getTime();
    }

    public static Date milliseconds2Date(long milliseconds) {
        return new Date(milliseconds);
    }

    private static long milliseconds2Unit(long milliseconds, TimeUnit unit) {
        switch (unit) {
            case MSEC:
                return milliseconds / MSEC;
            case SEC:
                return milliseconds / SEC;
            case MIN:
                return milliseconds / MIN;
            case HOUR:
                return milliseconds / HOUR;
            case DAY:
                return milliseconds / DAY;
        }
        return -1;
    }

    public static long getIntervalTime(String time0, String time1, TimeUnit unit) {
        return getIntervalTime(time0, time1, unit, DEFAULT_SDF);
    }

    public static long getIntervalTime(String time0, String time1, TimeUnit unit, DateFormat format) {
        return Math.abs(milliseconds2Unit(string2Milliseconds(time0, format)
                - string2Milliseconds(time1, format), unit));
    }

    public static long getIntervalTime(Date time0, Date time1, TimeUnit unit) {
        return Math.abs(milliseconds2Unit(date2Milliseconds(time1)
                - date2Milliseconds(time0), unit));
    }

    public static long getCurTimeMills() {
        return System.currentTimeMillis();
    }

    public static String getCurTimeString() {
        return date2String(new Date());
    }

    public static String getCurTimeString(DateFormat format) {
        return date2String(new Date(), format);
    }

    public static Date getCurTimeDate() {
        return new Date();
    }

    public static long getIntervalByNow(String time, TimeUnit unit) {
        return getIntervalByNow(time, unit, DEFAULT_SDF);
    }

    public static long getIntervalByNow(String time, TimeUnit unit, DateFormat format) {
        return getIntervalTime(getCurTimeString(), time, unit, format);
    }

    public static long getIntervalByNow(Date time, TimeUnit unit) {
        return getIntervalTime(getCurTimeDate(), time, unit);
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}