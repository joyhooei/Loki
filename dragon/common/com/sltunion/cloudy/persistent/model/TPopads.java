package com.sltunion.cloudy.persistent.model;

import java.util.List;

/**
 * @author sundial
 * @date 2014-03-04 01:54:31
 */
public class TPopads { 
	private Long id;//id 
	
	private String name;//name 
	
	private String url;//url 
	
	private Integer adwidth;//adwidth 
	
	private Integer adhigh;//adhigh 
	
	private Integer displayorder;//displayorder 
	
	private Integer displaytime;//displaytime 
	
	private Integer starttime;//starttime 
	
	private Integer nextstarttime;//nextstarttime 
	
	private Byte status;//status 
	
	private String createtime;//createtime 
	
	private List<TPopadschannel> popadschannelList;
	
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

	public String getUrl(){
		return this.url;
	}
	public void  setUrl(String url){
		this.url=url;
	}

	public Integer getAdwidth(){
		return this.adwidth;
	}
	public void  setAdwidth(Integer adwidth){
		this.adwidth=adwidth;
	}

	public Integer getAdhigh(){
		return this.adhigh;
	}
	public void  setAdhigh(Integer adhigh){
		this.adhigh=adhigh;
	}

	public Integer getDisplayorder(){
		return this.displayorder;
	}
	public void  setDisplayorder(Integer displayorder){
		this.displayorder=displayorder;
	}

	public Integer getDisplaytime(){
		return this.displaytime;
	}
	public void  setDisplaytime(Integer displaytime){
		this.displaytime=displaytime;
	}

	public Integer getStarttime(){
		return this.starttime;
	}
	public void  setStarttime(Integer starttime){
		this.starttime=starttime;
	}

	public Integer getNextstarttime(){
		return this.nextstarttime;
	}
	public void  setNextstarttime(Integer nextstarttime){
		this.nextstarttime=nextstarttime;
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

	public void setPopadschannelList(List<TPopadschannel> popadschannelList) {
		this.popadschannelList = popadschannelList;
	}
	public List<TPopadschannel> getPopadschannelList() {
		return popadschannelList;
	}
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TPopads").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("name").append(" = ").append(name).append(",");
		toString.append("url").append(" = ").append(url).append(",");
		toString.append("adwidth").append(" = ").append(adwidth).append(",");
		toString.append("adhigh").append(" = ").append(adhigh).append(",");
		toString.append("displayorder").append(" = ").append(displayorder).append(",");
		toString.append("displaytime").append(" = ").append(displaytime).append(",");
		toString.append("starttime").append(" = ").append(starttime).append(",");
		toString.append("nextstarttime").append(" = ").append(nextstarttime).append(",");
		toString.append("status").append(" = ").append(status).append(",");
		toString.append("createtime").append(" = ").append(createtime).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}