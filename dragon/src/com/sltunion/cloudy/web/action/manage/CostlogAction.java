package com.sltunion.cloudy.web.action.manage;

import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.TUser;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.CostlogService;
import com.sltunion.cloudy.service.UserService;
import com.sltunion.cloudy.web.action.PagerAction;

public class CostlogAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9179699564847293448L;
	@Resource
	private CostlogService costlogService;
	@Resource
	private ChannelService channelService;
	@Resource
	private UserService userService;
	
	private Long id;// id

	private Long channelid;

	private Long userid;
	
	private Byte status;// status
	
	private String createdate;// createdate

	private List<TUser> userList;

	private List<TChannel> channelList;

	private String enddate;
	private String startdate;

	public String index() {
		try {
			setPager();

			if (ObjectUtil.isEmpty(enddate)) {
				enddate = DateUtils.getDateStr();
				enddate = DateUtils.getDateStr(enddate, -1);
			}

			if (ObjectUtil.isEmpty(startdate)) {
				startdate = DateUtils.getDateStr();
				startdate = DateUtils.getDateStr(startdate, -1);
			}

			pager.addParams("channelid", channelid);
			pager.addParams("userid", userid);
			pager.addParams("startdate", startdate);
			pager.addParams("enddate", enddate);
			pager.addParams("group", "a.userid,a.createdate");
			pager.addParams("order", "a.status DESC,a.createdate ASC,a.userid ASC");
			costlogService.findPagerList(pager);

			userList = userService.findAll();
			channelList = channelService.findAll();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}
	
	public String playCost(){
		try {
			costlogService.changeStatus(id, Const.Status.ENABLE);
			addResultMap("message", "支付渠道[" + channelid + "] ["+ createdate +"]费用成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "支付渠道[" + channelid + "] ["+ createdate +"]状态失败");
		}
		return SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
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