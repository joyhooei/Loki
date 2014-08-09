package com.sltunion.cloudy.service;

import java.util.List;

import com.sltunion.cloudy.persistent.model.TRole;

public interface RoleService extends PagerService {

	TRole findById(Long id);

	void save(TRole entity);

	void update(TRole entity);

	void delete(Long id);

	void batchDel(String ids);

	List<TRole> selectAll();

	void assign(Long id, Long moduleid, boolean checked);
}
