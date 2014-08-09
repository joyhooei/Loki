package com.sltunion.cloudy.service;

import com.sltunion.cloudy.persistent.model.THomepagelock;


public interface HomepagelockService extends PagerService {

	void save(THomepagelock entity);

	void update(THomepagelock entity);

	void changeStatus(Long id, Byte status);

	void delete(Long id);

	void batchDel(String ids);

	void assign(Long id, Long channelid, boolean checked);
}
