package com.sltunion.cloudy.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class ReadProperties {
	private static ReadProperties instance;
	private String fileName = "qxt.properties";
	private File file;
	private Properties properties;
	private long lastModified;

	private ReadProperties() {
		try {
			this.properties = new Properties();
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ReadProperties(String fileName) {
		this.fileName = fileName;
		try {
			this.properties = new Properties();
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get instance of ReadProperties
	 * 
	 * @param - fileStr(If the incoming only allows a),Optiona
	 * @return instance
	 */
	public synchronized static ReadProperties getInstance(String... fileNames) {
		if (instance == null) {
			if (ObjectUtil.isEmpty(fileNames)) {
				instance = new ReadProperties();
			} else {
				instance = new ReadProperties(fileNames[0]);
			}
		}
		return instance;
	}

	private File getFile() throws FileNotFoundException{
		File file = null;
		try {
			URI fileUri = this.getClass().getClassLoader().getResource(
					this.fileName).toURI();
			file = new File(fileUri);
		} catch (URISyntaxException e) {
			throw new FileNotFoundException("File does not exist:" + this.fileName);
		}
		return file;
	}

	/**
	 * 
	 * @param strings
	 *            - key, required; - default value(null for no default
	 *            value),Optional;
	 * @return Key corresponding to the value in the file, or return to the
	 *         default value when the value is null
	 */
	public String getString(String... strings) {
		String value = null;
		try {
			if (ObjectUtil.isEmpty(strings)) {
				throw new IllegalArgumentException(
						"Parameter is null or length of parameters equal to 0:"
								+ strings);
			}
			long newTime = this.file.lastModified();
			if ( newTime> this.lastModified) {
				properties.clear();
				load();
				this.lastModified = newTime;
			}

			int length = strings.length;

			switch (length) {
			case 1: {
				value = properties.getProperty(strings[0]);
				break;
			}
			case 2: {
				value = properties.getProperty(strings[0], strings[1]);
				break;
			}
			default: {
				break;
			}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 
	 * @param strings
	 *            - key, required; - default value(null for no default
	 *            value),Optional;
	 * @return Key corresponding to the value in the file, or return to the
	 *         default value when the value is null
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public int getInt(String... strings) {
		int value = 0;
		try {
			if (ObjectUtil.isEmpty(strings)) {
				throw new IllegalArgumentException(
						"Parameter is null or length of parameters equal to 0:"
								+ strings);
			}

			long newTime = this.file.lastModified();
			if ( newTime> this.lastModified) {
				properties.clear();
				load();
				this.lastModified = newTime;
			}

			int length = strings.length;

			String tempValue = null;
			switch (length) {
			case 1: {
				tempValue = properties.getProperty(strings[0]);
				if (ObjectUtil.isEmpty(tempValue)) {
					tempValue = "0";
				}
				value = Integer.valueOf(tempValue);
				break;
			}
			case 2: {
				tempValue = properties.getProperty(strings[0], strings[1]);
				if (ObjectUtil.isEmpty(tempValue)) {
					tempValue = "0";
				}
				value = Integer.valueOf(tempValue);
				break;
			}
			default: {
				break;
			}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 
	 * @param strings
	 *            - key, required; - default value(null for no default
	 *            value),Optional;
	 * @return Key corresponding to the value in the file, or return to the
	 *         default value when the value is null
	 */
	public long getLong(String... strings) {
		long value = 0;
		try {
			if (ObjectUtil.isEmpty(strings)) {
				throw new IllegalArgumentException(
						"Parameter is null or length of parameters equal to 0:"
								+ strings);
			}

			long newTime = this.file.lastModified();
			if ( newTime> this.lastModified) {
				properties.clear();
				load();
				this.lastModified = newTime;
			}

			int length = strings.length;

			String tempValue = null;
			switch (length) {
			case 1: {
				tempValue = properties.getProperty(strings[0]);
				if (ObjectUtil.isEmpty(tempValue)) {
					tempValue = "0";
				}
				value = Long.valueOf(tempValue);
				break;
			}
			case 2: {
				tempValue = properties.getProperty(strings[0], strings[1]);
				if (ObjectUtil.isEmpty(tempValue)) {
					tempValue = "0";
				}
				value = Long.valueOf(tempValue);
				break;
			}
			default: {
				break;
			}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	private void load() throws IOException {
		FileInputStream fis = null;
		try {
			this.file = this.getFile();
			fis = new FileInputStream(this.file);
			this.properties.load(fis);
			this.lastModified = this.file.lastModified();
		} catch (Exception e) {
			throw new IOException(e);
		}finally{
			if(fis!=null){
				fis.close();
			}
		}
	}
}
