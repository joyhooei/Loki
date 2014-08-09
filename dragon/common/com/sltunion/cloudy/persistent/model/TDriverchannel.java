package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-03-04 04:21:51
 */
public class TDriverchannel { 
	private Long driverid;//driverid 
	
	private Long channelid;//channelid 
	
	public Long getDriverid(){
		return this.driverid;
	}
	public void  setDriverid(Long driverid){
		this.driverid=driverid;
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
		toString.append("TDriverchannel").append("{");
		toString.append("driverid").append(" = ").append(driverid).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}