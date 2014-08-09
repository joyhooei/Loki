package com.sltunion.cloudy.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author sundial
 * 
 */
public class Pager implements Serializable {
	private static final long serialVersionUID = -6908784306274073033L;

	/** 当前页首记录号 */
	private long startIndex = 0l;
	/**
	 * 每页显示的最大记录数
	 */
	private long pageSize = 10l;
	/**
	 * 最大记录数
	 */
	private long totalSize = 1L;
	/**
	 * 当前页码
	 */
	private long pageIndex = 1l;
	/**
	 * 总页数
	 */
	private long totalPage = 1l;
	/**
	 * 表格数据
	 */
	private List<Map<String, Object>> pageList;
	/**
	 * 分页样式
	 */
	private String pageStyle;
	/**
	 * 页脚
	 */
	private Map<String, Object> footer;
	/**
	 * 查询参数
	 */
	private Map<String, Object> params;
	
	
	/**
	 * 构造方法 默认当前为第一页 页页面数据条数为10条
	 * 
	 * @param pageIndex
	 *            当前页号
	 */
	public Pager() {
	}

	/**
	 * 构造方法 默认页面数据条数为10条
	 * 
	 * @param pageIndex
	 *            当前页号
	 */
	public Pager(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 构造方法
	 * 
	 * @param pageIndex
	 *            当前页号
	 * @param pageSize
	 *            每页最大记录数
	 */
	public Pager(long pageIndex, long pageSize) {
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	/**
	 * 构造方法
	 * 
	 * @param pageIndex
	 *            当前页号
	 * @param pageSize
	 *            每页最大记录数
	 * @param totalSize
	 *            总记录数
	 */
	public Pager(long pageIndex, long pageSize, long totalSize) {
		this.totalSize = totalSize;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		operational();
	}

	private void operational() {
		// 计算总页数
		if(pageSize==0){
			this.totalPage = 1;
		}else if (this.totalSize % this.pageSize == 0) {
			this.totalPage = this.totalSize / this.pageSize;
		} else {
			this.totalPage = this.totalSize / this.pageSize + 1;
		}

		if (this.totalPage <= 0) {
			this.totalPage = 1;
		}

		if (this.pageIndex <= 0) {
			this.pageIndex = 1;
		}else if (this.pageIndex > this.totalPage) {
			this.pageIndex = this.totalPage;
		}

		// 计算当前页首记录号
		this.startIndex = (this.pageIndex - 1) * this.pageSize;
		addParams("startIndex", startIndex);
		addParams("pageSize", pageSize);
	}

	private String pageing() {
		StringBuffer pageSb = new StringBuffer();
		pageSb.append("<table style='font-size: 12px; width: 99%; min-width: 520px; _width: 520px; margin-top: 6px;' cellspacing='0' cellpadding='0' align='right'>");
		pageSb.append("<tr>");
		pageSb.append("<td align='right'>");
		pageSb.append("[共" + this.totalSize + "&nbsp;条记录]&nbsp;&nbsp;");
		if (pageIndex - 1 > 0) {
			pageSb.append("<a id='pageF' href='javascript:void(0)' onclick='pageing(1)'>[首&nbsp;&nbsp;页]</a>&nbsp;&nbsp;");
			pageSb.append("<a id='pageP' href='javascript:void(0)' onclick='pageing("
					+ (this.pageIndex - 1) + ")'>[上一页]</a>&nbsp;&nbsp;");
		} else {
			pageSb.append("<a id='pageF' href='javascript:void(0)'>[首&nbsp;&nbsp; 页]</a>&nbsp;&nbsp;");
			pageSb.append("<a id='pageP' href='javascript:void(0)'>[上一页]</a>&nbsp;&nbsp; ");
		}
		if (pageIndex + 1 <= totalPage) {
			pageSb.append("<a id='pageN' href='javascript:void(0)' onclick='pageing("
					+ (this.pageIndex + 1) + ")'>[下一页]</a>&nbsp;&nbsp;");
			pageSb.append("<a id='pageL' href='javascript:void(0)' onclick='pageing("
					+ this.totalPage + ")'>[末&nbsp;&nbsp; 页]</a>");
		} else {
			pageSb.append("<a id='pageN' href='javascript:void(0)'>[下一页]</a>&nbsp;&nbsp;");
			pageSb.append("<a id='pageL' href='javascript:void(0)'>[末 &nbsp;&nbsp;页]</a>");
		}
		pageSb.append("&nbsp;&nbsp;[共" + this.totalPage + "&nbsp;页]&nbsp;&nbsp; 跳到&nbsp;");
		pageSb.append("<input type='text' name='pageIndex' id='pageIndex' value='" + this.pageIndex
				+ "' style='width: 30px;'/>");
		pageSb.append("&nbsp;页 &nbsp;&nbsp;");
		pageSb.append("<input type='button' id='GO' value='GO' onclick='pageing($(\"#pageIndex\").val())' style='background-color:#76CFE1;'/>");
		pageSb.append("</td>");
		pageSb.append("</tr>");
		pageSb.append("</table>");
		return pageSb.toString();
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
		operational();
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	public long getStartIndex() {
		return startIndex;
	}

	public List<Map<String, Object>> getPageList() {
		return pageList;
	}

	public void setPageList(List<Map<String, Object>> pageList) {
		this.pageList = pageList;
	}
	
	public void setPageStyle(String pageStyle) {
		this.pageStyle = pageStyle;
	}

	public String getPageStyle() {
		this.pageStyle = pageing();
		return pageStyle;
	}

	public Map<String, Object> getFooter() {
		return footer;
	}

	public void setFooter(Map<String, Object> footer) {
		this.footer = footer;
	}

	public Map<String, Object> getParams() {
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void addParams(String key, Object value) {
		getParams().put(key, value);
	}
}
