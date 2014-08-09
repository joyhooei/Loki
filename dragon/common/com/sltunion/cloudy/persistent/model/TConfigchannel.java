package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-15 22:11:47
 */
public class TConfigchannel { 
	private Long configid;//configid 
	
	private Long channelid;//channelid 
	
	public Long getConfigid(){
		return this.configid;
	}
	public void  setConfigid(Long configid){
		this.configid=configid;
	}

	public Long getChannelid(){
		return this.channelid;
	}
	public void  setChannelid(Long channelid){
		this.channelid=channelid;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TConfigchannel").append("{");
		toString.append("configid").append(" = ").append(configid).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}