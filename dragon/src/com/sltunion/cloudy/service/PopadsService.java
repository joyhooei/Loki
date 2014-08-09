package com.sltunion.cloudy.service;

import com.sltunion.cloudy.persistent.model.TPopads;


public interface PopadsService extends PagerService {

	void save(TPopads entity);

	void update(TPopads entity);

	void changeStatus(Long id, Byte status);

	void delete(Long id);

	void batchDel(String ids);

	void assign(Long id, Long channelid, boolean checked);
}
