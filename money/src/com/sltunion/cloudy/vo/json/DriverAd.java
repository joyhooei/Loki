/**
 *@Copyright:Copyright (c) 2008 - 2100
 *@Company:zdt
 */
package com.sltunion.cloudy.vo.json;

import java.io.Serializable;

/**
 * 
 * @author sundial
 * 
 */
public class DriverAd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1264828694882223341L;
	private Long adid;
	private String adkind;
	private String adourl;
	private Integer isno360;

	public Long getAdid() {
		return adid;
	}

	public void setAdid(Long adid) {
		this.adid = adid;
	}

	public String getAdkind() {
		return adkind;
	}

	public void setAdkind(String adkind) {
		this.adkind = adkind;
	}

	public String getAdourl() {
		return adourl;
	}

	public void setAdourl(String adourl) {
		this.adourl = adourl;
	}

	public Integer getIsno360() {
		return isno360;
	}

	public void setIsno360(Integer isno360) {
		this.isno360 = isno360;
	}
}
