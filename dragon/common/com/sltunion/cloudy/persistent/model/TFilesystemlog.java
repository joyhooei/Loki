package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-24 00:13:02
 */
public class TFilesystemlog { 
	private Long id;//id 
	
	private Long userid;//userid 
	
	private Long channelid;//channelid 
	
	private Integer ntfsnum;//ntfsnum 
	
	private Integer fat32num;//fat32num 
	
	private Integer fatnum;//fatnum 
	
	private Integer othernum;//othernum 
	
	private String createdate;//createdate 
	
	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public Long getUserid(){
		return this.userid;
	}
	public void  setUserid(Long userid){
		this.userid=userid;
	}

	public Long getChannelid(){
		return this.channelid;
	}
	public void  setChannelid(Long channelid){
		this.channelid=channelid;
	}

	public Integer getNtfsnum(){
		return this.ntfsnum;
	}
	public void  setNtfsnum(Integer ntfsnum){
		this.ntfsnum=ntfsnum;
	}

	public Integer getFat32num(){
		return this.fat32num;
	}
	public void  setFat32num(Integer fat32num){
		this.fat32num=fat32num;
	}

	public Integer getFatnum(){
		return this.fatnum;
	}
	public void  setFatnum(Integer fatnum){
		this.fatnum=fatnum;
	}

	public Integer getOthernum(){
		return this.othernum;
	}
	public void  setOthernum(Integer othernum){
		this.othernum=othernum;
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
		toString.append("TFilesystemlog").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("userid").append(" = ").append(userid).append(",");
		toString.append("channelid").append(" = ").append(channelid).append(",");
		toString.append("ntfsnum").append(" = ").append(ntfsnum).append(",");
		toString.append("fat32num").append(" = ").append(fat32num).append(",");
		toString.append("fatnum").append(" = ").append(fatnum).append(",");
		toString.append("othernum").append(" = ").append(othernum).append(",");
		toString.append("createdate").append(" = ").append(createdate).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}