package br.com.flightslist.util;

import java.time.LocalDateTime;

/**
 * Class utilitarie with tools to manipulate date and time.
 * 
 * @author David Pansardis
 * @version 1.0
 *
 */
public class DateUtil {

	/**
	 * Adjust a date time to initial time of day.
	 * 
	 * @param localDateTime to adjust.
	 * @return localDateTime to adjusted.
	 */
	public static LocalDateTime adjustDateToFirstHourOfDay(LocalDateTime localDateTime) {
		return localDateTime.withHour(0).withMinute(0).withSecond(0);
	}

	/**
	 * Adjust a date time to last time of day.
	 * 
	 * @param localDateTime to adjust.
	 * @return localDateTime to adjusted.
	 */
	public static LocalDateTime adjustDateToLastHourOfDay(LocalDateTime localDateTime) {
		return localDateTime.withHour(23).withMinute(59).withSecond(59);
	}

}
