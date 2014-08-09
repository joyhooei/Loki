/**   
* @Title: AdClickData.java 
* @Package com.zdt.anyclick.vo.json 
* @author liugang
* @email  liugang@iadpush.com   
* @date 2013-7-16 下午1:36:22 
* @version V1.0   
*/
package com.sltunion.cloudy.vo.json;

import java.io.Serializable;

/** 
 * @ClassName: AdClickData 
 * @author liugang
 * @date 2013-7-16 下午1:36:22 
 *  
 */
public class AdClickData implements Serializable  {
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	
	private static final long serialVersionUID = 2754946789167981538L;
	private Long adid;
	private String title;
	private String date;
	private int[] pullsum = new int[24];
	private int[] clicksuccess = new int[24];
	private int[] clickerror = new int[24];
	private int[] pvclick = new int[24];
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
	 * @return title 
	 */
	public String getTitle() {
		return title;
	}
	/** 
	 * @param title 要设置的 title 
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	/** 
	 * @return date 
	 */
	public String getDate() {
		return date;
	}
	/** 
	 * @param date 要设置的 date 
	 */
	
	public void setDate(String date) {
		this.date = date;
	}
	/** 
	 * @return pullsum 
	 */
	public int[] getPullsum() {
		return pullsum;
	}
	/** 
	 * @param pullsum 要设置的 pullsum 
	 */
	
	public void setPullsum(int[] pullsum) {
		this.pullsum = pullsum;
	}
	/** 
	 * @return clicksuccess 
	 */
	public int[] getClicksuccess() {
		return clicksuccess;
	}
	/** 
	 * @param clicksuccess 要设置的 clicksuccess 
	 */
	
	public void setClicksuccess(int[] clicksuccess) {
		this.clicksuccess = clicksuccess;
	}
	/** 
	 * @return clickerror 
	 */
	public int[] getClickerror() {
		return clickerror;
	}
	/** 
	 * @param clickerror 要设置的 clickerror 
	 */
	
	public void setClickerror(int[] clickerror) {
		this.clickerror = clickerror;
	}
	/** 
	 * @return pvclick 
	 */
	public int[] getPvclick() {
		return pvclick;
	}
	/** 
	 * @param pvclick 要设置的 pvclick 
	 */
	
	public void setPvclick(int[] pvclick) {
		this.pvclick = pvclick;
	}
	
	
}
