package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TBrowserlogMapper;
import com.sltunion.cloudy.service.BrowserlogService;

@Service("browserlogService")
public class BrowserlogServiceImpl implements BrowserlogService {
	@Resource
	private TBrowserlogMapper tBrowserlogMapper;

	public void findPagerList(Pager pager) {
		long totalSize = tBrowserlogMapper.countPager(pager.getParams());
		pager.setPageSize(totalSize);
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tBrowserlogMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
		Map<String,Object> footer = tBrowserlogMapper.selectFooter(pager.getParams());
		pager.setFooter(footer);
	}

	public List<Map<String, Object>> getChartResultList(Map<String, Object> params) {
		return tBrowserlogMapper.getChartResultList(params);
	}
}
