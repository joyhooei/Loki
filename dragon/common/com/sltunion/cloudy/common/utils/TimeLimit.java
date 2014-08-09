package com.sltunion.cloudy.common.utils;

import java.util.Calendar;

public class TimeLimit {
	private static String[] timeLimits = ReadProperties.getInstance()
			.getString("disable.time","00,01,02,03,04,05,06").split(",");

	public static boolean timeLimit() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		String hourStr = null;
		if(hour<10){
			hourStr = "0"+hour;
		}else{
			hourStr = ""+hour;
		}
		
		for (String timeLimit : timeLimits) {
			if (timeLimit.equals(hourStr)) {
				return true;
			}
		}
		return false;
	}

	public static boolean timeLimit(String sendtime) {
		if (ObjectUtil.isNotEmpty(sendtime)) {
			int index = sendtime.indexOf(":");
			String hour = sendtime.substring(index - 2, index);
			for (String timeLimit : timeLimits) {
				if (timeLimit.equals(hour)) {
					return true;
				}
			}
		}
		return false;
	}
}
