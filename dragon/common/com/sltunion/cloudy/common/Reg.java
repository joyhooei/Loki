/**
 * @Copyright:Copyright (c) 2008 - 2100
 * @Company:zdt
 */
package com.sltunion.cloudy.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title:
 * @Author:liugang
 * @Since:2012-12-10
 * @Version:1.1.0
 */
public class Reg {
    
    /**
     * 匹配所有【非】中文字符和英文字符
     */
    private static String chAndenRegStr = "[[^\u4e00-\u9fa5]&&[^a-z]&&[^A-Z]]";
	// /**
	// * 匹配中文字符的正则表达式： [u4e00-u9fa5]
	// */
	// private static String chineseRegStr = "[u4e00-u9fa5]";
	//
	// /**
	// * 匹配双字节字符(包括汉字在内)：[^x00-xff] 评注：可以用来计算字符串的长度（一个双字节字符长度计2，ASCII字符计1）
	// */
	// private static String doublebyteRegStr = "[^x00-xff]";
	//
	// /**
	// * 匹配空白行的正则表达式：ns*r 评注：可以用来删除空白行
	// */
	// private static String blanklineRegStr = "ns*r";
	//
	// /**
	// * 匹配HTML标记的正则表达式：<(S*?)[^>]*>.*?|<.*? />
	// 评注：网上流传的版本太糟糕，上面这个也仅仅能匹配部分，对于复杂的嵌套标记依旧无能为力
	// */
	// private static String htmlRegStr = "<(S*?)[^>]*>.*?|<.*? /> ";
	//
	// /**
	// * 匹配首尾空白字符的正则表达式：^s*|s*$ 评注：可以用来删除行首行尾的空白字符(包括空格、制表符、换页符等等)，非常有用的表达式
	// */
	// private static String bothDatesBlankRegStr = "^s*|s*$ ";
	//
	// /**
	// * 匹配Email地址的正则表达式：w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*
	// */
	// private static String emailRegStr =
	// "w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*";
	//
	// /**
	// * 匹配网址URL的正则表达式：[a-zA-z]+://[^s]* 评注：网上流传的版本功能很有限，上面这个基本可以满足需求
	// */
	// private static String urlRegStr = "[a-zA-z]+://[^s]* ";
	//
	// /**
	// * 匹配帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)：^[a-zA-Z][a-zA-Z0-9_]{4,15}$
	// */
	// private static String accountRegStr = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
	//
	// /**
	// * 匹配国内电话号码：d{3}-d{8}|d{4}-d{7} 评注：匹配形式如 0511-4405222 或 021-87888822
	// */
	// private static String domesticCallsRegStr = "d{3}-d{8}|d{4}-d{7}";
	//
	// /**
	// * 匹配腾讯QQ号：[1-9][0-9]{4,} 评注：腾讯QQ号从10000开始
	// */
	// private static String qqRegStr = "[1-9][0-9]{4,} ";
	//
	// /**
	// * 匹配中国邮政编码：[1-9]d{5}(?!d) 评注：中国邮政编码为6位数字
	// */
	// private static String postalCodeRegStr = "[1-9]d{5}(?!d)";
	//
	// /**
	// * 匹配身份证：d{15}|d{18} 评注：中国的身份证为15位或18位
	// */
	// private static String idCardRegStr = "d{15}|d{18}";
	//
	// /**
	// * 匹配ip地址：d+.d+.d+.d+ 评注：提取ip地址时有用
	// */
	// private static String ipRegStr = "d+.d+.d+.d+";
	
	/**
	 * 短信白名单内容匹配
	 * 1、带有测试、test、我测、ceshi
	 * 2、 纯数字、纯字母、纯标点符号及混合字符
	 */
	private static String smsContentRegStr = "^(.*(test|\\u6D4B\\u8BD5|\\u6211\\u6D4B|ceshi).*|[^\u4e00-\u9fa5]*)$";

	/**
	 * 1(3[4-9]|5[012789]|8[78])\\d{8} 匹配移动手机号码
	 * 移动号段：1340-1348、135、136、137、138、139
	 * 、147、150、151、152、157、158、159、182、183、184、187、188
	 */
	private static String mobileRegStr = "^(86)?1((34[0-8])|(((3[5-9])|(47)|(5[012789])|(8[23478]))\\d{1}))\\d{7}$";

	/**
	 * 匹配联通手机号码(1(3[0-2]|45|5[5,6]|8[5,6]))\\d{8}$
	 * 联通号段：130、131、132、145、155、156、185、186
	 */
	private static String unicomRegStr = "^(86)?1((3[0-2])|(45)|(5[56])|(8[56]))\\d{8}$";

	/**
	 * 匹配电信手机号码 电信号段：133、1349、153、180、181、189
	 */
	private static String telecomRegStr = "^(86)?1((349)|(((33)|(53)|(8[019]))\\d{1}))\\d{7}$";

	/**
	 * @param phone
	 * @return -1:其它|0:联通|1:电信|2:移动
	 */
	public static int checkphone(String phone) {

		int ret = -1;

		if (matchTaget(phone, mobileRegStr)) {
			ret = 2;
		} else if (matchTaget(phone, unicomRegStr)) {
			ret = 0;
		} else if (matchTaget(phone, telecomRegStr)) {
			ret = 1;
		}

		return ret;
	}

	public static boolean contentWhite(String content) {
		if(matchTaget(content, smsContentRegStr)){
			return true;
		}
		return false;
	}

	private static boolean matchTaget(String msg, String regStr) {
		Pattern p = Pattern.compile(regStr);
		Matcher m = p.matcher(msg);
		boolean flag = m.find();
		return flag;
	}

	public static String filterSymbol(String msg){
	    String ret = msg.replaceAll(chAndenRegStr, "");
	    return ret;
	}
	
	public static void main(String[] args) {
		String phone = "13481234321";
		int ret = checkphone(phone);
		System.out.println(phone + "=====" + ret);
		
		String content = "!@$#%$^%&*()!{}[]。。，，，！@#￥％【】";
		System.out.println(contentWhite(content));
	}
}
