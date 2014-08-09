package com.sltunion.cloudy.service;

import java.util.List;

import com.sltunion.cloudy.persistent.model.TChannel;

public interface ChannelService extends PagerService {

	TChannel findById(Long id);

	void save(TChannel entity);

	void update(TChannel entity);

	void updateUser(Long id, String username,Long userid);

	void changeStatus(Long id, Integer status);

	void delete(Long id);

	void batchDel(String ids);

	List<TChannel> findAll();

	List<TChannel> findListByUserid(Long userid);

	List<TChannel> idleChannel(String name);
}
