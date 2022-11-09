
package com.example.demo.common.datautil;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 * @author Mark sunlightcs@gmail.com
 */
public class DateUtils {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式(yyyyMMddHHmmss) */
    public final static String DATE_END_PATTERN = "yyyyMMddHHmmss";
    /** 时间格式(yyyyMMddHHmmss) */
    public final static String DATE_MILL_END_PATTERN = "yyyyMMddHHmm";
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PAGE_PATTERN = "yyyyMMdd";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd HH:mm:ss * @param date 日期
     *
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     *
     * @param week 周期 0本周，-1上周，-2上上周，1下周，2下下周
     * @return 返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 两个日期差 返回秒
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static String daysBetween(Date smdate, Date bdate) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(smdate);

        long time1 = cal.getTimeInMillis();

        cal.setTime(bdate);

        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000);

        return String.valueOf(between_days);

    }
    /**
     * 用年，月，日，时构造日期类型
     *
     * @param iYear
     * @param iMonth
     * @param iDate
     * @param iHour
     * @return
     */
    public static Date getDate(int iYear, int iMonth, int iDate, int iHour) {
        return DateUtils.getDate(iYear, iMonth, iDate, iHour, 0, 0);
    }

    /**
     * 用年，月，日构造日期类型
     *
     * @param iYear
     * @param iMonth
     * @param iDate
     * @return
     */
    public static Date getDate(int iYear, int iMonth, int iDate) {
        return DateUtils.getDate(iYear, iMonth, iDate, 0, 0, 0);
    }
    /**
     *********************************************************************************************************
     * 用年，月，日，时，分，秒构造日期类型
     * @param iYear
     * @param iMonth
     * @param iDate
     * @param iHour
     * @param iMinute
     * @param iSecond
     * @return :
     *********************************************************************************************************
     */
    public static Date getDate(int iYear, int iMonth, int iDate, int iHour,
                               int iMinute, int iSecond) {
        iMonth--;
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.set(iYear, iMonth, iDate, iHour, iMinute, iSecond);
        return canlendar.getTime();
    }
    /**
     * 用年，月，日，时，分构造日期类型
     *
     * @param iYear
     * @param iMonth
     * @param iDate
     * @param iHour
     * @param iMinute
     * @return
     */
    public static Date getDate(int iYear, int iMonth, int iDate, int iHour,
                               int iMinute) {
        return DateUtils.getDate(iYear, iMonth, iDate, iHour, iMinute, 0);
    }
    /**
     * yyyy-MM-dd HH:mm:ss 按此格式解析时间
     * @param aDate util.Date型date
     * @return String型date
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat(DATE_TIME_PATTERN);
            returnValue = df.format(aDate);
        }
        return returnValue;
    }
    /**
     * 日期增加天数
     *
     * @param date
     * @param iDate
     * @return
     */
    public static Date addDay(Date date, int iDate) {
        return addDate(date, Calendar.DAY_OF_MONTH, iDate);
    }

    private static Date addDate(Date date, int iArg0, int iDate) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(date);
        canlendar.add(iArg0, iDate);
        return canlendar.getTime();
    }

    /**
     * 日期增加小时
     *
     * @param date
     * @param iHour
     * @return
     */
    public static Date addHour(Date date, int iHour) {
        return addDate(date, Calendar.HOUR, iHour);
    }

    public static int getMonthByDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = 0;
        month = cal.get(cal.MONTH)+1;
        if(month == 13){
            month = 1;
        }
        return month;
    }
    /**
     * 日期增加分钟
     *
     * @param date
     * @param iMinute
     * @return
     */
    public static Date addMinute(Date date, int iMinute) {
        return addDate(date, Calendar.MINUTE, iMinute);
    }
    /**
     *********************************************************************************************************
     * 格式化日期 yyyy-MM-dd HH:mm:ss.SSS
     * @param date
     * @return :
     *********************************************************************************************************
     */
    public static String get4yMdHmsS(Date date) {
        return getDateFormate(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }
    /**
     *********************************************************************************************************
     * 格式化日期
     * @param date
     * @param formate
     * @return :
     *********************************************************************************************************
     */
    public static String getDateFormate(Date date, String formate) {
        if (date == null) {
            return "";}

        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        return simpleDateFormate.format(date);
    }

    /**
     *判断当前是时间是否在开始时间及结束时间中间
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (startTime == null || endTime == null) {
            return false;
        }
        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}