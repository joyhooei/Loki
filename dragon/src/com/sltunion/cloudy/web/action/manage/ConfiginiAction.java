package com.sltunion.cloudy.web.action.manage;

import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.TConfigini;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.ConfiginiService;
import com.sltunion.cloudy.web.action.PagerAction;

public class ConfiginiAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2500023624299304830L;
	@Resource
	private ConfiginiService configiniService;
	@Resource
	private ChannelService channelService;

	private Long id;// id

	private String name;// name

	private Byte logictype;// logictype

	private String content;// content

	private Byte status;// status

	private String createtime;// createtime

	private Long channelid;

	private String ids;

	private List<TChannel> channelList;

	private boolean checked;

	public String index() {
		try {
			setPager();
			pager.addParams("name", name);
			pager.addParams("logictype", logictype);
			pager.addParams("status", status);
			pager.addParams("order", " a.id DESC");
			configiniService.findPagerList(pager);

			channelList = channelService.findAll();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String assign() {
		try {
			configiniService.assign(id, channelid, checked);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String save() {
		try {
			createtime = DateUtils.getDatetime();
			TConfigini entity = formToBean(this, TConfigini.class);
			configiniService.save(entity);
			addResultMap("message", "新增通用配置[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "新增通用配置[" + name + "]失败");
		}
		return SUCCESS;
	}

	public String update() {
		try {
			TConfigini entity = formToBean(this, TConfigini.class);
			configiniService.update(entity);
			addResultMap("message", "保存通用配置[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "保存通用配置[" + name + "]失败");
		}
		return SUCCESS;
	}

	public String changeStatus() {
		try {
			if (status == 1) {
				status = Const.Status.DISABLE;
			} else if (status == 2) {
				status = Const.Status.ENABLE;
			}
			configiniService.changeStatus(id, status);
			addResultMap("message", "修改通用配置[" + name + "]状态成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "修改通用配置[" + name + "]状态失败");
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			configiniService.delete(id);
			addResultMap("message", "删除通用配置[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "删除通用配置[" + name + "]失败");
		}
		return SUCCESS;
	}

	public String batchDel() {
		try {
			if (ObjectUtil.isNotEmpty(ids)) {
				configiniService.batchDel(ids);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
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

	public Byte getLogictype() {
		return this.logictype;
	}

	public void setLogictype(Byte logictype) {
		this.logictype = logictype;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}

	public Long getChannelid() {
		return channelid;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<TChannel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<TChannel> channelList) {
		this.channelList = channelList;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}