package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TDriverMapper;
import com.sltunion.cloudy.persistent.mapper.TDriverchannelMapper;
import com.sltunion.cloudy.persistent.model.TDriver;
import com.sltunion.cloudy.persistent.model.TDriverchannel;
import com.sltunion.cloudy.service.DriverService;

@Service("driverService")
public class DriverServiceImpl implements DriverService {
	@Resource
	private TDriverMapper tDriverMapper;
	@Resource
	private TDriverchannelMapper tDriverchannelMapper;

	public void findPagerList(Pager pager) {
		long totalSize = tDriverMapper.countPager(pager.getParams());
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tDriverMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
		Map<String,Object> footer = tDriverMapper.selectFooter(pager.getParams());
		pager.setFooter(footer);
	}

	public void save(TDriver entity) {
		tDriverMapper.insertSelective(entity);
	}

	public void update(TDriver entity) {
		tDriverMapper.updateByPrimaryKeySelective(entity);
	}

	public void changeStatus(Long id, Byte status) {
		TDriver entity = new TDriver();
		entity.setId(id);
		entity.setStatus(status);
		tDriverMapper.updateByPrimaryKeySelective(entity);
	}

	public void delete(Long id) {
		TDriver entity = new TDriver();
		entity.setId(id);
		tDriverMapper.deleteByPrimaryKey(entity);
	}

	public void batchDel(String ids) {
		
	}

	public void assign(Long id, Long channelid, boolean checked) {
		TDriverchannel entity = new TDriverchannel();
		entity.setDriverid(id);
		entity.setChannelid(channelid);
		if(checked){
			tDriverchannelMapper.insertSelective(entity);
		}else{
			tDriverchannelMapper.deleteByPrimaryKey(entity);
		}
	}
}
