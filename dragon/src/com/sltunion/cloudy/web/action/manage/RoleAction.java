package com.sltunion.cloudy.web.action.manage;

import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TModule;
import com.sltunion.cloudy.persistent.model.TRole;
import com.sltunion.cloudy.service.ModuleService;
import com.sltunion.cloudy.service.RoleService;
import com.sltunion.cloudy.web.action.PagerAction;

public class RoleAction extends PagerAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3336190935366354544L;

	@Resource
	private RoleService roleService;
	@Resource
	private ModuleService moduleService;

	private Long id;//id 
	
	private String rolename;//rolename 
	
	private String remark;//remark 
	
	private Byte manage;//manage 
	
	private List<TModule> moduleList;
	
	private Long moduleid;

	private String ids;
	
	private boolean checked;

	public String index() {
		try {
			setPager();
			pager.addParams("rolename", rolename);
			pager.addParams("manage", manage);
			pager.addParams("order", "a.id DESC");
			roleService.findPagerList(pager);
			addResultMap("order", "a.sortindex ASC");
			moduleList = moduleService.findListByParams(resultMap);
			resultMap = null;
			
			
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}
	
	public String assign(){
		try {
			roleService.assign(id,moduleid,checked);
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String save() {
		try {
			TRole entity = formToBean(this, TRole.class);
			roleService.save(entity);
			addResultMap("message", "新增角色[" + rolename + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "新增角色[" + rolename + "]失败");
			addResultMap("result", 0);
		}
		return SUCCESS;
	}

	public String update() {
		try {
			TRole entity = formToBean(this, TRole.class);
			roleService.update(entity);
			addResultMap("message", "保存角色[" + rolename + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "保存角色[" + rolename + "]失败");
			addResultMap("result", 0);
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			roleService.delete(id);
			addResultMap("message", "删除角色[" + rolename + "]成功");
			addResultMap("result", 1);
		} catch (Exception e) {
			logger.error("", e);
			addResultMap("message", "删除角色[" + rolename + "]失败");
			addResultMap("result", 0);
		}
		return SUCCESS;
	}

	public String batchDel() {
		try {
			if (ObjectUtil.isNotEmpty(ids)) {
				roleService.batchDel(ids);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public Long getId(){
		return this.id;
	}
	public void  setId(Long id){
		this.id=id;
	}

	public String getRolename(){
		return this.rolename;
	}
	public void  setRolename(String rolename){
		this.rolename=rolename;
	}

	public String getRemark(){
		return this.remark;
	}
	public void  setRemark(String remark){
		this.remark=remark;
	}

	public Byte getManage(){
		return this.manage;
	}
	public void  setManage(Byte manage){
		this.manage=manage;
	}
	
	public List<TModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<TModule> moduleList) {
		this.moduleList = moduleList;
	}

	public Long getModuleid() {
		return moduleid;
	}

	public void setModuleid(Long moduleid) {
		this.moduleid = moduleid;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}