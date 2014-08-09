package com.sltunion.cloudy.service;

import java.util.List;

import com.sltunion.cloudy.persistent.model.TUser;

public interface UserService extends PagerService {

	public List<TUser> findAll();

	TUser findById(Long id);

	void save(TUser entity);

	void update(TUser entity);

	void updatePassword(Long id, String password);

	void changeEnable(Long id, Byte enable);

	void delete(Long id);

	void batchDel(String ids);
}
