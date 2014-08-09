package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TIaudailylogMapper;
import com.sltunion.cloudy.service.IaudailylogService;

@Service("iaudailylogService")
public class IaudailylogServiceImpl implements IaudailylogService {
	@Resource
	private TIaudailylogMapper tIaudailylogMapper;

	public void findPagerList(Pager pager) {
		long totalSize = tIaudailylogMapper.countPager(pager.getParams());
		pager.setPageSize(totalSize);
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tIaudailylogMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
		Map<String,Object> footer = tIaudailylogMapper.selectFooter(pager.getParams());
		pager.setFooter(footer);
	}

	public List<Map<String, Object>> getChartResultList(Map<String, Object> params) {
		return tIaudailylogMapper.getChartResultList(params);
	}
}
