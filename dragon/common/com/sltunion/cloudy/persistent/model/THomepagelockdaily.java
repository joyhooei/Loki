package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-07 15:54:50
 */
public class THomepagelockdaily { 
	private Long id;//id 
	
	private Long channelid;//channelid 
	
	private Long homepageid;//homepageid 
	
	private Integer xpnum;//xpnum 
	
	private Integer win732num;//win732num 
	
	private Integer othernum;//othernum
	
	private Integer hour;//hour
	
	private String createdate;//createdate 
	
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

	public Long getHomepageid(){
		return this.homepageid;
	}
	public void  setHomepageid(Long homepageid){
		this.homepageid=homepageid;
	}

	public Integer getXpnum(){
		return this.xpnum;
	}
	public void  setXpnum(Integer xpnum){
		this.xpnum=xpnum;
	}

	public Integer getWin732num(){
		return this.win732num;
	}
	public void  setWin732num(Integer win732num){
		this.win732num=win732num;
	}

	public Integer getOthernum(){
		return this.othernum;
	}
	public void  setOthernum(Integer othernum){
		this.othernum=othernum;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getHour() {
		return hour;
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
		toString.append("THomepagelockdaily").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.append("homepageid").append(" = ").append(homepageid).append(",");
		toString.append("xpnum").append(" = ").append(xpnum).append(",");
		toString.append("win732num").append(" = ").append(win732num).append(",");
		toString.append("hour").append(" = ").append(hour).append(",");
		toString.append("othernum").append(" = ").append(othernum).append(",");
		toString.append("createdate").append(" = ").append(createdate).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}