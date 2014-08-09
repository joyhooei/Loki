package com.sltunion.cloudy.web.action.manage;

import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.TPopads;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.PopadsService;
import com.sltunion.cloudy.web.action.PagerAction;

public class PopadsAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7901732852114667376L;

	@Resource
	private PopadsService popadsService;
	@Resource
	private ChannelService channelService;

	private Long id;// id

	private String name;// name

	private String url;// url

	private Integer adwidth;// adwidth

	private Integer adhigh;// adhigh

	private Integer displayorder;// displayorder

	private Integer displaytime;// displaytime

	private Integer starttime;// starttime

	private Integer nextstarttime;// nextstarttime

	private Byte status;// status

	private String createtime;// createtime

	private Long channelid;// channelid

	private String ids;

	private List<TChannel> channelList;

	private boolean checked;

	public String index() {
		try {
			setPager();
			pager.addParams("name", name);
			pager.addParams("status", status);
			pager.addParams("order", " a.id DESC");
			popadsService.findPagerList(pager);

			channelList = channelService.findAll();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String assign() {
		try {
			popadsService.assign(id, channelid, checked);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String save() {
		try {
			createtime = DateUtils.getDatetime();
			TPopads entity = formToBean(this, TPopads.class);
			popadsService.save(entity);
			addResultMap("message", "新增弹窗配置[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "新增弹窗配置[" + name + "]失败");
		}
		return SUCCESS;
	}

	public String update() {
		try {
			TPopads entity = formToBean(this, TPopads.class);
			popadsService.update(entity);
			addResultMap("message", "保存弹窗配置[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "保存弹窗配置[" + name + "]失败");
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
			popadsService.changeStatus(id, status);
			addResultMap("message", "修改弹窗配置[" + name + "]状态成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "修改弹窗配置[" + name + "]状态失败");
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			popadsService.delete(id);
			addResultMap("message", "删除弹窗配置[" + name + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "删除弹窗配置[" + name + "]失败");
		}
		return SUCCESS;
	}

	public String batchDel() {
		try {
			if (ObjectUtil.isNotEmpty(ids)) {
				popadsService.batchDel(ids);
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getAdwidth() {
		return this.adwidth;
	}

	public void setAdwidth(Integer adwidth) {
		this.adwidth = adwidth;
	}

	public Integer getAdhigh() {
		return this.adhigh;
	}

	public void setAdhigh(Integer adhigh) {
		this.adhigh = adhigh;
	}

	public Integer getDisplayorder() {
		return this.displayorder;
	}

	public void setDisplayorder(Integer displayorder) {
		this.displayorder = displayorder;
	}

	public Integer getDisplaytime() {
		return this.displaytime;
	}

	public void setDisplaytime(Integer displaytime) {
		this.displaytime = displaytime;
	}

	public Integer getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Integer starttime) {
		this.starttime = starttime;
	}

	public Integer getNextstarttime() {
		return this.nextstarttime;
	}

	public void setNextstarttime(Integer nextstarttime) {
		this.nextstarttime = nextstarttime;
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

	public Long getChannelid() {
		return this.channelid;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
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