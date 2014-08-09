package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-03-04 04:21:26
 */
public class TPopadschannel { 
	private Long popadsid;//popadsid 
	
	private Long channelid;//channelid 
	
	public Long getPopadsid(){
		return this.popadsid;
	}
	public void  setPopadsid(Long popadsid){
		this.popadsid=popadsid;
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
		toString.append("TPopadschannel").append("{");
		toString.append("popadsid").append(" = ").append(popadsid).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}