package com.sltunion.cloudy.service;

import com.sltunion.cloudy.persistent.model.TUser;


public interface LoginService {

	TUser findByUsername(String username);

}
