package com.sltunion.cloudy.web.action.interfaces;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;

import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.web.action.BaseAction;

/**
 * 
 * @author sundial
 * 
 */
public class CleanCacheAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 715010712641263700L;
	private String name;
	
	@Resource
	private CacheManager cacheManager;
	
	private String result;

	public String clean() {
		result = "失败";
		if (ObjectUtil.isNotEmpty(name)) {
			try {
				if("all".equals(name)){
//					cacheManager.clearAll();
					String[] cacheNames = cacheManager.getCacheNames();
					for (String cacheName : cacheNames) {
						cacheManager.getEhcache(cacheName).removeAll();
					}
				}else{
					cacheManager.getEhcache(name).removeAll();
				}
				result = "成功";
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		logger.info("清除ehcache[名称:" + name + ",结果："+result+"]");
		return SUCCESS;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}
}
