package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TModuleMapper;
import com.sltunion.cloudy.persistent.model.TModule;
import com.sltunion.cloudy.service.ModuleService;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
	@Resource
	private TModuleMapper tModuleMapper;

	public void findPagerList(Pager pager) {
//		long totalSize = tModuleMapper.countPager(pager.getParams());
//		pager.setTotalSize(totalSize);
//		pager.addParams("startIndex", null);
//		pager.addParams("pageSize", null);
		List<Map<String,Object>> pageList = tModuleMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
//		Map<String,Object> footer = tModuleMapper.selectFooter(pager.getParams());
//		pager.setFooter(footer);
	}

	public TModule findById(Long id) {
		TModule tModule = new TModule();
		tModule.setId(id);
		return tModuleMapper.selectByPrimaryKey(tModule);
	}
	
	public void save(TModule entity) {
		tModuleMapper.insertSelective(entity);
	}

	public void update(TModule entity) {
		tModuleMapper.updateByPrimaryKeySelective(entity);
	}

	public void delete(Long id) {
		TModule tModule = new TModule();
		tModule.setId(id);
		tModuleMapper.deleteByPrimaryKey(tModule);
	}

	public void batchDel(String ids) {
	}

	public List<TModule> selectAll() {
		return tModuleMapper.selectAll();
	}

	public List<TModule> findListByPid(long pid) {
		return tModuleMapper.selectListByPid(pid);
	}

	public List<TModule> findListByParams(Map<String, Object> params) {
		return tModuleMapper.selectListByParams(params);
	}
}
