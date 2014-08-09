package com.sltunion.cloudy.service;

import com.sltunion.cloudy.persistent.model.TConfigini;


public interface ConfiginiService extends PagerService {

	void save(TConfigini entity);

	void update(TConfigini entity);

	void changeStatus(Long id, Byte status);

	void delete(Long id);

	void batchDel(String ids);

	void assign(Long id, Long channelid, boolean checked);
}
