package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-23 23:52:16
 */
public class TDailyexist { 
	private String existval;//existval 
	
	private String createdate;//createdate 
	
	private String table;
	
	public String getExistval(){
		return this.existval;
	}
	public void  setExistval(String existval){
		this.existval=existval;
	}

	public String getCreatedate(){
		return this.createdate;
	}
	public void  setCreatedate(String createdate){
		this.createdate=createdate;
	}

	public void setTable(String table) {
		this.table = table;
	}
	public String getTable() {
		return table;
	}
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TDailyexist").append("{");
		toString.append("existval").append(" = ").append(existval).append(",");
		toString.append("createdate").append(" = ").append(createdate).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}