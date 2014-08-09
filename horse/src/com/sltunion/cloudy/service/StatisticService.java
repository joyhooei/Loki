package com.sltunion.cloudy.service;




public interface StatisticService {
	long findSourceid(String sourcetable, String targettable);
	
	void updateSourceid(String sourcetable, String targettable, long sourceid);

	int findChannelInstallNum(Long channelid);

	void updateInstallNum(Long channelid, Long userid, int installnum);
	
	long countExist(String existval,String table);

	void saveExist(String existval,String table);

	long countDailyExist(String existval, String createdate,String table);

	void saveDailyExist(String existval, String createdate,String table);
}
