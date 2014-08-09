package com.sltunion.cloudy.common.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesUtil {

	private static String filename = "dragon";
	private static ResourceBundle bundle;

	// 根据key读取value
	public static String readValue(String filename, String key) {
		ResourceBundle bundle = bundle(filename);
		String value = bundle.getString(key);
		return value;
	}

	// 根据key读取value
	public static String getString(String key, String defaultvalue) {
		bundle();
		String value = bundle.getString(key);
		if (null == value || "".equals(value.trim())) {
			value = defaultvalue;
		}
		return value;
	}

	// 根据key读取value
	public static String getString(String key) {
		bundle();
		String value = bundle.getString(key);
		return value;
	}

	private static void bundle() {
		if (null != bundle) {
			ResourceBundle.clearCache();
		}
		bundle = ResourceBundle.getBundle(filename, Locale.getDefault());
	}

	private static ResourceBundle bundle(String filename) {
		return ResourceBundle.getBundle(filename, Locale.getDefault());
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println(getString("temp_folder", "0"));
		Thread.sleep(30 * 1000);
		System.out.println(getString("temp_folder", "0"));
	}
}
