package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TRoleMapper;
import com.sltunion.cloudy.persistent.mapper.TRolemoduleMapper;
import com.sltunion.cloudy.persistent.model.TRole;
import com.sltunion.cloudy.persistent.model.TRolemodule;
import com.sltunion.cloudy.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private TRoleMapper roleMapper;
	@Resource
	private TRolemoduleMapper tRolemoduleMapper;

	public void findPagerList(Pager pager) {
		long totalSize = roleMapper.countPager(pager.getParams());
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = roleMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
//		Map<String,Object> footer = tModuleMapper.selectFooter(pager.getParams());
//		pager.setFooter(footer);
	}

	public TRole findById(Long id) {
		TRole tModule = new TRole();
		tModule.setId(id);
		return roleMapper.selectByPrimaryKey(tModule);
	}
	
	public void save(TRole entity) {
		roleMapper.insertSelective(entity);
	}

	public void update(TRole entity) {
		roleMapper.updateByPrimaryKeySelective(entity);
	}

	public void delete(Long id) {
		TRole tModule = new TRole();
		tModule.setId(id);
		roleMapper.deleteByPrimaryKey(tModule);
	}

	public void batchDel(String ids) {
	}

	public List<TRole> selectAll() {
		return roleMapper.selectAll();
	}

	public void assign(Long id, Long moduleid, boolean checked) {
		TRolemodule entity = new TRolemodule();
		entity.setModuleid(moduleid);
		entity.setRoleid(id);
		if(checked){
			tRolemoduleMapper.insertSelective(entity);
		}else{
			tRolemoduleMapper.deleteByPrimaryKey(entity);
		}
	}
}
