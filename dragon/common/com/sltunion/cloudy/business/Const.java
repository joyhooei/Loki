package com.sltunion.cloudy.business;

import java.util.Random;

public class Const {

	public static Random random = new Random();

	public class Configini {
		/**
		 * 1 推荐配置
		 */
		public static final int LOGICTYPE_RECOMM = 1;
		/**
		 * 2 迷你页配置
		 */
		public static final int LOGICTYPE_MINI = 2;
		/**
		 * 3 开关配置
		 */
		public static final int LOGICTYPE_SWITCH = 3;
	}
	
	public class User {
		/**
		 * 1 可用
		 */
		public static final byte ENABLE = 1;
		/**
		 * 0 禁用
		 */
		public static final byte DISABLE = 0;
	}
	
	public class Status {
		/**
		 * 0 测试
		 */
		public static final byte TEST = 0;
		/**
		 * 1 可用
		 */
		public static final byte ENABLE = 1;
		/**
		 * 2 禁用
		 */
		public static final byte DISABLE = 2;
	}
	
	public class Module {
		/**
		 * 0 顶级模块
		 */
		public static final long TOP = 0;
	}
	
	public class Statistics{
		public static final int LIMIT = 1000;
	}
	
	public class Table{
		public static final String T_INSTALLLOG = "t_installlog";
		public static final String T_ACTIVELOG = "t_activelog";
		public static final String T_UNINSTALLLOG = "t_uninstalllog";
		public static final String T_DRIVERDOWN = "t_driverdownlog";
		public static final String T_HOMEPAGELOCKLOG = "t_homepagelocklog";
		
		public static final String T_FILESYSTEMLOG = "t_filesystemlog";
		public static final String T_BROWSERLOG = "t_browserlog";
		public static final String T_HOMEPAGELOCKDAILY = "t_homepagelockdaily";
		public static final String T_IAUDAILYLOG = "t_iaudailylog";
		
		public static final String T_STATISTICS = "t_statistics";
		public static final String T_CHANNELINSTALL = "t_channelinstall";
		
		public static final String T_INSTALLEXIST = "t_installexist";   
		public static final String T_UNINSTALLEXIST = "t_uninstallexist";
		public static final String T_ACTIVEEXIST = "t_activeexist";
		public static final String T_DRIVERDOWNXIST = "t_driverdownxist";
		public static final String T_HOMEPAGELOCKEXIST = "t_homepagelockexist";
	}
}