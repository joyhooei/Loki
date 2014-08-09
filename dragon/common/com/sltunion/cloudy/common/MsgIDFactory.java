package com.sltunion.cloudy.common;

import java.util.Calendar;

public class MsgIDFactory {

	// private static long MSGSEQ_WEB_MIN = 1;// 0x000001
	// private static long MSGSEQ_WEB_MAX = 1048576;// 0x100000
	//
	// private static long MSGSEQ_HTTP_MIN = 1048577;// 0x100001
	// private static long MSGSEQ_HTTP_MAX = 2097152;// 0x200000
	//
	// private static long MSGSEQ_SOCKET_MIN = 2097153;// 0x200001
	// private static long MSGSEQ_SOCKET_MAX = 3145728;// 0x300000
	//
	// private static long MSGSEQ_WEBSERVICE_MIN = 3145729;// 0x300001
	// private static long MSGSEQ_WEBSERVICE_MAX = 4194304;// 0x400000

	private static long MSGSEQ_WEB_MIN = 1;// 0x1
	private static long MSGSEQ_WEB_MAX = 32768;// 0x8000

	private static long MSGSEQ_HTTP_MIN = 32769;// 0x8001
	private static long MSGSEQ_HTTP_MAX = 65536;// 0x10000

	private static long MSGSEQ_SOCKET_MIN = 65536;// 0x10001
	private static long MSGSEQ_SOCKET_MAX = 98304;// 0x18000

	private static long MSGSEQ_WEBSERVICE_MIN = 98305;// 0x18001
	private static long MSGSEQ_WEBSERVICE_MAX = 131072;// 0x20000

	private static long MSGSEQ_WEB = MSGSEQ_WEB_MIN;
	private static long MSGSEQ_HTTP = MSGSEQ_HTTP_MIN;
	private static long MSGSEQ_SOCKET = MSGSEQ_SOCKET_MIN;
	private static long MSGSEQ_WEBSERVICE = MSGSEQ_WEBSERVICE_MIN;

	public final static int WEB = 1;
	public final static int HTTP = 2;
	public final static int SOCKET = 3;
	public final static int WEBSERVICE = 4;

	protected static long getMsgidSeq(int type) {
		long msgseq = 0;
		switch (type) {
		case HTTP: {
			MSGSEQ_HTTP++;
			if (MSGSEQ_HTTP > MSGSEQ_HTTP_MAX) {
				MSGSEQ_HTTP = MSGSEQ_HTTP_MIN;
			}
			msgseq = MSGSEQ_HTTP;
			break;
		}
		case WEB: {
			MSGSEQ_WEB++;
			if (MSGSEQ_WEB > MSGSEQ_WEB_MAX) {
				MSGSEQ_WEB = MSGSEQ_WEB_MIN;
			}
			msgseq = MSGSEQ_WEB;
			break;
		}
		case WEBSERVICE: {
			MSGSEQ_WEBSERVICE++;
			if (MSGSEQ_WEBSERVICE > MSGSEQ_WEBSERVICE_MAX) {
				MSGSEQ_WEBSERVICE = MSGSEQ_WEBSERVICE_MIN;
			}
			msgseq = MSGSEQ_WEBSERVICE;
			break;
		}
		case SOCKET: {
			MSGSEQ_SOCKET++;
			System.out.println(MSGSEQ_SOCKET);
			if (MSGSEQ_SOCKET > MSGSEQ_SOCKET_MAX) {
				MSGSEQ_SOCKET = MSGSEQ_SOCKET_MIN;
			}
			msgseq = MSGSEQ_SOCKET;
			break;
		}
		}
		return msgseq;
	}

	public static long getMsgid(int type) {
		Calendar calendar = Calendar.getInstance();
		long year = calendar.get(Calendar.YEAR);
		long month = calendar.get(Calendar.MONTH) + 1;
		long day = calendar.get(Calendar.DAY_OF_MONTH);
		long hour = calendar.get(Calendar.HOUR_OF_DAY);
		long minute = calendar.get(Calendar.MINUTE);
		long second = calendar.get(Calendar.SECOND);
		long millisecond = calendar.get(Calendar.MILLISECOND);
		year = year % 100;
//		System.out.println(year + "-" + month + "-" + day + " " + hour + ":"
//				+ minute + ":" + second + ":" + millisecond);
		long res = 0;
		res = (((((((year << 56) | (month << 52)) | (day << 46)) | (hour << 40)) | (minute << 34)) | (second << 28)) | (millisecond << 18))
				| getMsgidSeq(type);
		return res;
	}

	protected static String getMsgID(long l) {
		long yy = l, mm = l, dd = l, HH = l, MM = l, SS = l, ms = l, sq = l;
		yy >>>= 56;
		mm = mm & 0xF0000000000000L;
		mm >>>= 52;
		dd = dd & 0xFC00000000000L;
		dd >>>= 46;
		HH = HH & 0x3F0000000000L;
		HH >>>= 40;
		MM = MM & 0xFC00000000L;
		MM >>>= 34;
		SS = SS & 0x3F0000000L;
		SS >>>= 28;
		ms = ms & 0xFFC0000L;
		ms >>>= 18;
		sq = sq & 0x3FFFFL;
		return String.format(
				"%1$02d-%2$02d-%3$02d %4$02d:%5$02d:%6$02d:%7$03d %8$06d", yy,
				mm, dd, HH, MM, SS, ms, sq);
	}

	// public static long getMsgid(int type) {
	// Calendar calendar = Calendar.getInstance();
	// long month = calendar.get(Calendar.MONTH) + 1;
	// long day = calendar.get(Calendar.DAY_OF_MONTH);
	// long hour = calendar.get(Calendar.HOUR_OF_DAY);
	// long minute = calendar.get(Calendar.MINUTE);
	// long second = calendar.get(Calendar.SECOND);
	// long millisecond = calendar.get(Calendar.MILLISECOND);
	// long res = 0;
	// res = ((((((month << 60) | (day << 54)) | (hour << 48)) | (minute << 42))
	// | (second << 36)) | (millisecond << 26))
	// | getMsgidSeq(type);
	// return res;
	// }

	// public static String getMsgID(long l) {
	// long mm = l, dd = l, HH = l, MM = l, SS = l, ms = l, sq = l;
	// mm >>>= 60;
	// dd = dd & 0x7C0000000000000L;
	// dd >>>= 54;
	// HH = HH & 0x1F000000000000L;
	// HH >>>= 48;
	// MM = MM & 0xFC0000000000L;
	// MM >>>= 42;
	// SS = SS & 0x3F000000000L;
	// SS >>>= 36;
	// ms = ms & 0xFAC000000L;
	// ms >>>= 26;
	// sq = sq & 0x3FFFFFFL;
	// return String.format(
	// "%1$02d-%2$02d %3$02d:%4$02d:%5$02d %6$03d %7$08d", mm, dd, HH,
	// MM, SS, ms, sq);
	// }
}
