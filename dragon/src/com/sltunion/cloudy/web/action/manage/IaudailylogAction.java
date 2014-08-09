package com.sltunion.cloudy.web.action.manage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.TUser;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.IaudailylogService;
import com.sltunion.cloudy.service.UserService;
import com.sltunion.cloudy.web.action.PagerAction;

public class IaudailylogAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7901732852114667376L;

	@Resource
	private IaudailylogService iaudailylogService;
	@Resource
	private ChannelService channelService;
	@Resource
	private UserService userService;

	private Long channelid;

	private Long userid;

	private Integer datedeff;

	private List<TUser> userList;

	private List<TChannel> channelList;
	
	private String enddate;
	private String startdate;

	public String index() {
		try {
			setPager();
			//String createdate = DateUtils.getDateStr();
			if(ObjectUtil.isEmpty(enddate)){
				enddate = DateUtils.getDateStr();
			}
			
			if(ObjectUtil.isEmpty(startdate)){
				startdate = DateUtils.getDateStr();
			}
			pager.addParams("channelid", channelid);
			pager.addParams("userid", userid);
			//pager.addParams("createdate", createdate);
			pager.addParams("startdate", startdate);
			pager.addParams("enddate", enddate);
			pager.addParams("group", "a.channelid");
			pager.addParams("order", "a.channelid DESC");
			iaudailylogService.findPagerList(pager);

			userList = userService.findAll();
			channelList = channelService.findAll();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}
	
	public String cost() {
		try {
			setPager();
			
			if(ObjectUtil.isEmpty(enddate)){
				enddate = DateUtils.getDateStr();
				enddate = DateUtils.getDateStr(enddate, -1);
			}
			
			if(ObjectUtil.isEmpty(startdate)){
				startdate = DateUtils.getDateStr();
				startdate = DateUtils.getDateStr(startdate, -1);
			}
			
			pager.addParams("channelid", channelid);
			pager.addParams("userid", userid);
			pager.addParams("startdate", startdate);
			pager.addParams("enddate", enddate);
			pager.addParams("group", "a.channelid");
			pager.addParams("order", "a.channelid DESC");
			iaudailylogService.findPagerList(pager);

			userList = userService.findAll();
			channelList = channelService.findAll();
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
				hourChart();
			}
			userList = userService.findAll();
			channelList = channelService.findAll();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}
	
	protected void hourChart(){
		datedeff = 24;
		String enddate = DateUtils.getDateStr();
		pager.addParams("channelid", channelid);
		pager.addParams("userid", userid);
		pager.addParams("startdate", enddate);
		pager.addParams("enddate", enddate);
		pager.addParams("group", "a.hour");
		pager.addParams("order", "a.hour ASC");
		List<Map<String, Object>> list = iaudailylogService.getChartResultList(pager
				.getParams());

		int[] installnum = new int[datedeff];
		int[] activenum = new int[datedeff];
		int[] uninstallnum = new int[datedeff];
		int[] driverdown = new int[datedeff];
		int[] xpnum = new int[datedeff];// 数量
		int[] win732num = new int[datedeff];
		int[] win764num = new int[datedeff];
		int[] win8num = new int[datedeff];
		int[] othernum = new int[datedeff];
		int total = 0;// 数量
		String[] createdate = new String[datedeff];// 数量
		String tempdate = "0";
		if (list != null && !list.isEmpty()) {
			int index = 0;
			while (index < datedeff) {
				for (Map<String, Object> map : list) {
					String hour = map.get("hour")==null?"": map.get("hour").toString();
					if (tempdate.equals(hour)) {
						installnum[index] = Integer.parseInt(map.get("installnum").toString());
						activenum[index] = Integer.parseInt(map.get("activenum").toString());
						uninstallnum[index] = Integer.parseInt(map.get("uninstallnum")
								.toString());
						driverdown[index] = Integer.parseInt(map.get("driverdown").toString());
						xpnum[index] = Integer.parseInt(map.get("xpnum").toString());
						win732num[index] = Integer.parseInt(map.get("win732num").toString());
						win764num[index] = Integer.parseInt(map.get("win764num").toString());
						win8num[index] = Integer.parseInt(map.get("win832num").toString())
								+ Integer.parseInt(map.get("win864num").toString());
						othernum[index] = Integer.parseInt(map.get("othernum").toString());
						total += installnum[index];
					}
				}
				createdate[index] = tempdate;
				index++;
				tempdate = index+"";
			}
		}

		addResultMap("createdate", createdate);
		addResultMap("installnum", installnum);
		addResultMap("activenum", activenum);
		addResultMap("uninstallnum", uninstallnum);
		addResultMap("driverdown", driverdown);
		addResultMap("xpnum", xpnum);
		addResultMap("win732num", win732num);
		addResultMap("win764num", win764num);
		addResultMap("win8num", win8num);
		addResultMap("othernum", othernum);
		addResultMap("total", total);
	}
	
	protected void dailyChart(){
		String enddate = DateUtils.getDateStr();
		String startdate = DateUtils.getDateStr(enddate, 1 - datedeff);
		pager.addParams("channelid", channelid);
		pager.addParams("userid", userid);
		pager.addParams("startdate", startdate);
		pager.addParams("enddate", enddate);
		pager.addParams("group", "a.createdate");
		pager.addParams("order", "a.createdate ASC");
		List<Map<String, Object>> list = iaudailylogService.getChartResultList(pager
				.getParams());

		int[] installnum = new int[datedeff];
		int[] activenum = new int[datedeff];
		int[] uninstallnum = new int[datedeff];
		int[] driverdown = new int[datedeff];
		int[] xpnum = new int[datedeff];// 数量
		int[] win732num = new int[datedeff];
		int[] win764num = new int[datedeff];
		int[] win8num = new int[datedeff];
		int[] othernum = new int[datedeff];
		int total = 0;// 数量
		String[] createdate = new String[datedeff];// 数量
		String tempdate = startdate;
		if (list != null && !list.isEmpty()) {
			int index = 0;
			while (index < datedeff) {
				for (Map<String, Object> map : list) {
					String date = map.get("createdate").toString();
					if (tempdate.equals(date)) {
						installnum[index] = Integer.parseInt(map.get("installnum").toString());
						activenum[index] = Integer.parseInt(map.get("activenum").toString());
						uninstallnum[index] = Integer.parseInt(map.get("uninstallnum")
								.toString());
						driverdown[index] = Integer.parseInt(map.get("driverdown").toString());
						xpnum[index] = Integer.parseInt(map.get("xpnum").toString());
						win732num[index] = Integer.parseInt(map.get("win732num").toString());
						win764num[index] = Integer.parseInt(map.get("win764num").toString());
						win8num[index] = Integer.parseInt(map.get("win832num").toString())
								+ Integer.parseInt(map.get("win864num").toString());
						othernum[index] = Integer.parseInt(map.get("othernum").toString());
						total += installnum[index];
					}
				}
				createdate[index] = tempdate;
				tempdate = DateUtils.getDateStr(enddate, 2 + index - datedeff);
				index++;
			}
		}

		addResultMap("createdate", createdate);
		addResultMap("installnum", installnum);
		addResultMap("activenum", activenum);
		addResultMap("uninstallnum", uninstallnum);
		addResultMap("driverdown", driverdown);
		addResultMap("xpnum", xpnum);
		addResultMap("win732num", win732num);
		addResultMap("win764num", win764num);
		addResultMap("win8num", win8num);
		addResultMap("othernum", othernum);
		addResultMap("total", total);
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Integer getDatedeff() {
		return datedeff;
	}

	public void setDatedeff(Integer datedeff) {
		this.datedeff = datedeff;
	}

	public List<TUser> getUserList() {
		return userList;
	}

	public void setUserList(List<TUser> userList) {
		this.userList = userList;
	}

	public Long getChannelid() {
		return channelid;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}

	public List<TChannel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<TChannel> channelList) {
		this.channelList = channelList;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStartdate() {
		return startdate;
	}
}