package com.sltunion.cloudy.service;


public interface DriverDownService {
	public String pullDriver(Long channelid, String dn, String ma, String ip, Integer i3,
			String db, String id, String sn, Integer sv, String debug);

	public String pullMultiXml(Long channelid, String dn, String ma, String ip, Integer i3,
			String db, String id, String sn, Integer sv, String debug);

	int pullDown(Long channelid, String dn, String ma, String ip, String debug);
}
