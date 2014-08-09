package com.sltunion.cloudy.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.sltunion.cloudy.common.utils.MD5Util;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TUser;
import com.sltunion.cloudy.service.LoginService;

@Scope("prototype")
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = -4562817216101948705L;

	@Resource
	protected LoginService loginService;

	private String username; // 账号名称
	private String password; // 密码

	public String login() {
		try {
			TUser tUser = loginService.findByUsername(username);
			if (ObjectUtil.isNotEmpty(tUser)) {
				if (MD5Util.compare(tUser.getPassword(), password)) {
					addUser(tUser);
					return SUCCESS;
				} else {
					addResultMap("message", "密码错误<br/>");
					return FAIL;
				}
			} else {
				 addResultMap("message", "用户不存在<br/>");
				return FAIL;
			}
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "登录失败，请联系系统管理员！<br/>");
			return FAIL;
		}
	}

	public String index() {
		return SUCCESS;
	}

	public String loginout() {
		try {
			removeUser();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
