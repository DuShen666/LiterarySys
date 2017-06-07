package com.ccut.literary.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String formatTime(Long timeMillis) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date(timeMillis);
		return sdf.format(date);
	}

	public static String formatTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}

	public static long getTimeMillis() {
		long timeMillis = System.currentTimeMillis();
		return timeMillis;
	}

	public static String formatTimeMillis(Long timeMillis) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date(timeMillis);
		return sdf.format(date);
	}

	public static String getDateString() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}

	public static String getSimpleDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

}
