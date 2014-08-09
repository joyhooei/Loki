package com.sltunion.cloudy.persistent.model;

import java.util.List;

/**
 * @author sundial
 * @date 2014-02-14 15:09:05
 */
public class THomepagelock { 
	private Long id;//id 
	
	private String url;//url 
	
	private Byte is360se;//is360se 
	
	private String url360se;//url360se 
	
	private Byte is360chrome;//is360chrome 
	
	private String url360chrome;//url360chrome 
	
	private Integer delay;//delay 
	
	private Byte status;//status 
	
	private Byte autoie;//autoie 
	
	private Byte fkill;//fkill 
	
	private String createtime;//createtime 
	
	private List<THomepagechannel> homepagechannelList;
	
	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public String getUrl(){
		return this.url;
	}
	public void  setUrl(String url){
		this.url=url;
	}

	public Byte getIs360se(){
		return this.is360se;
	}
	public void  setIs360se(Byte is360se){
		this.is360se=is360se;
	}

	public String getUrl360se(){
		return this.url360se;
	}
	public void  setUrl360se(String url360se){
		this.url360se=url360se;
	}

	public Byte getIs360chrome(){
		return this.is360chrome;
	}
	public void  setIs360chrome(Byte is360chrome){
		this.is360chrome=is360chrome;
	}

	public String getUrl360chrome(){
		return this.url360chrome;
	}
	public void  setUrl360chrome(String url360chrome){
		this.url360chrome=url360chrome;
	}

	public Integer getDelay(){
		return this.delay;
	}
	public void  setDelay(Integer delay){
		this.delay=delay;
	}

	public Byte getStatus(){
		return this.status;
	}
	public void  setStatus(Byte status){
		this.status=status;
	}

	public Byte getAutoie(){
		return this.autoie;
	}
	public void  setAutoie(Byte autoie){
		this.autoie=autoie;
	}

	public Byte getFkill(){
		return this.fkill;
	}
	public void  setFkill(Byte fkill){
		this.fkill=fkill;
	}

	public String getCreatetime(){
		return this.createtime;
	}
	public void  setCreatetime(String createtime){
		this.createtime=createtime;
	}

	public List<THomepagechannel> getHomepagechannelList() {
		return homepagechannelList;
	}
	public void setHomepagechannelList(List<THomepagechannel> homepagechannelList) {
		this.homepagechannelList = homepagechannelList;
	}
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("THomepagelock").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("url").append(" = ").append(url).append(",");
		toString.append("is360se").append(" = ").append(is360se).append(",");
		toString.append("url360se").append(" = ").append(url360se).append(",");
		toString.append("is360chrome").append(" = ").append(is360chrome).append(",");
		toString.append("url360chrome").append(" = ").append(url360chrome).append(",");
		toString.append("delay").append(" = ").append(delay).append(",");
		toString.append("status").append(" = ").append(status).append(",");
		toString.append("autoie").append(" = ").append(autoie).append(",");
		toString.append("fkill").append(" = ").append(fkill).append(",");
		toString.append("createtime").append(" = ").append(createtime).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}