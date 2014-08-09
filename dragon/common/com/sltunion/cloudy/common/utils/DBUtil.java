package com.sltunion.cloudy.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
	public static Map<String, Object> creatInsertSql(Object o, String... exps) {
		Class<?> c = o.getClass();
		Field[] fields = c.getDeclaredFields();
		StringBuffer isql = new StringBuffer("insert into ");// insert 语句
		isql.append(c.getSimpleName().toLowerCase() + "(");
		StringBuffer vsql = new StringBuffer(" values(");// valuse 语句
		List<Object> values = new ArrayList<Object>();
		List<String> expsl = Arrays.asList(exps);
		for (Field field : fields) {
			field.setAccessible(true);
			String name = field.getName();
			if (expsl.contains(name)) {
				continue;
			}
			isql.append(field.getName().toLowerCase() + ",");
			vsql.append("?,");
			try {
				values.add(field.get(o));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql",isql.substring(0, isql.length() - 1) + ")"+ vsql.substring(0, vsql.length() - 1) + ")");
		map.put("values", values.toArray());
		return map;
	}

	public static void createGetCode(Object obj, String objName) {

		String getCode = "";
		String setCode = "";

		Class<?> classShow = obj.getClass();
		Field[] fields = classShow.getDeclaredFields();
		Method[] methods = classShow.getMethods();

		for (Method method : methods) {
			String methodName = method.getName();

			String omp = "";
			String omptype = "";

			for (Field field : fields) {

				String fieldName = field.getName();
				String temp = fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);

				if (methodName.endsWith(temp)) {
					omp = fieldName;
					omptype = field.getType().getName();
					// break;
				}
			}

			if (methodName.startsWith("get") && !"getClass".equals(methodName)) {
				getCode += omptype + " " + omp + " = " + objName + "."
						+ methodName + "();\r\n";
			} else if (methodName.startsWith("set")) {
				setCode += objName + "." + methodName + "(" + omp + ");\r\n";
			}
		}
		System.out.println(getCode);
		System.out.println(setCode);
	}

	public static void main(String[] args) {
//		TChannel tChannel = new TChannel();
//		createGetCode(tChannel, "tChannel");
	}
}
