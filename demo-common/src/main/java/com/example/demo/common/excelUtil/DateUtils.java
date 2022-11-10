
package com.example.demo.common.excelUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
public class DateUtils {

	/** 时间格式(yyyy-MM-dd) */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * long型常量:一个小时
	 */
	public static final long HOURTIMEDIFF = 3600000l;
	/**
	 * long型常量:一分钟
	 */
	public static final long MINUTETIMEDIFF =60000l;
	/**
	 * long型常量:一天
	 */
	public static final long DAYTIMEDIFF = 86400000l;
	/**
	 * long型常量:一周
	 */
	public static final long WEEKTIMEDIFF = 604800000l;

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
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
     * @param date 日期
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
     * @param date 日期
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
     * @param date 日期
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
     * @param date 日期
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
     * @param date 日期
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
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }
    
	
	
	/**
	 * 将util.Date转换成sql.Date,会丢失时分秒
	 * @param date util.Date
	 * @return
	 */
	public static java.sql.Date convertToSqlDate(Date date){
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * 将util.Date转换成sql.Timestamp
	 * @param date util.Date
	 * @return
	 */
	public static Timestamp convertDateToTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss 按此格式解析时间
	 * @param dateStr String型date 
	 * @return util.Date型date
	 */
	public static Date toDate(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			log.error("转换类型异常",e);
		}
		
		return null;
	}
	
	/**
	 * 获取日期数据格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDatePattern() {
		return DATE_TIME_PATTERN;
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
	 * 根据传入时间数据格式返回该格式的String的当前时间
	 * @param pattern 时间数据格式
	 * @return String型date 
	 */
	public static final String getDate(String pattern) {
		Date date = new Date();
		return getDate(date, pattern);
	}

	
	/**
	 * 根据传入时间数据格式和时间返回该格式的String的时间
	 * @param date util.Date型date 
	 * @param pattern 时间数据格式
	 * @return String型date 
	 */
	public static final String getDate(Date date, String pattern) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (date != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return returnValue;
	}

	
	/**
	 * 根据传入时间数据格式和时间返回该格式的util.Date的时间
	 * @param dateString String型date 
	 * @param pattern 时间数据格式
	 * @return util.Date型date 
	 */
	public static Date getDate(String dateString, String pattern) {
		SimpleDateFormat df = null;
		Date date = null;
		if (dateString != null) {
			try {
				df = new SimpleDateFormat(pattern);
				date = df.parse(dateString);
			} catch (Exception e) {
				log.error("获取时间格式日期异常",e);
			}
		}
		return date;
	}

	
	/**
	 * 返回该格式的String型的当前时间
	 * @return String型date 
	 */
	public static final String getCurDateTime() {
		SimpleDateFormat df  = new SimpleDateFormat(DATE_TIME_PATTERN);
		return df.format(new Date());
	}

	
	/**
	 * 获取当前年份
	 * @return
	 */
	public static String getYear() {
		Date date = new Date();
		return getDate(date, "yyyy");
	}

	
	/**
	 * 获取当前月份
	 * @return
	 */
	public static String getMonth() {
		Date date = new Date();
		return getDate(date, "MM");
	}

	
	/**
	 * 获取当前日
	 * @return
	 */
	public static String getDay() {
		Date date = new Date();
		return getDate(date, "dd");
	}

	
	/**
	 * 获取当前小时
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(11);
	}

	
	/**
	 * 获取当前分钟
	 * @return
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(12);
	}

	
	/**
	 * 获取当前秒
	 * @return
	 */
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(13);
	}
	
	/**
	 * 根据传入的时间获取long型时间
	 * @param date util.Date
	 * @return
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	
	
	/**
	 * 返回两个时间参数int型的差
	 * @param date 开始时间
	 * @param date1 结束时间
	 * @param type 1,天;2,小时；3,分钟;4,秒 不在类型之内的就默认为小时
	 * @return
	 */
	public static int diffDate(Date date, Date date1,int type) {
		long diff=1000*60*60*24l;
		switch(type){
			case 1:
				diff=1000*60*60*24l;
				break;
			case 2:
				diff=1000*60*60l;
				break;
			case 3:
				diff=1000*60l;
				break;
			case 4:
				diff=1000l;
				break;
			default :
				
		}
		return (int) ((getMillis(date) - getMillis(date1)) / diff);
	}

	/**
	 * 
	 * @param date 原始日期
	 * @param minute 需要增加或减少的小时数 -1 表示往前推一小时; 1 表示往后推一小时
	 * @param type 1,年；2,月；3,周;4,天;5,小时；6,分钟; 不在类型之内的就默认为小时
	 * @return date
	 */
	public static Date getDateByType(Date date, int minute, int type) {
		if (null == date || minute == 0)
			return new Date();
		Calendar cc = Calendar.getInstance();
		cc.setTime(date);
		int tmpType = 0;
		switch (type) {
		case 0:
			tmpType = Calendar.HOUR;
			break;
		case 1:
			tmpType = Calendar.YEAR;
			break;
		case 2:
			tmpType = Calendar.MONTH;
			break;
		case 3:
			tmpType = Calendar.WEEK_OF_YEAR;
			break;
		case 4:
			tmpType = Calendar.DAY_OF_YEAR;
			break;
		case 5:
			tmpType = Calendar.HOUR_OF_DAY;
			break;
		case 6:
			tmpType = Calendar.MINUTE;
			break;
		case 7:
			tmpType = Calendar.SECOND;
			break;
		default:
			tmpType = Calendar.HOUR;
			break;
		}
		cc.add(tmpType, minute);
		return cc.getTime();
	}


	/**
	 * 将毫秒数换算成x天x时x分x秒x毫秒
	 * @param ms long型毫秒
	 * @return
	 */
	public static String format(long ms) {
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = ms / dd;
		long hour = (ms - day * dd) / hh;
		long minute = (ms - day * dd - hour * hh) / mi;
		long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		long milliSecond = ms - day * dd - hour * hh - minute * mi - second
				* ss;

		StringBuffer sb = new StringBuffer("");
		if (day != 0l) {
			sb.append(day + "天");
		}
		if (hour != 0l) {
			sb.append(hour + "小时");
		}
		if (minute != 0l) {
			sb.append(minute + "分");
		}

		if (milliSecond != 0l) {
			sb.append(second + "." + milliSecond + "秒");
		} else {
			sb.append(second + "秒");
		}
		return sb.toString();
	}

	/**
	 * 获取yyyy-MM-dd格式时间
	 *
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort(Date time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(time);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	public static String getYDay(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return sdf.format(date);
	}
	
	/**
	 * String转Timestamp
	 * @param dateStr
	 * @return
	 */
	public static Timestamp getTimestamp(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
		Timestamp result = null;
		if(dateStr == null || "".equals(dateStr)){
			return result;
		}
		try {
			Date date = sdf.parse(dateStr);
			result = new Timestamp(date.getTime());
		} catch (Exception e) {
		}
		return result;
	}
	
	/**
     * 得到昨天日期的 格式为 yyyy-MM-dd
     * @return
     */
    public static String getYesterday(){
    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date yesterday = new Date(System.currentTimeMillis() - 86400000);
    	return sdf.format(yesterday);
    }
    
    /**
	 * 厂家报文时间解析 TNMS-24941
	 * @param neTimeStr
	 * @return
	 * 
	 */
	public static Date timeAnalysis(String neTimeStr){
		if(neTimeStr == null || neTimeStr.equals("")){
			return null;
		}
		boolean ifUtc = false;
		if(neTimeStr.endsWith("Z")){
			ifUtc = true;
			neTimeStr = neTimeStr.replace("Z", "");
		}
		if(neTimeStr.indexOf("T") > 0){
			neTimeStr = neTimeStr.replace("T", " ");
		}
		neTimeStr = neTimeStr.split("\\.")[0];
		if (neTimeStr.length()<14){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if(neTimeStr.length() == 19){
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		try{
			Date neTime = sdf.parse(neTimeStr);
			if(ifUtc){
				Calendar c = Calendar.getInstance();
				c.setTime(neTime);
				c.add(Calendar.HOUR, 8);
				return c.getTime();
			}else{
				return neTime;
			}
		}catch(ParseException pe){
			//logger.info("neTime:" + neTimeStr);
			return null;
		}
	}
}
