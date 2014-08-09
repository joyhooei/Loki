package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-15 22:15:27
 */
public class TRolemodule { 
	private Long roleid;//roleid 
	
	private Long moduleid;//moduleid 
	
	public Long getRoleid(){
		return this.roleid;
	}
	public void  setRoleid(Long roleid){
		this.roleid=roleid;
	}

	public Long getModuleid(){
		return this.moduleid;
	}
	public void  setModuleid(Long moduleid){
		this.moduleid=moduleid;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TRolemodule").append("{");
		toString.append("roleid").append(" = ").append(roleid).append(",");
		toString.append("moduleid").append(" = ").append(moduleid).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}