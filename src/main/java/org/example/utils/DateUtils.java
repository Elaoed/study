package org.example.utils;


import org.apache.commons.lang3.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public final static String dayFormat = "yyyy-MM-dd";
    public final static String dateFormat = "yyyy-MM-dd HH:mm:ss";
    public final static String timeFormat = "HH:mm:ss";
    public final static String minuteFormat = "HH:mm";
    public final static String MAX_DATE = "9999-12-31 23:59:59";

    public static LocalDateTime getStrToDate(String strDate) {
        return getStrToDate(strDate, null);
    }

    public static LocalDateTime getStrToDate(String strDate, String format) {
        if (StringUtils.isBlank(format)) {
            format = dateFormat;
        }
        if (StringUtils.isBlank(strDate)){
            return null;
        }
        try {
            return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(format));
        } catch (Exception e) {
            e.printStackTrace();
            // throw new
        }
        return null;
    }

    public static LocalDateTime getStrToDay(String strDate) {
        return getStrToDate(strDate, dayFormat);
    }

    public static Boolean isWeekend(LocalDateTime localDateTime) {
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        return DayOfWeek.SUNDAY.equals(dayOfWeek) || DayOfWeek.SATURDAY.equals(dayOfWeek);
    }

    public static String getDateToStr(LocalDateTime datetime) {
        return getDateToStr(datetime, null);
    }

    public static String getDateToStr(LocalDateTime datetime, String format) {
        if (datetime == null) {
            return "";
        }
        if (StringUtils.isEmpty(format)) {
            format = dateFormat;
        }
        return datetime.format(DateTimeFormatter.ofPattern(format));
    }

//    public static Date getLongToDate(Long ts) {
//        if (ts == null) {
//            return null;
//        }
//        return new Date(ts);
//    }

//    public static String getLongToStr(Long ts) {
//        Date date = getLongToDate(ts);
//        return getDateToStr(date);
//    }
//
//    public static String getDateToStr(Date date) {
//        return getDateToStr(date, null);
//    }
//
//    public static String getDateToStr(Date date, String format) {
//        if (null == date) {
//            return null;
//        }
//        if (StringUtils.isBlank(format)) {
//            format = dateFormat;
//        }
//        SimpleDateFormat df = new SimpleDateFormat(format);
//        return df.format(date);
//    }

}
