package com.sltunion.cloudy.web.action.manage.ajax;

import javax.annotation.Resource;

import com.sltunion.cloudy.common.utils.MD5Util;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TUser;
import com.sltunion.cloudy.service.UserService;
import com.sltunion.cloudy.web.action.BaseAction;

public class UserAjaxAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4035835387349545374L;

	@Resource
	private UserService userService;

	private String name; // 账号名称
	private String password; // 密码
	private String newpassword; // 密码
	private String confirmpassword; // 密码
	private String msg; // 消息
	private String status; // 状态
	private boolean flag;

	public String checkpwd() {
		try {
			TUser tUser = getUser();
			password = MD5Util.md5(password);
			if (ObjectUtil.isNotEmpty(password) && password.equals(tUser.getPassword())) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error("UserAjaxAction", e);
		}
		return SUCCESS;
	}

	public String updatepwd() {
		try {
			TUser tUser = getUser();
			if (ObjectUtil.isNotEmpty(password)) {
				if (ObjectUtil.isNotEmpty(newpassword)) {
					password = MD5Util.md5(password);
					if (password.equals(tUser.getPassword())) {
						password = MD5Util.md5(newpassword);
						userService.updatePassword(tUser.getId(), password);
						tUser.setPassword(password);
						addUser(tUser);
						addResultMap("message", "修改密码成功");
						addResultMap("result", 1);
					} else {
						addResultMap("message", "修改密码失败：原密码不正确");
						addResultMap("result", 0);
					}
				} else {
					addResultMap("message", "修改密码失败：新密码为空");
					addResultMap("result", 0);
				}
			} else {
				addResultMap("message", "修改密码失败：原密码为空");
				addResultMap("result", 0);
			}
		} catch (Exception e) {
			logger.error("UserAjaxAction", e);
			addResultMap("message", "修改密码失败，请联系管理员");
			addResultMap("result", 0);
		}
		return SUCCESS;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getNewpassword() {
		return newpassword;
	}



	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}



	public String getConfirmpassword() {
		return confirmpassword;
	}



	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}