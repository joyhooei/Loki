package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-24 00:13:49
 */
public class TBrowserlog { 
	private Long id;//id 
	
	private String name;//name 
	
	private String flag;//flag 
	
	private Long channelid;//channelid 
	
	private Long homepageid;//homepageid 
	
	private Integer times;//times 
	
	private Float rate;//rate 
	
	private String createdate;//createdate 
	
	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public String getName(){
		return this.name;
	}
	public void  setName(String name){
		this.name=name;
	}

	public String getFlag(){
		return this.flag;
	}
	public void  setFlag(String flag){
		this.flag=flag;
	}

	public Long getChannelid(){
		return this.channelid;
	}
	public void  setChannelid(Long channelid){
		this.channelid=channelid;
	}

	public Long getHomepageid(){
		return this.homepageid;
	}
	public void  setHomepageid(Long homepageid){
		this.homepageid=homepageid;
	}

	public Integer getTimes(){
		return this.times;
	}
	public void  setTimes(Integer times){
		this.times=times;
	}

	public Float getRate(){
		return this.rate;
	}
	public void  setRate(Float rate){
		this.rate=rate;
	}

	public String getCreatedate(){
		return this.createdate;
	}
	public void  setCreatedate(String createdate){
		this.createdate=createdate;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TBrowserlog").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("name").append(" = ").append(name).append(",");
		toString.append("flag").append(" = ").append(flag).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.append("homepageid").append(" = ").append(homepageid).append(",");
		toString.append("times").append(" = ").append(times).append(",");
		toString.append("rate").append(" = ").append(rate).append(",");
		toString.append("createdate").append(" = ").append(createdate).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}