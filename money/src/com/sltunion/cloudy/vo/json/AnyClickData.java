/**   
* @Title: AnyClickData.java 
* @Package com.zdt.anyclick.vo.json 
* @author liugang
* @email  liugang@iadpush.com   
* @date 2013-7-5 下午4:31:08 
* @version V1.0   
*/
package com.sltunion.cloudy.vo.json;

import java.io.Serializable;

/** 
 * @ClassName: AnyClickData 
 * @author liugang
 * @date 2013-7-5 下午4:31:08 
 *  
 */
public class AnyClickData implements Serializable {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	
	private static final long serialVersionUID = 68277731296874118L;
	private Long adid;
	private String adname;
	private String date;
	private int[] pullsum = new int[24];
	private int[] clicksuccess = new int[24];
	private int[] clickerror = new int[24];
	private int[] senclick = new int[24];
	private int[] thrclick = new int[24];
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
	 * @return adname 
	 */
	public String getAdname() {
		return adname;
	}
	/** 
	 * @param adname 要设置的 adname 
	 */
	
	public void setAdname(String adname) {
		this.adname = adname;
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
	 * @return senclick 
	 */
	public int[] getSenclick() {
		return senclick;
	}
	/** 
	 * @param senclick 要设置的 senclick 
	 */
	
	public void setSenclick(int[] senclick) {
		this.senclick = senclick;
	}
	/** 
	 * @return thrclick 
	 */
	public int[] getThrclick() {
		return thrclick;
	}
	/** 
	 * @param thrclick 要设置的 thrclick 
	 */
	
	public void setThrclick(int[] thrclick) {
		this.thrclick = thrclick;
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
	
	
}
