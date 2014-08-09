package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-24 01:19:49
 */
public class TStatistics { 
	private Long id;//id 
	
	private String sourcetable;//sourcetable 
	
	private String targettable;//targettable 
	
	private Long sourceid;//sourceid 
	
	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public String getSourcetable(){
		return this.sourcetable;
	}
	public void  setSourcetable(String sourcetable){
		this.sourcetable=sourcetable;
	}

	public String getTargettable(){
		return this.targettable;
	}
	public void  setTargettable(String targettable){
		this.targettable=targettable;
	}

	public Long getSourceid(){
		return this.sourceid;
	}
	public void  setSourceid(Long sourceid){
		this.sourceid=sourceid;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TStatistics").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("sourcetable").append(" = ").append(sourcetable).append(",");
		toString.append("targettable").append(" = ").append(targettable).append(",");
		toString.append("sourceid").append(" = ").append(sourceid).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}