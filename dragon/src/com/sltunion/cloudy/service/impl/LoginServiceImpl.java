package com.sltunion.cloudy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.persistent.mapper.TModuleMapper;
import com.sltunion.cloudy.persistent.mapper.TUserMapper;
import com.sltunion.cloudy.persistent.model.TModule;
import com.sltunion.cloudy.persistent.model.TUser;
import com.sltunion.cloudy.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource
	private TUserMapper tUserMapper;
	@Resource
	private TModuleMapper tModuleMapper;

	public TUser findByUsername(String username) {
		TUser tUser = tUserMapper.selectUserByUsername(username);
		if (tUser != null && tUser.getEnable()==1) {
			Long roleid = tUser.getRoleid();
			List<TModule> tModuleList = tModuleMapper.selectModuleByRoleid(roleid);
			tUser.setModuleList(tModuleList);
		}else{
			tUser = null;
		}
		return tUser;
	}
}
