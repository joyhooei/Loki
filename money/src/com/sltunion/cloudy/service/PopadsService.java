package com.sltunion.cloudy.service;

import java.util.List;

import com.sltunion.cloudy.vo.json.Popads;

public interface PopadsService {
	public List<Popads> pullPopads(String debug, Long channelid);
}
