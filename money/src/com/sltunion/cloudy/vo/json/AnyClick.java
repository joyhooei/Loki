/**   
* @Title: AnyClick.java 
* @Package com.zdt.anyclick.vo.json 
* @author liugang
* @email  liugang@iadpush.com   
* @date 2013-7-5 下午3:22:54 
* @version V1.0   
*/
package com.sltunion.cloudy.vo.json;

import java.io.Serializable;

/** 
 * @ClassName: AnyClick 
 * @author liugang
 * @date 2013-7-5 下午3:22:54 
 *  
 */
public class AnyClick implements Serializable {
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	
	private static final long serialVersionUID = -6569420751803073695L;
	/** 
	* @Fields adid : 广告ID
	*/ 
	private Long adid;
	/** 
	* @Fields adlandpage : 着陆页地址 
	*/ 
	private String adlandpage;
	/** 
	* @Fields adourl : 目标页地址 
	*/ 
	private String adourl;
	/** 
	* @Fields admustclick : 是否强制点击 
	*/ 
	private Integer admustclick;
	/** 
	* @Fields issecond : 是否进行二次点击 
	*/ 
	private Integer issecond;
	/** 
	* @Fields secondurl : 二次点击目标页 
	*/ 
	private String secondurl;
	/** 
	* @Fields isthird : 是否进行三次点击 
	*/ 
	private Integer isthird;
	/** 
	* @Fields thirdurl : 三次点击目标页
	*/ 
	private String thirdurl;
	/** 
	* @Fields reporturl : 上报地址前缀 
	*/ 
	private String reporturl;
	/** 
	 * @return adid 
	 */
	public Long getAdid() {
		return adid;
	}
	/** 
	 * @param adid 要设置的 adid 
	 */
	
	public void setAdid(Long adid) {
		this.adid = adid;
	}
	/** 
	 * @return adlandpage 
	 */
	public String getAdlandpage() {
		return adlandpage;
	}
	/** 
	 * @param adlandpage 要设置的 adlandpage 
	 */
	
	public void setAdlandpage(String adlandpage) {
		this.adlandpage = adlandpage;
	}
	/** 
	 * @return adourl 
	 */
	public String getAdourl() {
		return adourl;
	}
	/** 
	 * @param adourl 要设置的 adourl 
	 */
	
	public void setAdourl(String adourl) {
		this.adourl = adourl;
	}
	/** 
	 * @return admustclick 
	 */
	public Integer getAdmustclick() {
		return admustclick;
	}
	/** 
	 * @param admustclick 要设置的 admustclick 
	 */
	
	public void setAdmustclick(Integer admustclick) {
		this.admustclick = admustclick;
	}
	/** 
	 * @return issecond 
	 */
	public Integer getIssecond() {
		return issecond;
	}
	/** 
	 * @param issecond 要设置的 issecond 
	 */
	
	public void setIssecond(Integer issecond) {
		this.issecond = issecond;
	}
	/** 
	 * @return secondurl 
	 */
	public String getSecondurl() {
		return secondurl;
	}
	/** 
	 * @param secondurl 要设置的 secondurl 
	 */
	
	public void setSecondurl(String secondurl) {
		this.secondurl = secondurl;
	}
	/** 
	 * @return isthird 
	 */
	public Integer getIsthird() {
		return isthird;
	}
	/** 
	 * @param isthird 要设置的 isthird 
	 */
	
	public void setIsthird(Integer isthird) {
		this.isthird = isthird;
	}
	/** 
	 * @return thirdurl 
	 */
	public String getThirdurl() {
		return thirdurl;
	}
	/** 
	 * @param thirdurl 要设置的 thirdurl 
	 */
	
	public void setThirdurl(String thirdurl) {
		this.thirdurl = thirdurl;
	}
	/** 
	 * @return reporturl 
	 */
	public String getReporturl() {
		return reporturl;
	}
	/** 
	 * @param reporturl 要设置的 reporturl 
	 */
	
	public void setReporturl(String reporturl) {
		this.reporturl = reporturl;
	}

	
}
