package com.sltunion.cloudy.web.action.front;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.IaudailylogService;
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

	private Long channelid;

	private Long userid;

	private Integer datedeff;

	private List<TChannel> channelList;
	
	private String enddate;
	private String startdate;

	public String index() {
		try {
			setPager();
			userid = getUser().getId();
			//String createdate = DateUtils.getDateStr();
			
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
			//pager.addParams("createdate", createdate);
			pager.addParams("startdate", startdate);
			pager.addParams("enddate", enddate);
			pager.addParams("group", "a.channelid");
			pager.addParams("order", "a.channelid DESC");
			iaudailylogService.findPagerList(pager);

			channelList = channelService.findListByUserid(userid);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String chart() {
		try {
			setPager();
			userid = getUser().getId();
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
							total += installnum[index];
						}
					}
					if(datedeff>7){
						tempdate = tempdate.replaceAll("-", "<br/>-");
					}
					createdate[index] = tempdate;
					tempdate = DateUtils.getDateStr(enddate, 2 + index - datedeff);
					index++;
				}
			}

			addResultMap("createdate", createdate);
			addResultMap("installnum", installnum);
			addResultMap("total", total);
			channelList = channelService.findListByUserid(userid);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
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

	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
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

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
}