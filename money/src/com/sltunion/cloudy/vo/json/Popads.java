package com.sltunion.cloudy.vo.json;

import java.io.Serializable;

/**
 * @author sundial
 * @date 2013-10-22
 */
public class Popads implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8814708752763917633L;

	private String adurl; // 弹窗地址

	private Integer adwidth; // 广告宽

	private Integer adhigh; // 广告高

	private Integer displayorder; // 显示顺序

	private Integer displaytime; // 显示时间

	private Integer starttime; // 首次启动时间（秒）

	private Integer nextstarttime; // 下次启动时间（秒）

	public String getAdurl() {
		return adurl;
	}

	public void setAdurl(String adurl) {
		this.adurl = adurl;
	}

	public Integer getAdwidth() {
		return adwidth;
	}

	public void setAdwidth(Integer adwidth) {
		this.adwidth = adwidth;
	}

	public Integer getAdhigh() {
		return adhigh;
	}

	public void setAdhigh(Integer adhigh) {
		this.adhigh = adhigh;
	}

	public Integer getDisplayorder() {
		return displayorder;
	}

	public void setDisplayorder(Integer displayorder) {
		this.displayorder = displayorder;
	}

	public Integer getDisplaytime() {
		return displaytime;
	}

	public void setDisplaytime(Integer displaytime) {
		this.displaytime = displaytime;
	}

	public Integer getStarttime() {
		return starttime;
	}

	public void setStarttime(Integer starttime) {
		this.starttime = starttime;
	}

	public Integer getNextstarttime() {
		return nextstarttime;
	}

	public void setNextstarttime(Integer nextstarttime) {
		this.nextstarttime = nextstarttime;
	}

	@Override
	public String toString() {
		return "TPopads [adurl = " + adurl + ",adwidth = " + adwidth + ",adhigh = " + adhigh
				+ ",displayorder = " + displayorder + ",displaytime = " + displaytime
				+ ",starttime = " + starttime + ",nextstarttime = " + nextstarttime + "]";
	}
}