package com.sltunion.cloudy.web.action.manage;

import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TModule;
import com.sltunion.cloudy.service.ModuleService;
import com.sltunion.cloudy.web.action.PagerAction;

public class ModuleAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3336190935366354544L;

	@Resource
	private ModuleService moduleService;

	private Long id;// id

	private Long pid;// pid

	private String modulename;// modulename

	private String modulecode;// modulecode

	private Byte manage;// manage

	private Byte moduleleave;// moduleleave

	private String url;// url

	private Integer sortindex;// sortindex
	
	private List<TModule> moduleList;

	private String ids;

	public String index() {
		try {
			setPager();
			pager.addParams("order", "a.sortindex ASC");
			moduleService.findPagerList(pager);
			moduleList = moduleService.findListByPid(Const.Module.TOP);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String save() {
		try {
			TModule entity = formToBean(this, TModule.class);
			moduleService.save(entity);
			addResultMap("message", "新增模块[" + modulename + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "新增模块[" + modulename + "]失败");
		}
		return SUCCESS;
	}

	public String update() {
		try {
			TModule entity = formToBean(this, TModule.class);
			moduleService.update(entity);
			addResultMap("message", "保存模块[" + modulename + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "保存模块[" + modulename + "]失败");
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			moduleService.delete(id);
			addResultMap("message", "删除模块[" + modulename + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "删除模块[" + modulename + "]失败");
		}
		return SUCCESS;
	}

	public String batchDel() {
		try {
			if (ObjectUtil.isNotEmpty(ids)) {
				moduleService.batchDel(ids);
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

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getModulename() {
		return this.modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getModulecode() {
		return this.modulecode;
	}

	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}

	public Byte getManage() {
		return this.manage;
	}

	public void setManage(Byte manage) {
		this.manage = manage;
	}

	public Byte getModuleleave() {
		return this.moduleleave;
	}

	public void setModuleleave(Byte moduleleave) {
		this.moduleleave = moduleleave;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSortindex() {
		return this.sortindex;
	}

	public void setSortindex(Integer sortindex) {
		this.sortindex = sortindex;
	}

	public List<TModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<TModule> moduleList) {
		this.moduleList = moduleList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}