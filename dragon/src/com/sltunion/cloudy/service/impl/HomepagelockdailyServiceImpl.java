package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.THomepagelockdailyMapper;
import com.sltunion.cloudy.service.HomepagelockdailyService;

@Service("homepagelockdailyService")
public class HomepagelockdailyServiceImpl implements HomepagelockdailyService {
	@Resource
	private THomepagelockdailyMapper tHomepagelockdailyMapper;

	public void findPagerList(Pager pager) {
		long totalSize = tHomepagelockdailyMapper.countPager(pager.getParams());
		pager.setPageSize(totalSize);
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tHomepagelockdailyMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
		Map<String,Object> footer = tHomepagelockdailyMapper.selectFooter(pager.getParams());
		pager.setFooter(footer);
	}

	public List<Map<String, Object>> getChartResultList(Map<String, Object> params) {
		return tHomepagelockdailyMapper.getChartResultList(params);
	}
}
