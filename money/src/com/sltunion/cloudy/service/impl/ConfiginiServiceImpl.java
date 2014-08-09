package com.sltunion.cloudy.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.persistent.mapper.TConfiginiMapper;
import com.sltunion.cloudy.persistent.model.TConfigini;
import com.sltunion.cloudy.service.ConfiginiService;

@Service("configiniService")
public class ConfiginiServiceImpl implements ConfiginiService {

	@Resource
	private TConfiginiMapper tConfiginiMapper;

	@Override
	public String pullconfigini(Long channelid,String debug, Integer logictype) {
		String result = "";
		Integer status = 1;
		if ("true".equalsIgnoreCase(debug)) {
			status = 0;
		}
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("channelid", channelid);
		param.put("status", status);
		param.put("logictype", logictype);
		TConfigini tConfigini = tConfiginiMapper.selectByPull(param);
		if (tConfigini != null) {
			result = tConfigini.getContent().trim();
		}
		return result;
	}
}