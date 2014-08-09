package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TConfigchannelMapper;
import com.sltunion.cloudy.persistent.mapper.TConfiginiMapper;
import com.sltunion.cloudy.persistent.model.TConfigchannel;
import com.sltunion.cloudy.persistent.model.TConfigini;
import com.sltunion.cloudy.service.ConfiginiService;

@Service("configiniService")
public class ConfiginiServiceImpl implements ConfiginiService {
	@Resource
	private TConfiginiMapper tConfiginiMapper;
	@Resource
	private TConfigchannelMapper tConfigchannelMapper;

	public void findPagerList(Pager pager) {
		long totalSize = tConfiginiMapper.countPager(pager.getParams());
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tConfiginiMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
		Map<String,Object> footer = tConfiginiMapper.selectFooter(pager.getParams());
		pager.setFooter(footer);
	}

	public void save(TConfigini entity) {
		tConfiginiMapper.insertSelective(entity);
	}

	public void update(TConfigini entity) {
		tConfiginiMapper.updateByPrimaryKeySelective(entity);
	}

	public void changeStatus(Long id, Byte status) {
		TConfigini entity = new TConfigini();
		entity.setId(id);
		entity.setStatus(status);
		tConfiginiMapper.updateByPrimaryKeySelective(entity);
	}

	public void delete(Long id) {
		TConfigini entity = new TConfigini();
		entity.setId(id);
		tConfiginiMapper.deleteByPrimaryKey(entity);
	}

	public void batchDel(String ids) {
		
	}

	public void assign(Long id, Long channelid, boolean checked) {
		TConfigchannel entity = new TConfigchannel();
		entity.setConfigid(id);
		entity.setChannelid(channelid);
		if(checked){
			tConfigchannelMapper.insertSelective(entity);
		}else{
			tConfigchannelMapper.deleteByPrimaryKey(entity);
		}
	}
}
