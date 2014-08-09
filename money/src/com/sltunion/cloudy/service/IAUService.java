package com.sltunion.cloudy.service;



/**
 * 
 * @author sundial
 *
 */
public interface IAUService {
	public int iauReport(Long channelid, String mac, String ip, String op, String cn, Integer st,
			Integer sver, Integer is64, Integer fat,String sn,Integer iv, Integer hour, String createdate, String createtime);

	public int install(Long channelid, String mac, String ip, Integer sv, String disknum);
}
