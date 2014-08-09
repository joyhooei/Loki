package com.sltunion.cloudy.persistent.model;

import java.util.List;

/**
 * @author sundial
 * @date 2014-03-04 01:55:44
 */
public class TDriver { 
	private Long id;//id 
	
	private Integer version;//version 
	
	private String updatehost;//updatehost 
	
	private String updateip;//updateip 
	
	private String updateip2;//updateip2 
	
	private Integer updateport;
	
	private String sysurl;//sysurl 
	
	private String dllurl;//dllurl 
	
	private Byte status;//status 
	
	private String createtime;//createtime 
	
	private List<TDriverchannel> driverchannelList;
	
	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public Integer getVersion(){
		return this.version;
	}
	public void  setVersion(Integer version){
		this.version=version;
	}

	public String getUpdatehost(){
		return this.updatehost;
	}
	public void  setUpdatehost(String updatehost){
		this.updatehost=updatehost;
	}

	public String getUpdateip(){
		return this.updateip;
	}
	public void  setUpdateip(String updateip){
		this.updateip=updateip;
	}

	public String getUpdateip2(){
		return this.updateip2;
	}
	public void  setUpdateip2(String updateip2){
		this.updateip2=updateip2;
	}

	public String getSysurl(){
		return this.sysurl;
	}
	public void setUpdateport(Integer updateport) {
		this.updateport = updateport;
	}
	public Integer getUpdateport() {
		return updateport;
	}
	public void  setSysurl(String sysurl){
		this.sysurl=sysurl;
	}

	public String getDllurl(){
		return this.dllurl;
	}
	public void  setDllurl(String dllurl){
		this.dllurl=dllurl;
	}

	public Byte getStatus(){
		return this.status;
	}
	public void  setStatus(Byte status){
		this.status=status;
	}

	public String getCreatetime(){
		return this.createtime;
	}
	public void  setCreatetime(String createtime){
		this.createtime=createtime;
	}

	public void setDriverchannelList(List<TDriverchannel> driverchannelList) {
		this.driverchannelList = driverchannelList;
	}
	public List<TDriverchannel> getDriverchannelList() {
		return driverchannelList;
	}
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TDriver").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("version").append(" = ").append(version).append(",");
		toString.append("updatehost").append(" = ").append(updatehost).append(",");
		toString.append("updateip").append(" = ").append(updateip).append(",");
		toString.append("updateip2").append(" = ").append(updateip2).append(",");
		toString.append("updateport").append(" = ").append(updateport).append(",");
		toString.append("sysurl").append(" = ").append(sysurl).append(",");
		toString.append("dllurl").append(" = ").append(dllurl).append(",");
		toString.append("status").append(" = ").append(status).append(",");
		toString.append("createtime").append(" = ").append(createtime).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}