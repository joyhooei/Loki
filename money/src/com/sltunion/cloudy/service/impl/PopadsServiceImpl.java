package com.sltunion.cloudy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.persistent.mapper.TPopadsMapper;
import com.sltunion.cloudy.persistent.model.TPopads;
import com.sltunion.cloudy.service.PopadsService;
import com.sltunion.cloudy.vo.json.Popads;

@Service("popadsService")
public class PopadsServiceImpl implements PopadsService {

	@Resource
	private TPopadsMapper tPopadsMapper;

	public List<Popads> pullPopads(String debug, Long channelid) {
		Integer status = 1;
		if ("true".equalsIgnoreCase(debug)) {
			status = 0;
		}
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("channelid", channelid);
		param.put("status", status);
		List<TPopads> tPopadsList = tPopadsMapper.selectByPull(param);
		List<Popads> popadsList = new ArrayList<Popads>();
		if (tPopadsList != null) {
			for (TPopads tPopads : tPopadsList) {
				Popads popads = new Popads();
				popads.setAdhigh(tPopads.getAdhigh());
				popads.setAdwidth(tPopads.getAdwidth());
				popads.setAdurl(tPopads.getUrl());
				popads.setStarttime(tPopads.getStarttime());
				popads.setNextstarttime(tPopads.getNextstarttime());
				popads.setDisplayorder(tPopads.getDisplayorder());
				popads.setDisplaytime(tPopads.getDisplaytime());
				popadsList.add(popads);
			}
		}
		return popadsList;
	}
}