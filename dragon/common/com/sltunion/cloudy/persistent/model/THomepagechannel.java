package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-14 14:18:21
 */
public class THomepagechannel { 
	private Long homepageid;//homepageid 
	
	private Long channelid;//channelid 
	
	public Long getHomepageid(){
		return this.homepageid;
	}
	public void  setHomepageid(Long homepageid){
		this.homepageid=homepageid;
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
		toString.append("THomepagechannel").append("{");
		toString.append("homepageid").append(" = ").append(homepageid).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}