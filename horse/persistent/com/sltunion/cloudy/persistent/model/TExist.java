package com.sltunion.cloudy.persistent.model;
/**
 * @author sundial
 * @date 2014-02-23 23:50:52
 */
public class TExist { 
	private String existval;//existval 
	private String table;
	
	public String getExistval(){
		return this.existval;
	}
	public void  setExistval(String existval){
		this.existval=existval;
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
		toString.append("TInstallexist").append("{");
		toString.append("existval").append(" = ").append(existval).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}