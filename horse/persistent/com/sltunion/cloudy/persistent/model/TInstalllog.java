package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-24 00:10:34
 */
public class TInstalllog { 
	private Long id;//id 
	
	private Long channelid;//channelid 
	
	private String disknum;//disknum 
	
	private String mac;//mac 
	
	private String ip;//ip 
	
	private String useragent;//useragent 
	
	private Integer hour;//hour 
	
	private String createdate;//createdate 
	
	private String createtime;//createtime 
	
	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public Long getChannelid(){
		return this.channelid;
	}
	public void  setChannelid(Long channelid){
		this.channelid=channelid;
	}

	public String getDisknum(){
		return this.disknum;
	}
	public void  setDisknum(String disknum){
		this.disknum=disknum;
	}

	public String getMac(){
		return this.mac;
	}
	public void  setMac(String mac){
		this.mac=mac;
	}

	public String getIp(){
		return this.ip;
	}
	public void  setIp(String ip){
		this.ip=ip;
	}

	public String getUseragent(){
		return this.useragent;
	}
	public void  setUseragent(String useragent){
		this.useragent=useragent;
	}

	public Integer getHour(){
		return this.hour;
	}
	public void  setHour(Integer hour){
		this.hour=hour;
	}

	public String getCreatedate(){
		return this.createdate;
	}
	public void  setCreatedate(String createdate){
		this.createdate=createdate;
	}

	public String getCreatetime(){
		return this.createtime;
	}
	public void  setCreatetime(String createtime){
		this.createtime=createtime;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TInstalllog").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.append("disknum").append(" = ").append(disknum).append(",");
		toString.append("mac").append(" = ").append(mac).append(",");
		toString.append("ip").append(" = ").append(ip).append(",");
		toString.append("useragent").append(" = ").append(useragent).append(",");
		toString.append("hour").append(" = ").append(hour).append(",");
		toString.append("createdate").append(" = ").append(createdate).append(",");
		toString.append("createtime").append(" = ").append(createtime).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}