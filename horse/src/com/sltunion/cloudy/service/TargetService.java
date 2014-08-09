package com.sltunion.cloudy.service;

import java.util.Map;

import com.sltunion.cloudy.persistent.model.TBrowserlog;
import com.sltunion.cloudy.persistent.model.TFilesystemlog;
import com.sltunion.cloudy.persistent.model.THomepagelockdaily;
import com.sltunion.cloudy.persistent.model.TIaudailylog;


public interface TargetService {

	int updateTIaudailyCount(TIaudailylog tIaudailylog);

	int updateTFilesystemlogCount(TFilesystemlog tFilesystemlog);

	int updateTHomepagelockdailyCount(THomepagelockdaily tHomepagelockdaily);

	int updateTBrowserlogCount(TBrowserlog tBrowserlog);

	int updateTCostlog(Map<String, Object> param);
}
