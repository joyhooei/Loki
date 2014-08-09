package com.sltunion.cloudy.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.sltunion.cloudy.common.Reg;

public class ObjectUtil {
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String) {
			if ("".equals(((String) obj).trim())) {
				return true;
			}
		} else if (obj instanceof Object[]) {
			if (((Object[]) obj).length <= 0) {
				return true;
			}
		} else if (obj instanceof ArrayList<?>) {
			if (((ArrayList<?>) obj).size() <= 0) {
				return true;
			}
		} else if (obj instanceof Vector<?>) {
			if (((Vector<?>) obj).size() <= 0) {
				return true;
			}
		} else if (obj instanceof HashSet<?>) {
			if (((HashSet<?>) obj).size() <= 0) {
				return true;
			}
		} else if (obj instanceof HashMap<?, ?>) {
			if (((HashMap<?, ?>) obj).size() <= 0) {
				return true;
			}
		}

		return false;
	}

	public static boolean isNotEmpty(Object obj) {
		return (!isEmpty(obj));
	}

	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	public static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(src);
				out = new FileOutputStream(dst);
				byte[] buffer = new byte[400];
				int length = 0;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getString(Object obj) {
		return isEmpty(obj) ? "" : obj.toString().trim();
	}

	public static int getInt(Object obj) {
		return isEmpty(obj) ? 0 : Integer.parseInt(obj.toString().trim());
	}

	public static long getLong(Object obj) {
		return isEmpty(obj) ? 0 : Long.parseLong(obj.toString().trim());
	}

	public static double getDouble(Object obj) {
		return isEmpty(obj) ? 0 : Double.parseDouble(obj.toString().trim());
	}

	public static int testUrl(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			return connection.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 400;
	}

	public static String array2String(String[] strArr) {
		StringBuffer sb = new StringBuffer();
		for (String string : strArr) {
			sb.append(string + ",");
		}

		if (sb.length() > 1) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String getPhonesFromFile(String filepath) throws IOException {
		StringBuffer sb = new StringBuffer();
		Set<String> phoneSet = new HashSet<String>();
		BufferedReader bf = null;
		String line = null;
		bf = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
		while ((line = bf.readLine()) != null) {
			if (ObjectUtil.isEmpty(line)) {
				continue;
			}
			if (Reg.checkphone(line) == -1) {
				continue;
			}
			phoneSet.add(line);
		}

		if (bf != null) {
			bf.close();
		}

		for (String phone : phoneSet) {
			sb.append(phone + ",");
		}

		if (sb.length() > 1) {
			sb.setLength(sb.length() - 1);
		}

		return sb.toString();
	}

	public static String[] splitPhones(String phones, int count) {
		String[] phoneArr = phones.split(",");
		int length = phoneArr.length / count;
		if (phoneArr.length % count != 0) {
			length++;
		}
		String[] tempArr = new String[length];

		for (int i = 0; i < length; i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = i * count; j < i * count + count && j < phoneArr.length; j++) {
				sb.append(phoneArr[j] + ",");
			}
			if (sb.length() > 1) {
				sb.setLength(sb.length() - 1);
			}
			tempArr[i] = sb.toString();
		}
		return tempArr;
	}

	public static String set2String(Set<String> set) {
		StringBuffer sb = new StringBuffer();
		for (String string : set) {
			sb.append(string + ",");
		}
		if (sb.length() > 1) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String[] splitContent(String content, int count) {
		int length = content.length() / count;
		if (content.length() % count != 0) {
			length++;
		}
		String[] tempArr = new String[length];

		for (int i = 0; i < length; i++) {
			int end = i * count + count < content.length() ? i * count + count : content.length();
			tempArr[i] = content.substring(i * count, end);
		}
		return tempArr;
	}

	@SuppressWarnings("unchecked")
	public static <T> T typeCast(Class<T> fieldType, String param) throws Exception {
		T value = null;
		if (param == null) {
			return null;
		}
		if (fieldType == String.class) {
			value = (T) param;
		} else if (fieldType == Integer.class) {
			value = (T) Integer.valueOf(param);
		} else if (fieldType == Double.class) {
			value = (T) Double.valueOf(param);
		} else if (fieldType == Long.class) {
			value = (T) Long.valueOf(param);
		} else if (fieldType == Character.class) {
			value = (T) Character.valueOf(param.toCharArray()[0]);
		} else if (fieldType == Boolean.class) {
			value = (T) Boolean.valueOf(param);
		} else if (fieldType == Float.class) {
			value = (T) Float.valueOf(param);
		} else if (fieldType == Short.class) {
			value = (T) Short.valueOf(param);
		} else if (fieldType == Date.class) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (param.trim().length() > 10) {
				value = (T) format.parse(param);
			} else {
				value = (T) format.parse(param + " 00:00:00");
			}
		}
		return value;
	}

	public static String toBlank(String str) {
		if (isNotEmpty(str)) {
			str = str.replaceAll("\\s*", "");
		} else {
			str = "";
		}
		return str;
	}
	
	public static String object2String(Object obj){
		StringBuffer sb = new StringBuffer();
		Class<?> classShow = obj.getClass();
//		Field[] fields = classShow.getDeclaredFields();
		Method[] methods = classShow.getMethods();

		for (Method method : methods) {
			String methodName = method.getName();
//			for (Field field : fields) {
//				String fieldName = field.getName();
//				String temp = fieldName.substring(0, 1).toUpperCase()
//						+ fieldName.substring(1);
//			}
			sb.append(methodName);
		}
		return sb.toString();
	}

	// 将list转换成json数组
	public static String arrayToJsonArray(List<Object> applist) {
		StringBuffer strB = new StringBuffer();
		strB.append("[");
		for (int i = 0; i < applist.size(); i++) {
			if (i == applist.size() - 1) {
				strB.append(applist.get(i));
			} else {
				strB.append(applist.get(i) + ",");
			}
		}
		strB.append("]");
		return strB.toString();
	}

	// 对小数 进行处理
	public static float pointposrss(float valueNum, int weiNum) {
		BigDecimal bd = new BigDecimal(valueNum);
		return bd.setScale(weiNum, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	public static void main(String[] args) {
		String[] a = splitPhones(
				"15823242427,15123242427,15023242427,11111,22222,3333,444444,555555,666,7777,888,000,999",
				5);
		System.out.println(a.length);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}