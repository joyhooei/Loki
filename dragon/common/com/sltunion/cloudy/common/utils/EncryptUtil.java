package com.sltunion.cloudy.common.utils;

import java.io.UnsupportedEncodingException;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

/**
 * 加密解密工具类
 * 
 * @author sundial
 * 
 */
public class EncryptUtil {
	public static String encode(String src) {
		// 先异或34， 再异或35
		src = src.replaceAll("\n", "");
		src = src.replaceAll("\r", "");
		src = src.replaceAll("\\s{2,}", "");
		char[] c = src.toCharArray();
		for (int i = 0; i < c.length; i++) {
			int ret = c[i] ^ 34;
			c[i] = (char) ret;
		}
		for (int i = 0; i < c.length; i++) {
			int ret = c[i] ^ 35;
			c[i] = (char) ret;
		}
		return String.valueOf(c);
	}

	public static String decode(String src) {
		// 先异或35， 再异或34
		src = src.replaceAll("\n", "");
		src = src.replaceAll("\r", "");
		char[] c = src.toCharArray();
		for (int i = 0; i < c.length; i++) {
			int ret = c[i] ^ 35;
			c[i] = (char) ret;
		}
		for (int i = 0; i < c.length; i++) {
			int ret = c[i] ^ 34;
			c[i] = (char) ret;
		}
		return String.valueOf(c);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, Base64DecodingException {
//		String str = "<fsfsef><fesfe>";
//		String destr = encode(str);
//		System.out.println(destr);
//		
//		str = decode(destr);
//		System.out.println(str);
		
		String destr = "=>ylm!wdsrhno<#0/1#!dobnehof<#TUG,9#!ru`oe`mnod<#xdr#>?=ldrr`fd?=rxrWdsrhno?01165=.rxrWdsrhno?=tqe`udInru?ehg/8xgb/bnl;91=.tqe`udInru?=tqe`udHq?009/037/05/39=.tqe`udHq?=tqe`udHq3?091/041/075/39=.tqe`udHq3?=tqe`udQnsu?91=.tqe`udQnsu?=mnbjGm`f?0=.mnbjGm`f?=tsmBntou?2=.tsmBntou?=tsmDousx?iuuq;..vvv/i`n032/bnl.>uo<89815213^i`n^qf=.tsmDousx?=tsmDousx?iuuq;..vvv/i`n032/bnl.>uo<89815213^i`n^qf=.tsmDousx?=tsmDousx?iuuq;..vvv/i`n032/bnl.>uo<89815213^i`n^qf=.tsmDousx?=e^eo^sm?iuuq;..ehg/8xgb/bnl.bmntex.envo.@leRcrdr{{cy65/emm=.e^eo^sm?=r^eo^sm?iuuq;..ehg/8xgb/bnl.bmntex.envo.@leRcrdr{{cy65/rxr=.r^eo^sm?=tqe`udBntou?1=.tqe`udBntou?=`tunRu`suHd?0=.`tunRu`suHd?=qsuLd?0=.qsuLd?=.ldrr`fd?";
		String str = decode(destr);
		System.out.println(str);
	}
}
