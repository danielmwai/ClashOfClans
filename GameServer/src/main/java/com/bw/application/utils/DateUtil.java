package com.bw.application.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhYou
 */
public class DateUtil {

    /**
     * 线程安全的DateFormat
     */
    private static ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        @Override
        public void set(DateFormat value) {
            // do nothing for set
        }

        @Override
        public void remove() {
            // do nothing for remove
        }
    };

    /**
     * 将日期字符串转换为日期, 格式为yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期字符串
     * @return 时间戳
     */
    public static long parse(String date) {
        try {
            return DATE_FORMAT.get().parse(date).getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("parse date error: " + date);
        }
    }

    /**
     * 将日期转换为字符串, 格式yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return 日期字符串
     */
    public static String format(Date date) {
        return DATE_FORMAT.get().format(date);
    }

    /**
     * @see #format(java.util.Date)
     */
    public static String format(long timestamp) {
        return format(new Date(timestamp));
    }

    /**
     * 判断t1是否在t2之后<br/>
     * 当t2==null||t2.equals("")时, t2=Long.MAX_VALUE;
     *
     * @param t1
     * @param t2
     * @return true, t1在t2之后
     */
    public static boolean after(long t1, String t2) {
        long time = Long.MAX_VALUE;
        if (t2 != null && t2.trim().length() > 0) {
            time = parse(t2);
        }
        return t1 > time;
    }
}
