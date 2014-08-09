package com.sltunion.cloudy.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	/**
	 * dateDiff()方法的unit参数,以年为单位
	 */
	public final static byte DIFF_YEAR = 0;

	/**
	 * dateDiff()方法的unit参数,以月为单位
	 */
	public final static byte DIFF_MONTH = 1;

	/**
	 * dateDiff()方法的unit参数,以日为单位
	 */
	public final static byte DIFF_DAY = 2;

	/**
	 * dateDiff()方法的unit参数,以小时为单位
	 */
	public final static byte DIFF_HOUR = 3;

	/**
	 * dateDiff()方法的unit参数,以分钟为单位
	 */
	public final static byte DIFF_MINUTE = 4;

	/**
	 * dateDiff()方法的unit参数,以秒为单位
	 */
	public final static byte DIFF_SECONDE = 5;

	/**
	 * dateDiff()方法的unit参数,以毫秒为单位
	 */
	public final static byte DIFF_MILLISECOND = 6;

	/**
	 * 获取时间日历对象
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 获取年份(yyyy)
	 */
	public static String getYear() {
		Date date = null;
		return getYear(date, 0);
	}

	/**
	 * 获取年份(yyyy)
	 * 
	 * @param dateStr
	 *            日期字符串，要求yyyy-MM-dd
	 */
	public static String getYear(String dateStr) {
		Date date = parseDate(dateStr);
		return getYear(date, 0);
	}

	/**
	 * 获取年份(yyyy)
	 * 
	 * @param date
	 *            日期
	 */
	public static String getYear(Date date) {
		return getYear(date, 0);
	}

	/**
	 * 获取年份(yyyy)
	 * 
	 * @param dateStr
	 *            日期字符串，要求yyyy-MM-dd
	 * @param deff
	 *            与日期中年份的差值
	 */
	public static String getYear(String dateStr, int deff) {
		Date date = parseDate(dateStr);
		return getYear(date, deff);
	}

	/**
	 * 获取年份(yyyy)
	 * 
	 * @param date
	 *            日期
	 * @param deff
	 *            与日期中年份的差值
	 */
	public static String getYear(Date date, int deff) {
		Calendar c = getCalendar();
		if (date != null) {
			c.setTime(date);
		}
		int year = c.get(Calendar.YEAR) + deff;
		return "" + year;
	}

	/**
	 * 获取月份(MM)
	 */
	public static String getMonthOfYear() {
		Date date = null;
		return getMonthOfYear(date);
	}

	/**
	 * 获取月份(MM)
	 * 
	 * @param dateStr
	 *            日期字符串，要求yyyy-MM-dd
	 */
	public static String getMonthOfYear(String dateStr) {
		Date date = parseDate(dateStr);
		return getMonthOfYear(date);
	}

	/**
	 * 获取月份(MM)
	 * 
	 * @param date
	 *            日期
	 */
	public static String getMonthOfYear(Date date) {
		Calendar c = getCalendar();
		if (date != null) {
			c.setTime(date);
		}
		String monthStr = "";
		int month = c.get(Calendar.MONTH) + 1;
		if (month < 10) {
			monthStr += "0" + month;
		} else {
			monthStr += "" + month;
		}
		return monthStr;
	}

	/**
	 * 获取日期(DD)
	 */
	public static int getDayofMonth() {
		Calendar c = getCalendar();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取月份(MM)
	 * 
	 * @param dateStr
	 *            日期字符串，要求yyyy-MM-dd
	 * @param deff
	 *            与日期中月份的差值
	 */
	public static String getMonthOfYear(String dateStr, int deff) {
		Date date = parseDate(dateStr);
		return getMonthOfYear(date);
	}

	/**
	 * 获取月份(MM)
	 * 
	 * @param date
	 *            日期
	 * @param deff
	 *            与日期中月份的差值
	 */
	public static String getMonthOfYear(Date date, int deff) {
		Calendar c = getCalendar();
		if (date != null) {
			c.setTime(date);
		}
		String monthStr = "";
		int month = c.get(Calendar.MONTH) + 1 + deff;
		if (month <= 0 || month == 12) {
			month = c.get(Calendar.MONTH) + 1;
		} else if (month > 12) {
			month = month % 12;
		}
		if (month < 10) {
			monthStr += "0" + month;
		} else {
			monthStr += "" + month;
		}
		return monthStr;
	}

	/**
	 * 取得当前月 yyyy-MM
	 * 
	 * @return
	 */
	public static String getMonth(String dateStr) {
		return getMonth(dateStr, 0);
	}

	/**
	 * 取得当前日期后（前）deff个月的月 yyyy-MM
	 * 
	 * @return
	 */
	public static String getMonth(String dateStr, int deff) {
		Calendar c = getCalendar();
		Date date = parseDate(dateStr);
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		month += deff;
		if (month < 0) {
			month += 12;
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
		} else if (month > 11) {
			month -= 12;
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
		}
		c.set(c.get(Calendar.YEAR), month, 1);
		return date2String(c.getTime(), "yyyy-MM");
	}

	/**
	 * 得到当前日期所在月的开始日期 yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getMonthBegin() {
		return getMonthBegin(null);
	}

	/**
	 * 得到当前日期所在月的结束日期 yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getMonthEnd() {
		return getMonthEnd(null);
	}

	/**
	 * 得到由dateStr指定的日期所在月的开始日期 yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getMonthBegin(String dateStr) {
		Calendar c = getCalendar();
		if (dateStr != null && !"".equals(dateStr.trim())) {
			Date date = parseDate(dateStr);
			c.setTime(date);
		}
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1, 0, 0, 0);
		return date2String(c.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 得到由dateStr指定的日期所在月的结束日期 yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getMonthEnd(String dateStr) {
		Calendar c = getCalendar();
		if (dateStr != null && !"".equals(dateStr.trim())) {
			Date date = parseDate(dateStr);
			c.setTime(date);
		}
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 1, 23, 59, 59);
		c.add(Calendar.DATE, -1);
		return date2String(c.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 得到由c指定的日期所在星期的开始日期 yyyy-MM-dd
	 * 
	 * @param c
	 * @return
	 */
	public static String getWeekBegin(String dateStr) {
		return getWeekBegin(dateStr, 0);
	}

	/**
	 * 得到由c指定的日期所在星期的结束日期 yyyy-MM-dd
	 * 
	 * @param c
	 * @return
	 */
	public static String getWeekEnd(String dateStr) {
		return getWeekEnd(dateStr, 0);
	}

	/**
	 * 得到指定的日期所在星期的后（前）deff周的开始日期 yyyy-MM-dd
	 * 
	 * @param c
	 * @return
	 */
	public static String getWeekBegin(String dateStr, int deff) {
		Calendar c = getCalendar();
		Date date = parseDate(dateStr);
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		// c.setFirstDayOfWeek(3);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		if (c.get(Calendar.MONTH) == Calendar.JANUARY && week > 51) {
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
		} else if (c.get(Calendar.MONTH) == Calendar.DECEMBER && week == 1) {
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
		}
		week += deff;
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return date2String(c.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 得到由c指定的日期所在星期的后（前）deff周的结束日期 yyyy-MM-dd
	 * 
	 * @param c
	 * @return
	 */
	public static String getWeekEnd(String dateStr, int deff) {
		Calendar c = getCalendar();
		Date date = parseDate(dateStr);
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		if (c.get(Calendar.MONTH) == Calendar.JANUARY && week > 51) {
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
		} else if (c.get(Calendar.MONTH) == Calendar.DECEMBER && week == 1) {
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
		}
		week += deff;
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return date2String(c.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 取得当前日期对象
	 * 
	 * @return 返回java.util.Date日期对象
	 */
	public static Date getDate() {
		return getCalendar().getTime();
	}

	/**
	 * 取得当前日
	 * 
	 * @return
	 */
	public static String getDayOfMonth() {
		Calendar c = getCalendar();
		return "" + (c.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 取得当前星期数 1,2,3,4,5,6,7 代表 星期一.....
	 * 
	 * @return
	 */
	public static String getDayOfWeek() {
		Calendar c = getCalendar();
		int week = c.get(Calendar.DAY_OF_WEEK);
		if (week > 1) {
			week--;
		} else {
			week = 7;
		}
		return "" + week;
	}

	public static int getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static int getDayOfYear(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static int getHour() {
		Calendar c = getCalendar();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 取得当前时间,格式为HH:MM:SS
	 * 
	 * @return 返回当前时间
	 */
	public static String getTimeStr() {
		return date2String(getDate(), "HH:mm:ss");
	}

	/**
	 * 取得当前日期的字符串表示,格式为 yyyy-MM-dd
	 * 
	 * @return 返回日期的字符串表示
	 */
	public static String getDateStr() {
		return date2String(getDate(), "yyyy-MM-dd");
	}

	public static String getDate(Date date) {
		return date2String(date, "yyyy-MM-dd");
	}

	/**
	 * 获取当前日期时间字符串,格式为 yyyy-MM-dd hh:mm:ss
	 * 
	 * @return 返回当前字符串
	 */
	public static String getDatetime() {
		return date2String(getDate(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将指定Date类型转换成指定格式的字符串,格式串参见类注释
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            指定的格式,当format为NULL或空串时,<BR>
	 *            默认为 yyyy-MM-dd 格式
	 * @return 当date为NULL时,返回空串
	 */
	public static String date2String(Date date, String format) {

		String dtstr = "";
		if (date == null) {
			return dtstr;
		}

		if (format == null || "".equals(format.trim())) {
			format = "yyyy-MM-dd";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		dtstr = sdf.format(date);
		return (dtstr == null ? "" : dtstr);

	}

	/**
	 * 将指定字串转换按指定格式转换成java.util.Date类型
	 * 
	 * @param dateStr
	 *            日期字串
	 * @param format
	 *            指定的格式,当format为NULL或空串时,<BR>
	 *            默认为 yyyy-MM-dd 格式
	 * @return 当dateStr 为null或者转换出错时,返回NULL值
	 * @throws RuntimeException
	 *             日期格式与格式串不一致时，抛出RuntimeException
	 */
	public static Date string2Date(String dateStr, String format) {
		Date date = null;

		if (format == null || "".equals(format.trim())) {
			format = "yyyy-MM-dd";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException ex) {
			throw new RuntimeException("日期格式与格式串不一致", ex);
		}

		return date;
	}

	/**
	 * 将指定字串转换按指定格式转换成java.util.Date类型
	 * 
	 * @param dateStr
	 *            日期字串 yyyy-MM-dd 格式
	 * @return 当dateStr 为null或者转换出错时,返回NULL值
	 * @throws RuntimeException
	 *             日期格式与格式串不一致时，抛出RuntimeException
	 */
	public static Date parseDate(String dateStr) {
		return string2Date(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 将指定字串转换按指定格式转换成java.util.Date类型
	 * 
	 * @param dateStr
	 *            日期字串 yyyy-MM-dd HH:mm:ss 格式
	 * @return 当dateStr 为null或者转换出错时,返回NULL值
	 * @throws RuntimeException
	 *             日期格式与格式串不一致时，抛出RuntimeException
	 */
	public static Date paseDateTime(String dateStr) {
		return string2Date(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将java.util.Date转换成 java.sql.Date类型
	 * 
	 * @param date
	 *            java.util.Date对象
	 * @return java.sql.Date对象,如果date为NULL,则返回NULL值
	 */
	public static java.sql.Date parseSQLDate(Date date) {
		if (date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 将java.util.Date转换成 java.sql.Timestamp类型
	 * 
	 * @param date
	 *            java.util.Date对象
	 * @return ava.sql.Timestamp对象,如果date为NULL,则返回NULL值
	 */
	public static Timestamp parseTimestamp(Date date) {

		if (date == null) {
			return null;
		}
		return new Timestamp(date.getTime());

	}

	/**
	 * 将指定时间串转换成日期时间对象
	 * 
	 * @param dateStr
	 *            时间串,可以是yyyy-MM-dd格式 或者 yyyy-MM-dd HH:mm:ss 格式
	 * @return 返回指定时间的Calendar对象
	 * @throws NullPointerException
	 *             当日期时间格式不正确时
	 */
	public static Calendar parseCalendar(String dateStr) {
		Date dt = null;
		dt = string2Date(dateStr, "yyyy-MM-dd HH:mm:ss");
		if (dt == null) {
			dt = parseDate(dateStr);
		}
		Calendar c = getCalendar();
		c.setTime(dt);
		return (c);
	}

	/**
	 * @see #dateDiff(Calendar, Calendar, byte)
	 * 
	 * @return 相差时差
	 * 
	 */
	public static long dateDiff(String dateFrom, String dateTo, byte unit) {
		Calendar from = parseCalendar(dateFrom);
		Calendar to = parseCalendar(dateTo);
		return dateDiff(from, to, unit);
	}

	/**
	 * @see #dateDiff(Calendar, Calendar, byte)
	 * 
	 * @param dateFrom
	 *            开始时间
	 * @param dateTo
	 *            结束时间
	 * @param unit
	 *            单位
	 * @return
	 */
	public static long dateDiff(Date dateFrom, Date dateTo, byte unit) {
		Calendar from = Calendar.getInstance();
		from.setTime(dateFrom);
		Calendar to = Calendar.getInstance();
		to.setTime(dateTo);
		return dateDiff(from, to, unit);
	}

	/**
	 * 返回2个日期之间的差距 unit是时间计算的单位,为本类常量DIFF_&lgt;XXXX&rgt;中的任一类型
	 * 
	 * @param dateFrom
	 *            开始时间的时间串,可以是yyyy-MM-dd格式 或者 yyyy-MM-dd HH:mm:ss 格式
	 * @param dateTo
	 *            结束时间的时间串,可以是yyyy-MM-dd格式 或者 yyyy-MM-dd HH:mm:ss 格式
	 * @param unit
	 *            是时间计算的单位,为以下值中的任一值:<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_YEAR &nbsp;以年为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_MONTH &nbsp;以月为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_DAY &nbsp;以日为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_HOUR &nbsp;以小时为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_MINUTE &nbsp;以分钟为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_SECONDE &nbsp;以秒为单位&nbsp;<br>
	 *            &nbsp;&nbsp;&nbsp;&nbsp;DIFF_MILLISECOND &nbsp;以毫秒为单位&nbsp;
	 * 
	 * @return 相差时差
	 */
	public static long dateDiff(Calendar dateFrom, Calendar dateTo, byte unit) {
		long diff = dateTo.getTimeInMillis() - dateFrom.getTimeInMillis();
		long interval = 0;
		switch (unit) {
		case 0: {
			Calendar from = dateFrom;
			Calendar to = dateTo;
			interval = to.get(Calendar.YEAR) - from.get(Calendar.YEAR);
			from.set(Calendar.YEAR, to.get(Calendar.YEAR));
			if (from.after(to)) {
				if (interval < 0) {
					interval++;
				} else {
					interval--;
				}
			}
			break;
		}
		case 1: {
			int year = dateTo.get(Calendar.YEAR) - dateFrom.get(Calendar.YEAR);
			int month = dateTo.get(Calendar.MONTH) - dateFrom.get(Calendar.MONTH);
			Calendar from = dateFrom;
			Calendar to = dateTo;
			from.set(Calendar.YEAR, dateTo.get(Calendar.YEAR));
			from.set(Calendar.MONTH, dateTo.get(Calendar.MONTH));
			interval = year * 12 + month;
			if (from.after(to)) {
				if (interval < 0) {
					interval++;
				} else {
					interval--;
				}
			}
			break;
		}
		case 2:
			interval = (int) (diff / (1000 * 60 * 60 * 24));
			break;

		case 3:
			interval = (int) (diff / (1000 * 60 * 60));
			break;

		case 4:
			interval = (int) (diff / (1000 * 60));
			break;

		case 5:
			interval = (int) (diff / 1000);
			break;

		default:
			interval = diff;
		}
		return interval;
	}

/**
	 * 自由串格式化日期串,对于下列表中的字符支持\转义<br>
	 * 例如:<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;\yyyy 结果为 y08y (原因为第一个被转义,剩下的字串只能构造成两位年份)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;\y 结果为 y <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;\yyyyy 结果为 y2008 <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;\yyyyyy 结果为 y2008y <br>
	 * 其它字符如此类同
	 * 
	 * @param date
	 *            日期对象
	 * @param strFormat
	 *            格式串,含义如下,注意大小写区分:<br>
	 * 
	 *            <table border=0 cellspacing=3 cellpadding=2 style='font-size:10pt;border:1px solid #000' summary="Chart shows pattern letters, date/time component, presentation, and examples.">
	 *            <tr bgcolor="#ccccff">
	 *            <th align=left>字符表达式
	 *            <th align=left>日期或时间表达含义
	 *            <th align=left>例子
	 *            <tr>
	 *            <td><code>yyyy</code>
	 *            <td>四位年
	 *            <td><code>2000, 2009</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>yy</code>
	 *            <td>两位年
	 *            <td><code>00, 09</code>
	 *            <tr>
	 *            <td><code>MM</code>
	 *            <td>两位月
	 *            <td><code>07 , 23</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>M</code>
	 *            <td>普通月
	 *            <td><code>7 , 23</code>
	 *            <tr>
	 *            <td><code>dd</code>
	 *            <td>两位天
	 *            <td><code>03, 12</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>d</code>
	 *            <td>普通天
	 *            <td><code>3, 12</code>
	 *            <tr>
	 *            <td><code>hh</code>
	 *            <td>两位小时
	 *            <td><code>03, 12</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>h</code>
	 *            <td>普通小时
	 *            <td><code> 3, 12</code>
	 *            <tr>
	 *            <td><code>mm</code>
	 *            <td>分
	 *            <td><code>03 , 12</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>m</code>
	 *            <td>分
	 *            <td><code>3 , 12</code>
	 *            <tr>
	 *            <td><code>ss</code>
	 *            <td>秒
	 *            <td><code>03 , 12</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>s</code>
	 *            <td>秒
	 *            <td><code>3 , 12</code>
	 *            <tr>
	 *            <td><code>SSS</code>
	 *            <td>三位微秒
	 *            <td><code>003, 012 , 199</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>S</code>
	 *            <td>微秒
	 *            <td><code>3, 12 , 199</code>
	 *            <tr>
	 *            <td><code>F</code>
	 *            <td>周几 ,数值
	 *            <td><code>3</code>
	 *            <tr bgcolor="#eeeeff">
	 *            <td><code>E</code>
	 *            <td>周几 ,文本
	 *            <td><code>星期三, Tuesday</code>
	 *            <tr>
	 *            <td><code>a</code>
	 *            <td>Am/pm 标识
	 *            <td><code> PM, 下午</code>
	 *            </table>
	 * @since 1.0.1
	 * @return 如果date 或者 strFormat 为null,则返回空串，否则返回指定格式串
	 */
	public static String formartRandomDate(Date date, String strFormat) {
		if (date == null || strFormat == null) {
			return "";
		}

		String key = strFormat;
		key = key.replaceAll("(?<!\\\\)yyyy", date2String(date, "yyyy"));
		key = key.replaceAll("(?<!\\\\)yy", date2String(date, "yy"));
		key = key.replaceAll("\\\\y", "y");

		key = key.replaceAll("(?<!\\\\)MM", date2String(date, "MM"));
		key = key.replaceAll("(?<!\\\\)M", date2String(date, "M"));
		key = key.replaceAll("(?<!\\\\)mm", date2String(date, "mm"));
		key = key.replaceAll("(?<!\\\\)m", date2String(date, "m"));
		key = key.replaceAll("\\\\(M|m)", "$1");

		key = key.replaceAll("(?<!\\\\)dd", date2String(date, "dd"));
		key = key.replaceAll("(?<!\\\\)d", date2String(date, "d"));
		key = key.replaceAll("\\\\d", "d");

		key = key.replaceAll("(?<!\\\\)hh", date2String(date, "hh"));
		key = key.replaceAll("(?<!\\\\)h", date2String(date, "h"));
		key = key.replaceAll("(?<!\\\\)HH", date2String(date, "HH"));
		key = key.replaceAll("(?<!\\\\)H", date2String(date, "H"));
		key = key.replaceAll("\\\\(H|h)", "$1");

		key = key.replaceAll("(?<!\\\\)ss", date2String(date, "ss"));
		key = key.replaceAll("(?<!\\\\)s", date2String(date, "s"));
		key = key.replaceAll("(?<!\\\\)SSS", date2String(date, "SSS"));
		key = key.replaceAll("(?<!\\\\)SS", date2String(date, "SS"));
		key = key.replaceAll("(?<!\\\\)s", date2String(date, "S"));
		key = key.replaceAll("\\\\(S|s)", "$1");

		key = key.replaceAll("(?<!\\\\)F", date2String(date, "F"));
		key = key.replaceAll("\\\\F", "F");
		key = key.replaceAll("(?<!\\\\)E", date2String(date, "E"));
		key = key.replaceAll("\\\\E", "E");
		key = key.replaceAll("(?<!\\\\)a", date2String(date, "a"));
		key = key.replaceAll("\\\\a", "a");

		return key;
	}

	public static Date getBeforeDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		return c.getTime();
	}

	public static Date getNextDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		return c.getTime();
	}

	/**
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getNextDateStr(String dateStr) {
		return getDateStr(dateStr, 1);
	}

	public static String getBeforDateStr(String dateStr) {
		return getDateStr(dateStr, -1);
	}

	public static String getDateStr(String dateStr, int deff) {
		Calendar c = getCalendar();
		c.setTime(parseDate(dateStr));
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + deff);
		return getDate(c.getTime());
	}

	public static String getDateTime(String dateStr, int deff) {
		Calendar c = getCalendar();
		c.setTime(parseDate(dateStr));
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + deff);
		return date2String(c.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getCurBefDateTime() {
		Calendar c = getCalendar();
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		return date2String(c.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	public static int getHourOfDay(Date date) {
		Calendar c = getCalendar();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMonth(Long time) {
		Date date = new Date(time);
		Calendar c = getCalendar();
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;
		return month;

	}

	public static long formatDateToLong(String strDate, String formatstr) {
		if (strDate == null || "".equals(strDate.trim()) || "0".equals(strDate)) {
			return 0;
		}
		try {
			SimpleDateFormat sfarmat = new SimpleDateFormat(formatstr);
			Date date = sfarmat.parse(strDate);
			return date.getTime() / 1000;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getHour(String time) {
		String[] t = time.split(":");
		int hour = Integer.valueOf(t[0]);
		int min = Integer.valueOf(t[1]);
		if (min > 0) {
			hour += 1;
		}
		return hour;
	}

	public static long getTotalSecondForTime(String time) {
		String[] t = time.split(":");
		int hour = Integer.valueOf(t[0]);
		int min = Integer.valueOf(t[1]);
		int sec = Integer.valueOf(t[2]);
		long ret = 3600 * hour + 60 * min + sec;
		return ret;
	}

	/**
	 * 获取当前时间与0点之间的时间差
	 * 
	 * @return
	 */
	public static int deff() {
		Calendar c = getCalendar();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int curr = hour * 3600 + minute * 60 + second;
		int target = 24 * 3600;
		return target - curr;
	}

	/**
	 * 指定时间与之0点之间的时间差
	 * 
	 * @return
	 */
	public static int deff(int hour, int minute, int second) {
		int curr = hour * 3600 + minute * 60 + second;
		int target = 24 * 3600;
		return target - curr;
	}

	/**
	 * 指定时间与之整点之间的时间差
	 * 
	 * @return
	 */
	public static int deff(int minute, int second) {
		int curr = minute * 60 + second;
		int target = 3600;
		return target - curr;
	}
}
