package com.sltunion.cloudy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.persistent.mapper.TChannelMapper;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.service.ChannelService;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService {
	@Resource
	private TChannelMapper tChannelMapper;

	public List<TChannel> findAll() {
		return tChannelMapper.selectAll();
	}
}
