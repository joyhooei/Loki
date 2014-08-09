package com.sltunion.cloudy.web.action.interfaces;

import java.util.List;

import javax.annotation.Resource;

import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.service.PopadsService;
import com.sltunion.cloudy.vo.json.Popads;
import com.sltunion.cloudy.web.action.BaseAction;

/**
 * 
 * @author sundial
 * 
 */
public class PopadsAction extends BaseAction {

	private static final long serialVersionUID = 6310445206029572729L;

	@Resource
	private PopadsService popadsService;

	private List<Popads> result;
	private String debug;
	private String ci;

	public String pullpopads() {
		Long channelid = 1l;
		long start = System.currentTimeMillis();
		try {
			if (ObjectUtil.isNotEmpty(ci)) {
				ci = ci.split("\\D")[0];
				channelid = Long.valueOf(ci.trim());
				result = popadsService.pullPopads(debug, channelid);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		logger.info("取弹窗广告[渠道ID:" + ci + ",耗时：" + (System.currentTimeMillis() - start) + "ms]");
		return SUCCESS;
	}

	public List<Popads> getResult() {
		return result;
	}

	public void setResult(List<Popads> result) {
		this.result = result;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}
}
