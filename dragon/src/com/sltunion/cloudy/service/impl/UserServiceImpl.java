package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TModuleMapper;
import com.sltunion.cloudy.persistent.mapper.TUserMapper;
import com.sltunion.cloudy.persistent.model.TModule;
import com.sltunion.cloudy.persistent.model.TUser;
import com.sltunion.cloudy.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private TUserMapper tUserMapper;
	@Resource
	private TModuleMapper tModuleMapper;

	public TUser findUsername(String username) {
		TUser tUser = tUserMapper.selectUserByUsername(username);
		if (tUser != null) {
			Long roleid = tUser.getRoleid();
			List<TModule> tModuleList = tModuleMapper.selectModuleByRoleid(roleid);
			tUser.setModuleList(tModuleList);
		}
		return tUser;
	}

	public void findPagerList(Pager pager) {
		long totalSize = tUserMapper.countPager(pager.getParams());
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tUserMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
//		Map<String,Object> footer = tUserMapper.selectFooter(pager.getParams());
//		pager.setFooter(footer);
	}

	public TUser findById(Long id) {
		TUser tUser = new TUser();
		tUser.setId(id);
		return tUserMapper.selectByPrimaryKey(tUser);
	}

	public void save(TUser tUser) {
		tUserMapper.insertSelective(tUser);
	}

	public void update(TUser tUser) {
		tUserMapper.updateByPrimaryKeySelective(tUser);
	}

	public void updatePassword(Long id, String password) {
		TUser tUser = new TUser();
		tUser.setId(id);
		tUser.setPassword(password);
		tUserMapper.updateByPrimaryKeySelective(tUser);
	}

	public void changeEnable(Long id, Byte enable) {
		TUser tUser = new TUser();
		tUser.setId(id);
		tUser.setEnable(enable);
		tUserMapper.updateByPrimaryKeySelective(tUser);
	}

	public void delete(Long id) {
		TUser tUser = new TUser();
		tUser.setId(id);
		tUserMapper.deleteByPrimaryKey(tUser);
	}

	public void batchDel(String ids) {
	}

	public List<TUser> findAll() {
		return tUserMapper.selectAll();
	}
}
