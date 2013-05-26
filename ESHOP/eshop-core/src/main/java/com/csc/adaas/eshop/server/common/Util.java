package com.csc.adaas.eshop.server.common;

import static com.csc.adaas.eshop.server.common.EShopConstants.BLANK;
import static com.csc.adaas.eshop.server.common.EShopConstants.UI_DATE_FORMAT;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;





/**
 * Class Name : BRRUtil Description : Branch Revenue Reporting System Utility
 * Class.
 * 
 * @author CSC
 */
public final class Util {

	/** private constructor. */
	private Util() {
	}

	/**
	 * This method returns the instance of SimpleDateFormat with given timeZone.
	 * If timeZone is null creates SimpleDateFormat with default timeZone.
	 * 
	 * @param format
	 *            - date format to be used
	 * @param timeZone
	 *            - timeZone to be used
	 * @return SimpleDateFormat
	 */
	public static SimpleDateFormat instanceSimpleDateFormat(String format, String timeZone) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		if (timeZone != null) {
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		}
		return simpleDateFormat;
	}
	
	/**
	 * Formats a date field using a date format.
	 * 
	 * @author CSC
	 * @param aDate
	 *            the date to be formatted
	 * @param aFormat
	 *            the date format string to use
	 * @return the formatted date
	 */
	public static String formatDate(final java.sql.Date aDate, final String aFormat, final String aTimeZone) {
		if (aDate == null) {
			return BLANK;
		}
		SimpleDateFormat simpleDateFormat = instanceSimpleDateFormat(aFormat, aTimeZone);
		return simpleDateFormat.format(aDate);
	}

	/**
	 * Formats a date field using a date format.
	 * 
	 * @author CSC
	 * @param aDate
	 *            the date to be formatted
	 * @param aFormat
	 *            the date format string to use
	 * @return the formatted date
	 */
	public static String formatDate(final java.sql.Date aDate, final String aFormat) {
		if (aDate == null) {
			return BLANK;
		}
		SimpleDateFormat simpleDateFormat = instanceSimpleDateFormat(aFormat, null);
		return simpleDateFormat.format(aDate);
	}
	
	public static Timestamp formatIntoDBTimestamp(final String dateTime) {
		try {
			SimpleDateFormat uiDateFormat = instanceSimpleDateFormat(UI_DATE_FORMAT, null);
			java.util.Date dt = uiDateFormat.parse(dateTime);
			return new Timestamp(dt.getTime());
		} catch (ParseException pe) {
			return null;
		}
	}
	
	public static String formatIntoUIDate(final Timestamp dateTimestamp) {
		long dateTime = dateTimestamp.getTime();
		SimpleDateFormat uiDateFormat = instanceSimpleDateFormat(UI_DATE_FORMAT, null);
		return uiDateFormat.format(dateTime);
	}
	
	/**
	 * This method gets the time difference between two timestamp in long
	 * representation based on the format asked for. FORMAT supported -
	 * MILLISECOND, SECOND, MINUTE, HOUR
	 * 
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return Time diff
	 */
	public static long getTimeDiff(long startTime, long endTime, int format) {
		// Calculate difference in milliseconds
		long diff = endTime - startTime;
		switch (format) {
		case Calendar.MILLISECOND:
			return diff;
		case Calendar.SECOND:
			// Calculate difference in seconds
			long diffSeconds = diff / 1000;
			return diffSeconds;
		case Calendar.MINUTE:
			// Calculate difference in minutes
			long diffMinutes = diff / (60 * 1000);
			return diffMinutes;
		case Calendar.HOUR:
			// Calculate difference in hours
			long diffHours = diff / (60 * 60 * 1000);
			return diffHours;
		default:
			return diff;
		}
	}
}
