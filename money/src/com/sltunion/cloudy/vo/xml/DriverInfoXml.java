package com.sltunion.cloudy.vo.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "sysVersion", "updateHost", "updateIp", "updateIp2",
		"updatePort", "lockFlag", "urlCount", "urlEntry","d_dn_rl","s_dn_rl", "updateCount", "updateFile",
		"autoStartIe","prtMe" })
@XmlRootElement(name = "message")
public class DriverInfoXml {

	@XmlElement(required = true)
	protected String sysVersion;
	@XmlElement(required = true)
	protected String updateHost;
	@XmlElement(required = true)
	protected String updateIp;
	@XmlElement(required = true)
	protected String updateIp2;
	@XmlElement(required = true)
	protected Integer updatePort;
	
	@XmlElement(required = true)
	protected Integer lockFlag;
	@XmlElement(required = true)
	protected Integer urlCount;
	protected List<String> urlEntry;
	
	@XmlElement(required = true)
	private String d_dn_rl;
	@XmlElement(required = true)
	private String s_dn_rl;
	
	@XmlElement(required = true)
	protected Integer updateCount;
	protected List<String> updateFile;
	
	@XmlElement(required = true)
	protected Integer autoStartIe;
	
	private Integer prtMe;

	public String getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(String value) {
		this.sysVersion = value;
	}

	public String getUpdateHost() {
		return updateHost;
	}

	public void setUpdateHost(String value) {
		this.updateHost = value;
	}

	public String getUpdateIp() {
		return updateIp;
	}

	public void setUpdateIp(String value) {
		this.updateIp = value;
	}

	public String getUpdateIp2() {
		return updateIp2;
	}

	public void setUpdateIp2(String value) {
		this.updateIp2 = value;
	}

	public Integer getUpdatePort() {
		return updatePort;
	}

	public void setUpdatePort(Integer value) {
		this.updatePort = value;
	}

	public Integer getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(Integer value) {
		this.lockFlag = value;
	}

	public Integer getUrlCount() {
		return urlCount;
	}

	public void setUrlCount(Integer value) {
		this.urlCount = value;
	}

	public List<String> getUrlEntry() {
		if (urlEntry == null) {
			urlEntry = new ArrayList<String>();
		}
		return this.urlEntry;
	}

	public void setUrlEntry(List<String> urlEntry) {
		this.urlEntry = urlEntry;
	}

	public void setD_dn_rl(String d_dn_rl) {
		this.d_dn_rl = d_dn_rl;
	}

	public String getD_dn_rl() {
		return d_dn_rl;
	}

	public void setS_dn_rl(String s_dn_rl) {
		this.s_dn_rl = s_dn_rl;
	}

	public String getS_dn_rl() {
		return s_dn_rl;
	}

	public Integer getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(Integer value) {
		this.updateCount = value;
	}

	public List<String> getUpdateFile() {
		if (updateFile == null) {
			updateFile = new ArrayList<String>();
		}
		return this.updateFile;
	}

	public void setUpdateFile(List<String> updateFile) {
		this.updateFile = updateFile;
	}

	public Integer getAutoStartIe() {
		return autoStartIe;
	}

	public void setAutoStartIe(Integer value) {
		this.autoStartIe = value;
	}

	public void setPrtMe(Integer prtMe) {
		this.prtMe = prtMe;
	}

	public Integer getPrtMe() {
		return prtMe;
	}
}
