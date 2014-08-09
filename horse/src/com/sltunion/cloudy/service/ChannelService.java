package com.sltunion.cloudy.service;

import java.util.List;

import com.sltunion.cloudy.persistent.model.TChannel;

public interface ChannelService{
	List<TChannel> findAll();
}
