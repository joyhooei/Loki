package com.sltunion.cloudy.web.action.manage;

import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.common.utils.MD5Util;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TRole;
import com.sltunion.cloudy.persistent.model.TUser;
import com.sltunion.cloudy.service.RoleService;
import com.sltunion.cloudy.service.UserService;
import com.sltunion.cloudy.web.action.PagerAction;

public class UserAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3982560325067038240L;

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	private Long id; //
	private String username; // 用户名
	private String password; // 密码
	private Byte enable; // 用户状态 0 禁用 1 可用
	private Byte usertype; // 用户类型 0 普通用户 1 管理员
	private Long roleid;
	private String createtime; // 创建时间 yyyy-MM-dd HH:mm:ss

	private String ids;
	private String newpassword;
	private String confirmpassword;
	private List<TRole> roleList;

	public String index() {
		try {
			setPager();
			pager.addParams("username", username);
			pager.addParams("usertype", usertype);
			pager.addParams("roleid", roleid);
			userService.findPagerList(pager);
			roleList = roleService.selectAll();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}
	
	public String changePassword() {
		return SUCCESS;
	}

	public String save() {
		try {
			createtime = DateUtils.getDatetime();
			password = MD5Util.md5(password);
			TUser tUser = formToBean(this, TUser.class);
			userService.save(tUser);
			addResultMap("message", "新增用户["+username+"]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "新增用户["+username+"]失败");
		}
		return SUCCESS;
	}

	public String update() {
		try {
			TUser tUser = formToBean(this, TUser.class);
			userService.update(tUser);
			addResultMap("message", "保存用户["+username+"]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "保存用户["+username+"]失败");
		}
		return SUCCESS;
	}

	public String updatepwd() {
		try {
			TUser tUser = getUser();
			if (ObjectUtil.isNotEmpty(password)) {
				if (ObjectUtil.isNotEmpty(newpassword)) {
					if (newpassword.equals(confirmpassword)) {
						password = MD5Util.md5(password);
						if (password.equals(tUser.getPassword())) {
							password = MD5Util.md5(newpassword);
							userService.updatePassword(tUser.getId(), password);
							tUser.setPassword(password);
							addUser(tUser);
							addResultMap("message", "修改密码成功！<br/>");
							addResultMap("result", 1);
						} else {
							addResultMap("message", "修改密码失败：原密码不正确！<br/>");
						}
					} else {
						addResultMap("message", "修改密码失败：新密码和确认密码不一致！<br/>");
					}
				} else {
					addResultMap("message", "修改密码失败：新密码为空！<br/>");
				}
			} else {
				addResultMap("message", "修改密码失败：原密码为空！<br/>");
			}
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "修改密码失败，请联系管理员！<br/>");
		}
		return SUCCESS;
	}
	
	public String enableUser() {
		try {
			if(enable==0){
				enable = Const.User.ENABLE;
			}else{
				enable = Const.User.DISABLE;
			}
			userService.changeEnable(id, enable);
			addResultMap("message", "修改用户["+username+"]状态成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "修改用户["+username+"]状态失败");
		}
		return SUCCESS;
	}

	public String disableUser() {
		try {
			userService.changeEnable(id, Const.User.DISABLE);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String resetPassword() {
		try {
			password = MD5Util.md5("654321");
			userService.updatePassword(id, password);
			addResultMap("message", "重置用户["+username+"]密码[654321]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "重置用户["+username+"]密码失败");
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			userService.delete(id);
			addResultMap("message", "删除用户["+username+"]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "删除用户["+username+"]失败");
		}
		return SUCCESS;
	}

	public String batchDel() {
		try {
			if (ObjectUtil.isNotEmpty(ids)) {
				userService.batchDel(ids);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte getEnable() {
		return enable;
	}

	public void setEnable(Byte enable) {
		this.enable = enable;
	}

	public Byte getUsertype() {
		return usertype;
	}

	public void setUsertype(Byte usertype) {
		this.usertype = usertype;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public List<TRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<TRole> roleList) {
		this.roleList = roleList;
	}
}