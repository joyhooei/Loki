package com.sltunion.cloudy.common.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogerUtil {
	public static Logger logger = LogManager.getLogger(LogerUtil.class);

	public static void info(Class<?> clazz, String message) {
		logger = LogManager.getLogger(clazz);
		logger.info(message);
	}

	public static void info(Class<?> clazz, String message, Throwable t) {
		logger = LogManager.getLogger(clazz);
		logger.info(message, t);
	}

	public static void debug(Class<?> clazz, String message) {
		logger = LogManager.getLogger(clazz);
		logger.debug(message);
	}

	public static void debug(Class<?> clazz, String message, Throwable t) {
		logger = LogManager.getLogger(clazz);
		logger.debug(message, t);
	}

	public static void error(Class<?> clazz, String message) {
		logger = LogManager.getLogger(clazz);
		logger.error(message);
	}

	public static void error(Class<?> clazz, String message, Throwable t) {
		logger = LogManager.getLogger(clazz);
		logger.error(message, t);
	}

	public static void main(String[] args) {
		info(LogerUtil.class, "eeee");
	}
}
