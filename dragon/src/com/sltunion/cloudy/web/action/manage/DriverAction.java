package com.sltunion.cloudy.web.action.manage;

import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.DateUtils;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.TDriver;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.DriverService;
import com.sltunion.cloudy.web.action.PagerAction;

public class DriverAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7901732852114667376L;

	@Resource
	private DriverService driverService;
	@Resource
	private ChannelService channelService;

	private Long id;// id

	private Integer version;// version

	private String updatehost;// updatehost

	private String updateip;// updateip

	private String updateip2;// updateip2
	
	private Integer updateport;

	private String sysurl;// sysurl

	private String dllurl;// dllurl

	private Byte status;// status

	private String createtime;// createtime

	private Long channelid;// channelid

	private String ids;

	private List<TChannel> channelList;

	private boolean checked;

	public String index() {
		try {
			setPager();
			pager.addParams("version", version);
			pager.addParams("status", status);
			pager.addParams("order", " a.id DESC");
			driverService.findPagerList(pager);

			channelList = channelService.findAll();
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String assign() {
		try {
			driverService.assign(id, channelid, checked);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String save() {
		try {
			createtime = DateUtils.getDatetime();
			TDriver entity = formToBean(this, TDriver.class);
			driverService.save(entity);
			addResultMap("message", "新增驱动配置[" + version + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "新增驱动配置[" + version + "]失败");
		}
		return SUCCESS;
	}

	public String update() {
		try {
			TDriver entity = formToBean(this, TDriver.class);
			driverService.update(entity);
			addResultMap("message", "保存驱动配置[" + version + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "保存驱动配置[" + version + "]失败");
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
			driverService.changeStatus(id, status);
			addResultMap("message", "修改驱动配置[" + version + "]状态成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "修改驱动配置[" + version + "]状态失败");
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			driverService.delete(id);
			addResultMap("message", "删除驱动配置[" + version + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "删除驱动配置[" + version + "]失败");
		}
		return SUCCESS;
	}

	public String batchDel() {
		try {
			if (ObjectUtil.isNotEmpty(ids)) {
				driverService.batchDel(ids);
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

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUpdatehost() {
		return this.updatehost;
	}

	public void setUpdatehost(String updatehost) {
		this.updatehost = updatehost;
	}

	public String getUpdateip() {
		return this.updateip;
	}

	public void setUpdateip(String updateip) {
		this.updateip = updateip;
	}

	public String getUpdateip2() {
		return this.updateip2;
	}

	public void setUpdateip2(String updateip2) {
		this.updateip2 = updateip2;
	}

	public String getSysurl() {
		return this.sysurl;
	}

	public void setUpdateport(Integer updateport) {
		this.updateport = updateport;
	}

	public Integer getUpdateport() {
		return updateport;
	}

	public void setSysurl(String sysurl) {
		this.sysurl = sysurl;
	}

	public String getDllurl() {
		return this.dllurl;
	}

	public void setDllurl(String dllurl) {
		this.dllurl = dllurl;
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