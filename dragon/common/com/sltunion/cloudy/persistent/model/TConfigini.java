package com.sltunion.cloudy.persistent.model;

import java.util.List;

/**
 * @author sundial
 * @date 2014-02-15 22:17:57
 */
public class TConfigini { 
	private Long id;//id 
	
	private String name;//name 
	
	private Byte logictype;//logictype 
	
	private String content;//content 
	
	private Byte status;//status 
	
	private String createtime;//createtime 
	
	private List<TConfigchannel> configchannelList;
	
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

	public Byte getLogictype(){
		return this.logictype;
	}
	public void  setLogictype(Byte logictype){
		this.logictype=logictype;
	}

	public String getContent(){
		return this.content;
	}
	public void  setContent(String content){
		this.content=content;
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

	public void setConfigchannelList(List<TConfigchannel> configchannelList) {
		this.configchannelList = configchannelList;
	}
	public List<TConfigchannel> getConfigchannelList() {
		return configchannelList;
	}
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TConfigini").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("name").append(" = ").append(name).append(",");
		toString.append("logictype").append(" = ").append(logictype).append(",");
		toString.append("content").append(" = ").append(content).append(",");
		toString.append("status").append(" = ").append(status).append(",");
		toString.append("createtime").append(" = ").append(createtime).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}