package com.sltunion.cloudy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.persistent.mapper.TChannelinstallMapper;
import com.sltunion.cloudy.persistent.mapper.TDailyexistMapper;
import com.sltunion.cloudy.persistent.mapper.TExistMapper;
import com.sltunion.cloudy.persistent.mapper.TStatisticsMapper;
import com.sltunion.cloudy.persistent.model.TChannelinstall;
import com.sltunion.cloudy.persistent.model.TDailyexist;
import com.sltunion.cloudy.persistent.model.TExist;
import com.sltunion.cloudy.persistent.model.TStatistics;
import com.sltunion.cloudy.service.StatisticService;

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService {
	@Resource
	private TDailyexistMapper tDailyexistMapper;
	@Resource
	private TExistMapper tExistMapper;
	@Resource
	private TChannelinstallMapper tChannelinstallMapper;
	@Resource
	private TStatisticsMapper tStatisticsMapper;

	@Override
	public long countDailyExist(String existval, String createdate,String table) {
		TDailyexist entity = new TDailyexist();
		entity.setExistval(existval);
		entity.setCreatedate(createdate);
		entity.setTable(table);
		entity = tDailyexistMapper.selectByPrimaryKey(entity);
		if(entity!=null){
			return 1;
		}
		return 0;
	}
	
	@Override
	public void saveDailyExist(String existval, String createdate,String table) {
		TDailyexist entity = new TDailyexist();
		entity.setExistval(existval);
		entity.setCreatedate(createdate);
		entity.setTable(table);
		tDailyexistMapper.insertSelective(entity);
	}

	@Override
	public long countExist(String existval,String table) {
		TExist entity = new TExist();
		entity.setExistval(existval);
		entity.setTable(table);
		entity = tExistMapper.selectByPrimaryKey(entity);
		if(entity!=null){
			return 1;
		}
		return 0;
	}
	
	@Override
	public void saveExist(String existval,String table) {
		TExist entity = new TExist();
		entity.setExistval(existval);
		entity.setTable(table);
		tExistMapper.insertSelective(entity);
	}

	@Override
	public int findChannelInstallNum(Long channelid) {
		TChannelinstall entity = new TChannelinstall();
		entity.setChannelid(channelid);
		entity = tChannelinstallMapper.selectByPrimaryKey(entity);
		if(entity!=null){
			return entity.getInstallnum();
		}
		return 0;
	}

	@Override
	public void updateInstallNum(Long channelid,Long userid, int installnum) {
		TChannelinstall entity = new TChannelinstall();
		entity.setChannelid(channelid);
		entity.setUserid(userid);
		entity.setInstallnum(installnum);
		int count = tChannelinstallMapper.updateByPrimaryKeySelective(entity);
		if(count==0){
			tChannelinstallMapper.insertSelective(entity);
		}
	}
	
	@Override
	public long findSourceid(String sourcetable, String targettable) {
		TStatistics entity = new TStatistics();
		entity.setSourcetable(sourcetable);
		entity.setTargettable(targettable);
		entity = tStatisticsMapper.selectByTable(entity);
		if(entity!=null){
			return entity.getSourceid();
		}
		return 0;
	}

	@Override
	public void updateSourceid(String sourcetable, String targettable, long sourceid) {
		TStatistics entity = new TStatistics();
		entity.setSourcetable(sourcetable);
		entity.setTargettable(targettable);
		entity.setSourceid(sourceid);
		int count = tStatisticsMapper.updateByTable(entity);
		if(count==0){
			tStatisticsMapper.insertSelective(entity);
		}
	}
}
