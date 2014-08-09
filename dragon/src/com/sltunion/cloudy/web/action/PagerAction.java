package com.sltunion.cloudy.web.action;

import com.sltunion.cloudy.common.Pager;

public class PagerAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7680624412544376752L;
	protected Pager pager;
	protected long pageIndex = 1L;
	protected long pageSize = 10L;
	protected long totalSize;

	public Pager getPager() {
		return this.pager;
	}

	public void setPager(){
		this.pager = new Pager(pageIndex, pageSize);
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
}
