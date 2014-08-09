package com.sltunion.cloudy.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.persistent.mapper.TBrowserlogMapper;
import com.sltunion.cloudy.persistent.mapper.TCostlogMapper;
import com.sltunion.cloudy.persistent.mapper.TFilesystemlogMapper;
import com.sltunion.cloudy.persistent.mapper.THomepagelockdailyMapper;
import com.sltunion.cloudy.persistent.mapper.TIaudailylogMapper;
import com.sltunion.cloudy.persistent.model.TBrowserlog;
import com.sltunion.cloudy.persistent.model.TFilesystemlog;
import com.sltunion.cloudy.persistent.model.THomepagelockdaily;
import com.sltunion.cloudy.persistent.model.TIaudailylog;
import com.sltunion.cloudy.service.TargetService;

@Service("targetServiceService")
public class TargetServiceImpl implements TargetService {
	@Resource
	private TBrowserlogMapper tBrowserlogMapper;
	@Resource
	private TFilesystemlogMapper tFilesystemlogMapper;
	@Resource
	private THomepagelockdailyMapper tHomepagelockdailyMapper;
	@Resource
	private TIaudailylogMapper tIaudailylogMapper;
	@Resource
	private TCostlogMapper tCostlogMapper;

	@Override
	public int updateTBrowserlogCount(TBrowserlog tBrowserlog) {
		int count = tBrowserlogMapper.updateByStatisticsSelective(tBrowserlog);
		if(count==0){
			tBrowserlogMapper.insertSelective(tBrowserlog);
		}
		return count;
	}

	@Override
	public int updateTFilesystemlogCount(TFilesystemlog tFilesystemlog) {
		int count = tFilesystemlogMapper.updateByStatisticsSelective(tFilesystemlog);
		if(count==0){
			tFilesystemlogMapper.insertSelective(tFilesystemlog);
		}
		return count;
	}

	@Override
	public int updateTHomepagelockdailyCount(THomepagelockdaily tHomepagelockdaily) {
		int count = tHomepagelockdailyMapper.updateByStatisticsSelective(tHomepagelockdaily);
		if(count==0){
			tHomepagelockdailyMapper.insertSelective(tHomepagelockdaily);
		}
		return count;
	}

	@Override
	public int updateTIaudailyCount(TIaudailylog tIaudailylog) {
		int count = tIaudailylogMapper.updateByStatisticsSelective(tIaudailylog);
		if(count==0){
			tIaudailylogMapper.insertSelective(tIaudailylog);
		}
		return count;
	}

	@Override
	public int updateTCostlog(Map<String, Object> param) {
		int count = tCostlogMapper.insertByStatisticsSelective(param);
		return count;
	}
}
