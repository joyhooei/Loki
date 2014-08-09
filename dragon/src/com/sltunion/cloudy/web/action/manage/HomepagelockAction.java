package com.sltunion.cloudy.web.action.manage;

import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.THomepagelock;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.HomepagelockService;
import com.sltunion.cloudy.web.action.PagerAction;

public class HomepagelockAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7901732852114667376L;

	@Resource
	private HomepagelockService homepagelockService;
	@Resource
	private ChannelService channelService;

	private Long id;// id

	private Long channelid;// channelid

	private String url;// url

	private Byte is360se;// is360se

	private String url360se;// url360se

	private Byte is360chrome;// is360chrome

	private String url360chrome;// url360chrome

	private Integer delay;// delay

	private Byte status;// status

	private Byte autoie;// autoie

	private Byte fkill;// fkill

	private String createtime;// createtime

	private String ids;

	private List<TChannel> channelList;
	
	private boolean checked;

	public String index() {
		try {
			setPager();
			pager.addParams("status", status);
			pager.addParams("fkill", fkill);
			pager.addParams("order", " a.id DESC");
			homepagelockService.findPagerList(pager);

			channelList = channelService.findAll();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}
	
	public String assign(){
		try {
			homepagelockService.assign(id,channelid,checked);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String save() {
		try {
			createtime = DateUtils.getDatetime();
			THomepagelock entity = formToBean(this, THomepagelock.class);
			homepagelockService.save(entity);
			addResultMap("message", "新增主页配置[" + url + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "新增主页配置[" + url + "]失败");
		}
		return SUCCESS;
	}

	public String update() {
		try {
			THomepagelock entity = formToBean(this, THomepagelock.class);
			homepagelockService.update(entity);
			addResultMap("message", "保存主页配置[" + url + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "保存主页配置[" + url + "]失败");
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
			homepagelockService.changeStatus(id, status);
			addResultMap("message", "修改主页配置[" + url + "]状态成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "修改主页配置[" + url + "]状态失败");
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			homepagelockService.delete(id);
			addResultMap("message", "删除主页配置[" + url + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "删除主页配置[" + url + "]失败");
		}
		return SUCCESS;
	}

	public String batchDel() {
		try {
			if (ObjectUtil.isNotEmpty(ids)) {
				homepagelockService.batchDel(ids);
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

	public Long getChannelid() {
		return this.channelid;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Byte getIs360se() {
		return this.is360se;
	}

	public void setIs360se(Byte is360se) {
		this.is360se = is360se;
	}

	public String getUrl360se() {
		return this.url360se;
	}

	public void setUrl360se(String url360se) {
		this.url360se = url360se;
	}

	public Byte getIs360chrome() {
		return this.is360chrome;
	}

	public void setIs360chrome(Byte is360chrome) {
		this.is360chrome = is360chrome;
	}

	public String getUrl360chrome() {
		return this.url360chrome;
	}

	public void setUrl360chrome(String url360chrome) {
		this.url360chrome = url360chrome;
	}

	public Integer getDelay() {
		return this.delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getAutoie() {
		return this.autoie;
	}

	public void setAutoie(Byte autoie) {
		this.autoie = autoie;
	}

	public Byte getFkill() {
		return this.fkill;
	}

	public void setFkill(Byte fkill) {
		this.fkill = fkill;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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