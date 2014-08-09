package com.sltunion.cloudy.persistent.model;


/**
 * @author sundial
 * @date 2014-02-10 09:37:22
 */
public class TModule {
	private Long id;// id

	private Long pid;// pid

	private String modulename;// modulename

	private String modulecode;// modulecode

	private Byte manage;// manage

	private Byte moduleleave;// moduleleave

	private String url;// url

	private Integer sortindex;// sortindex

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getModulename() {
		return this.modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getModulecode() {
		return this.modulecode;
	}

	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}

	public Byte getManage() {
		return this.manage;
	}

	public void setManage(Byte manage) {
		this.manage = manage;
	}

	public Byte getModuleleave() {
		return this.moduleleave;
	}

	public void setModuleleave(Byte moduleleave) {
		this.moduleleave = moduleleave;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSortindex() {
		return this.sortindex;
	}

	public void setSortindex(Integer sortindex) {
		this.sortindex = sortindex;
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("TModule").append("{");
		toString.append("id").append(" = ").append(id).append(",");
		toString.append("pid").append(" = ").append(pid).append(",");
		toString.append("modulename").append(" = ").append(modulename).append(",");
		toString.append("modulecode").append(" = ").append(modulecode).append(",");
		toString.append("manage").append(" = ").append(manage).append(",");
		toString.append("moduleleave").append(" = ").append(moduleleave).append(",");
		toString.append("url").append(" = ").append(url).append(",");
		toString.append("sortindex").append(" = ").append(sortindex).append(",");
		toString.setLength(toString.length() - 1);
		toString.append("}");
		return toString.toString();
	}
}