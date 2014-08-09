package com.sltunion.cloudy.service;

import com.sltunion.cloudy.persistent.model.TDriver;


public interface DriverService extends PagerService {

	void save(TDriver entity);

	void update(TDriver entity);

	void changeStatus(Long id, Byte status);

	void delete(Long id);

	void batchDel(String ids);

	void assign(Long id, Long channelid, boolean checked);
}
