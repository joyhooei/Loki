package com.sltunion.cloudy.persistent.model;

import java.util.List;

/**
 * @author sundial
 * @date 2014-02-08 16:00:16
 */
public class TRole {
	private Long id;// id

	private String rolename;// rolename

	private String remark;// remark

	private Byte manage;// manage

	private List<TRolemodule> rolemoduleList;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getManage() {
		return this.manage;
	}

	public void setManage(Byte manage) {
		this.manage = manage;
	}

	public List<TRolemodule> getRolemoduleList() {
		return rolemoduleList;
	}

	public void setRolemoduleList(List<TRolemodule> rolemoduleList) {
		this.rolemoduleList = rolemoduleList;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TRole").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("rolename").append(" = ").append(rolename).append(",");
		toString.append("remark").append(" = ").append(remark).append(",");
		toString.append("manage").append(" = ").append(manage).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}