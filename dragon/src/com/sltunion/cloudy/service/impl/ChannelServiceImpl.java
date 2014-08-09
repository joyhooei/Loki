package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TChannelMapper;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.service.ChannelService;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService {
	@Resource
	private TChannelMapper tChannelMapper;

	public void findPagerList(Pager pager) {
		long totalSize = tChannelMapper.countPager(pager.getParams());
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tChannelMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
//		Map<String,Object> footer = tChannelMapper.selectFooter(pager.getParams());
//		pager.setFooter(footer);
	}

	public TChannel findById(Long id) {
		TChannel tChannel = new TChannel();
		tChannel.setId(id);
		return tChannelMapper.selectByPrimaryKey(tChannel);
	}
	
	public void save(TChannel entity) {
		tChannelMapper.insertSelective(entity);
	}

	public void update(TChannel entity) {
		tChannelMapper.updateByPrimaryKeySelective(entity);
	}

	public void updateUser(Long id, String username, Long userid) {
		TChannel tChannel = new TChannel();
		tChannel.setId(id);
		tChannel.setUsername(username);
		tChannel.setUserid(userid);
		tChannelMapper.updateByPrimaryKeySelective(tChannel);
	}

	public void changeStatus(Long id, Integer status) {
		TChannel tChannel = new TChannel();
		tChannel.setId(id);
		tChannel.setStatus(status);
		tChannelMapper.updateByPrimaryKeySelective(tChannel);
		
	}

	public void delete(Long id) {
		TChannel tChannel = new TChannel();
		tChannel.setId(id);
		tChannelMapper.deleteByPrimaryKey(tChannel);
	}

	public void batchDel(String ids) {
	}

	public List<TChannel> findAll() {
		return tChannelMapper.selectAll();
	}

	public List<TChannel> findListByUserid(Long userid) {
		return tChannelMapper.selectChannelListByUserid(userid);
	}

	@Override
	public List<TChannel> idleChannel(String name) {
		return tChannelMapper.selectIdleChannelListByTablename(name);
	}
}
