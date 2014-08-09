package com.sltunion.cloudy.web.action.manage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.service.BrowserlogService;
import com.sltunion.cloudy.web.action.PagerAction;

public class BrowserlogAction extends PagerAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7256028013913741533L;
	@Resource
	private BrowserlogService browserlogService;
	
	private Integer datedeff;

	public String index() {
		try {
			setPager();
			String createdate = DateUtils.getDateStr();
			pager.addParams("createdate", createdate);
			pager.addParams("order", "a.id ASC");
			browserlogService.findPagerList(pager);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String chart() {
		try {
			setPager();
			if(datedeff>2){
				dailyChart();
			}else{
				pieChart();
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}
	
	protected void pieChart(){
		datedeff = 24;
		String enddate = DateUtils.getDateStr();
		pager.addParams("startdate", enddate);
		pager.addParams("enddate", enddate);
		List<Map<String, Object>> list = browserlogService.getChartResultList(pager.getParams());
		
		int[] xpnum = new int[datedeff];// 数量
		int[] win732num = new int[datedeff];
		int[] othernum = new int[datedeff];
		int[] total = new int[datedeff];// 数量
		String[] createdate = new String[datedeff];// 数量
		String tempdate = "0";
		if (list != null && !list.isEmpty()) {
			int index = 0;
			while (index<datedeff) {
				for (Map<String, Object> map : list) {
					String hour = map.get("hour")==null?"": map.get("hour").toString();
					if(tempdate.equals(hour)){
						xpnum[index] = Integer.parseInt(map.get("xpnum").toString());
						win732num[index] = Integer.parseInt(map.get("win732num").toString());
						othernum[index] = Integer.parseInt(map.get("othernum").toString());
						total[index] = xpnum[index] + win732num[index] + othernum[index];
					}
				}
				createdate[index] = tempdate;
				index++;
				tempdate = index+"";
			}
		}
		addResultMap("createdate", createdate);
		addResultMap("xpnum", xpnum);
		addResultMap("win732num", win732num);
		addResultMap("othernum", othernum);
		addResultMap("total", total);
		addResultMap("datedeff", datedeff);
	}
	
	protected void dailyChart(){
		String enddate = DateUtils.getDateStr();
		String startdate = DateUtils.getDateStr(enddate, 1-datedeff);
		pager.addParams("startdate", startdate);
		pager.addParams("enddate", enddate);
		pager.addParams("group", "a.createdate");
		pager.addParams("order", "a.createdate ASC");
		List<Map<String, Object>> list = browserlogService.getChartResultList(pager.getParams());
		
		int[] xpnum = new int[datedeff];// 数量
		int[] win732num = new int[datedeff];
		int[] othernum = new int[datedeff];
		int[] total = new int[datedeff];// 数量
		String[] createdate = new String[datedeff];// 数量
		String tempdate = startdate;
		if (list != null && !list.isEmpty()) {
			int index = 0;
			while (index<datedeff) {
				for (Map<String, Object> map : list) {
					String date = map.get("createdate").toString();
					if(tempdate.equals(date)){
						xpnum[index] = Integer.parseInt(map.get("xpnum").toString());
						win732num[index] = Integer.parseInt(map.get("win732num").toString());
						othernum[index] = Integer.parseInt(map.get("othernum").toString());
						total[index] = xpnum[index] + win732num[index] + othernum[index];
					}
				}
				createdate[index] = tempdate;
				tempdate = DateUtils.getDateStr(enddate, 2+index-datedeff);
				index++;
			}
		}
		addResultMap("createdate", createdate);
		addResultMap("xpnum", xpnum);
		addResultMap("win732num", win732num);
		addResultMap("othernum", othernum);
		addResultMap("total", total);
		addResultMap("datedeff", datedeff);
	}

	public Integer getDatedeff() {
		return datedeff;
	}

	public void setDatedeff(Integer datedeff) {
		this.datedeff = datedeff;
	}
}