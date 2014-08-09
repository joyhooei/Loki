package com.sltunion.cloudy.service;

import java.util.List;
import java.util.Map;

import com.sltunion.cloudy.persistent.model.TModule;

public interface ModuleService extends PagerService {

	TModule findById(Long id);

	void save(TModule entity);

	void update(TModule entity);

	void delete(Long id);

	void batchDel(String ids);

	List<TModule> selectAll();

	List<TModule> findListByPid(long pid);

	List<TModule> findListByParams(Map<String, Object> params);
}
