package com.sltunion.cloudy.web.action.interfaces;

import javax.annotation.Resource;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.service.ConfiginiService;
import com.sltunion.cloudy.web.action.BaseAction;

/**
 * 
 * @author sundial
 * 
 */
public class ConfiginiAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1705875188790121818L;

	private String ci;
	private String debug;

	private String result;

	@Resource
	private ConfiginiService configiniService;

	public String pullrecommend() {
		long start = System.currentTimeMillis();
		if (ObjectUtil.isNotEmpty(ci)) {
			Long channelid = 1l;// 渠道
			try {
				// 渠道处理
				ci = ci.split("\\D")[0];
				channelid = Long.valueOf(ci);
			} catch (Exception e) {
				channelid = 1l;
			}
			try {
				Integer logictype = Const.Configini.LOGICTYPE_RECOMM;
				result = configiniService.pullconfigini(channelid, debug, logictype);
				result = ObjectUtil.toBlank(result);
				result = result.replaceAll("\r", "");
				result = result.replaceAll("\n", "");
				result = "[".concat(result.replaceAll("\\[", "{\""));
				result = result.replaceAll("=", "\":\"");
				result = result.replaceAll(";", "\",\"");
				result = result.replaceAll("\\]", "\"}").concat("]");
				result = result.replaceAll("\\}\\{", "},{");
			} catch (Exception e) {
				logger.error("", e);
			}
			logger.info("取推荐配置[渠道id:" + ci + ",耗时：" + (System.currentTimeMillis() - start) + "ms]");
		}
		return SUCCESS;
	}

	public String pullmini() {
		long start = System.currentTimeMillis();
		if (ObjectUtil.isNotEmpty(ci)) {
			Long channelid = 1l;// 渠道
			try {
				// 渠道处理
				ci = ci.split("\\D")[0];
				channelid = Long.valueOf(ci);
			} catch (Exception e) {
				channelid = 1l;
			}
			try {
				Integer logictype = Const.Configini.LOGICTYPE_MINI;
				result = configiniService.pullconfigini(channelid, debug, logictype);
			} catch (Exception e) {
				logger.error("", e);
			}
			logger.info("取迷你配置[渠道id:" + ci + ",耗时：" + (System.currentTimeMillis() - start) + "ms]");
		}
		return SUCCESS;
	}

	public String pullSwitch() {
		long start = System.currentTimeMillis();
		if (ObjectUtil.isNotEmpty(ci)) {
			Long channelid = 1l;// 渠道
			try {
				// 渠道处理
				ci = ci.split("\\D")[0];
				channelid = Long.valueOf(ci);
			} catch (Exception e) {
				channelid = 1l;
			}
			try {
				Integer logictype = Const.Configini.LOGICTYPE_SWITCH;
				result = configiniService.pullconfigini(channelid, debug, logictype);
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		if (ObjectUtil.isEmpty(result)) {
			int random = Const.random.nextInt(Integer.MAX_VALUE);
			while (random == 200) {
				random = Const.random.nextInt(Integer.MAX_VALUE);
			}
			result = "" + random;
		}
		logger.info("取开关配置[渠道id:" + ci + ",耗时：" + (System.currentTimeMillis() - start) + "ms]");
		return SUCCESS;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getCi() {
		return ci;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public String getDebug() {
		return debug;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}
}
