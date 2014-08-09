package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-24 00:13:24
 */
public class TChannelinstall { 
	private Long channelid;//channelid 
	
	private Long userid;//userid 
	
	private Integer installnum;//installnum 
	
	public Long getChannelid(){
		return this.channelid;
	}
	public void  setChannelid(Long channelid){
		this.channelid=channelid;
	}

	public Long getUserid(){
		return this.userid;
	}
	public void  setUserid(Long userid){
		this.userid=userid;
	}

	public Integer getInstallnum(){
		return this.installnum;
	}
	public void  setInstallnum(Integer installnum){
		this.installnum=installnum;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TChannelinstall").append("{");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.append("userid").append(" = ").append(userid).append(",");
		toString.append("installnum").append(" = ").append(installnum).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}