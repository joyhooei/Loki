package com.sltunion.cloudy.web.action.manage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.TUser;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.UserService;
import com.sltunion.cloudy.web.action.PagerAction;

public class ChannelAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3336190935366354544L;

	@Resource
	private ChannelService channelService;
	@Resource
	private UserService userService;

	private Long id;// id

	private String name;// name

	private String username;// username

	private Long userid;// userid

	private Float deduct;// deduct

	private Integer startnum;// startnum

	private Integer status;// status

	private String remark;// remark

	private String createtime;// createtime
	
	private Float price;

	private String ids;

	private List<TUser> userList;

	public String index() {
		try {
			setPager();
			pager.addParams("userid", userid);
			pager.addParams("name", name);
			pager.addParams("order", " a.id DESC");
			channelService.findPagerList(pager);

			userList = userService.findAll();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String changeUser() {
		return SUCCESS;
	}

	public String save() {
		try {
			TUser tUser = userService.findById(userid);
			username = tUser.getUsername();
			createtime = DateUtils.getDatetime();
			TChannel entity = formToBean(this, TChannel.class);
			channelService.save(entity);
			addResultMap("message", "新增渠道[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "新增渠道[" + name + "]失败");
		}
		return SUCCESS;
	}

	public String update() {
		try {
			TUser tUser = userService.findById(userid);
			username = tUser.getUsername();
			TChannel entity = formToBean(this, TChannel.class);
			channelService.update(entity);
			addResultMap("message", "保存渠道[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "保存渠道[" + name + "]失败");
		}
		return SUCCESS;
	}

	public String changeStatus() {
		try {
			if (status == 1) {
				status = (int) Const.Status.DISABLE;
			} else if (status == 2) {
				status = (int) Const.Status.ENABLE;
			}
			channelService.changeStatus(id, status);
			addResultMap("message", "修改渠道[" + name + "]状态成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "修改渠道[" + name + "]状态失败");
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			channelService.delete(id);
			addResultMap("message", "删除渠道[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "删除渠道[" + name + "]失败");
		}
		return SUCCESS;
	}

	public String batchDel() {
		try {
			if (ObjectUtil.isNotEmpty(ids)) {
				channelService.batchDel(ids);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}
	
	public String idleChannel() {
		List<TChannel> idleChannelList = new ArrayList<TChannel>();
		try {
			if (ObjectUtil.isNotEmpty(name)) {
				idleChannelList = channelService.idleChannel(name);
			}
			addResultMap("message", "查询空闲[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "查询空闲[" + name + "]失败");
		}
		addResultMap("idleChannelList",idleChannelList);
		return SUCCESS;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Float getDeduct() {
		return this.deduct;
	}

	public void setDeduct(Float deduct) {
		this.deduct = deduct;
	}

	public Integer getStartnum() {
		return this.startnum;
	}

	public void setStartnum(Integer startnum) {
		this.startnum = startnum;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getCreatetime(){
		return this.createtime;
	}
	public void  setCreatetime(String createtime){
		this.createtime=createtime;
	}
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<TUser> getUserList() {
		return userList;
	}

	public void setUserList(List<TUser> userList) {
		this.userList = userList;
	}
}