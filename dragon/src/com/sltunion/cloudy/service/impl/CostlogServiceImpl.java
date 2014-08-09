package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TCostlogMapper;
import com.sltunion.cloudy.persistent.model.TCostlog;
import com.sltunion.cloudy.service.CostlogService;

@Service("costlogService")
public class CostlogServiceImpl implements CostlogService {
	@Resource
	private TCostlogMapper tCostlogMapper;

	public void findPagerList(Pager pager) {
		long totalSize = tCostlogMapper.countPager(pager.getParams());
		pager.setPageSize(totalSize);
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tCostlogMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
		Map<String,Object> footer = tCostlogMapper.selectFooter(pager.getParams());
		pager.setFooter(footer);
	}

	@Override
	public void changeStatus(Long id, Byte status) {
		TCostlog tCostlog = new TCostlog();
		tCostlog.setId(id);
		tCostlog.setStatus(status);
		tCostlogMapper.updateByPrimaryKeySelective(tCostlog);
	}
}
