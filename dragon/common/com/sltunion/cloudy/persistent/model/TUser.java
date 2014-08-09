package com.sltunion.cloudy.persistent.model;

import java.util.List;

/**
 * @author sundial
 * @date 2014-02-07 11:09:24
 */
public class TUser { 
	private Long id;//id 
	
	private String username;//username 
	
	private String password;//password 
	
	private Byte enable;//enable 
	
	private Byte usertype;//usertype 
	
	private Long roleid;//roleid 
	
	private String createtime;//createtime 
	
	private List<TModule> moduleList;
	
	private String rolename;
	
	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public String getUsername(){
		return this.username;
	}
	public void  setUsername(String username){
		this.username=username;
	}

	public String getPassword(){
		return this.password;
	}
	public void  setPassword(String password){
		this.password=password;
	}

	public Byte getEnable(){
		return this.enable;
	}
	public void  setEnable(Byte enable){
		this.enable=enable;
	}

	public Byte getUsertype(){
		return this.usertype;
	}
	public void  setUsertype(Byte usertype){
		this.usertype=usertype;
	}

	public Long getRoleid(){
		return this.roleid;
	}
	public void  setRoleid(Long roleid){
		this.roleid=roleid;
	}

	public String getCreatetime(){
		return this.createtime;
	}
	public void  setCreatetime(String createtime){
		this.createtime=createtime;
	}

	public List<TModule> getModuleList() {
		return moduleList;
	}
	public void setModuleList(List<TModule> moduleList) {
		this.moduleList = moduleList;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TUser").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("username").append(" = ").append(username).append(",");
		toString.append("password").append(" = ").append(password).append(",");
		toString.append("enable").append(" = ").append(enable).append(",");
		toString.append("usertype").append(" = ").append(usertype).append(",");
		toString.append("roleid").append(" = ").append(roleid).append(",");
		toString.append("createtime").append(" = ").append(createtime).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}