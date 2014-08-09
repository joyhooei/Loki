package com.sltunion.cloudy.web.action.interfaces;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;

import com.sltunion.cloudy.common.utils.EncryptUtil;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.common.utils.XmlParseUtil;
import com.sltunion.cloudy.service.DriverDownService;
import com.sltunion.cloudy.service.IAUService;
import com.sltunion.cloudy.vo.xml.DriverInfoXml;
import com.sltunion.cloudy.web.action.BaseAction;

/**
 * 
 * @author sundial
 * 
 */
public class DriverDownAction extends BaseAction {

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
	private Integer isd;
	private Integer ifs;
	private String debug;
	private String laws;

	private String result;

	@Resource
	private DriverDownService driverDownService;
	@Resource
	protected IAUService iauService;

	public String pullMultiurl() {
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
		if(i3==null){
			i3=0;
		}
		try {
			result = driverDownService.pullMultiXml(channelid, dn, ma, ip, i3, db, id, sn, sv,
					debug);
		} catch (Exception e) {
			logger.error("", e);
			DriverInfoXml driverInfoXml = new DriverInfoXml();
			driverInfoXml.setSysVersion("0");
			driverInfoXml.setLockFlag(0);
			driverInfoXml.setUrlCount(0);
			driverInfoXml.setUpdateHost("");
			driverInfoXml.setUpdateIp("");
			driverInfoXml.setUpdateIp2("");
			driverInfoXml.setUpdatePort(0);
			driverInfoXml.setD_dn_rl("");
			driverInfoXml.setS_dn_rl("");
			driverInfoXml.setUpdateCount(0);
			driverInfoXml.setAutoStartIe(0);
			driverInfoXml.setPrtMe(0);

			result = XmlParseUtil.marshal(driverInfoXml, DriverInfoXml.class);
		}
		int ret = 0;
		if (ObjectUtil.isNotEmpty(dn) && 1==ifs) {
			start = System.currentTimeMillis();
			ma = ObjectUtil.toBlank(ma);
			dn = ObjectUtil.toBlank(dn);
			try {
				ret = iauService.install(channelid, ma, ip, sv, dn);
			} catch (DuplicateKeyException e) {
				logger.error(e.getMessage());
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		if (!"true".equalsIgnoreCase(laws)) {
			result = EncryptUtil.encode(result);
		}
		logger.info("取驱主页、安装上报、更新驱动[渠道id:" + ci + ",ip:" + ip + ",is360:" + i3 + ",defaultbrowser:"
				+ db + ",安装日期:" + id + ",驱动版本号:" + sn + ",系统版本:" + sv + ",disknum:" + dn + ",mac:"
				+ ma + ",ifs:"+ifs+",install:" + ret + ",耗时：" + (System.currentTimeMillis() - start) + "ms]");
		return SUCCESS;
	}

	public String pullDriver() {
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
		if(i3==null){
			i3=0;
		}
		try {
			ma = ObjectUtil.toBlank(ma);
			dn = ObjectUtil.toBlank(dn);
			result = driverDownService.pullDriver(channelid, dn, ma, ip, i3, db, id, sn, sv,
					debug);
		} catch (Exception e) {
			logger.error("", e);
			DriverInfoXml driverInfoXml = new DriverInfoXml();
			driverInfoXml.setSysVersion("0");
			driverInfoXml.setLockFlag(0);
			driverInfoXml.setUrlCount(0);
			driverInfoXml.setUpdateHost("");
			driverInfoXml.setUpdateIp("");
			driverInfoXml.setUpdateIp2("");
			driverInfoXml.setUpdatePort(0);
			driverInfoXml.setD_dn_rl("");
			driverInfoXml.setS_dn_rl("");
			driverInfoXml.setUpdateCount(0);
			driverInfoXml.setAutoStartIe(0);
			driverInfoXml.setPrtMe(0);

			result = XmlParseUtil.marshal(driverInfoXml, DriverInfoXml.class);
		}
		logger.info("驱动下载[渠道id:" + ci + ",ip:" + ip + ",is360:" + i3 + ",defaultbrowser:" + db
				+ ",安装日期:" + id + ",驱动版本号:" + sn + ",系统版本:" + sv + ",disknum:" + dn + ",mac:" + ma
				+ ",耗时：" + (System.currentTimeMillis() - start) + "ms]");
		return SUCCESS;
	}
	
	/**
	 * 不能与驱动下载一起使用
	 */
	public String pullDown() {
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
			ma = ObjectUtil.toBlank(ma);
			dn = ObjectUtil.toBlank(dn);
			result = String.valueOf(driverDownService.pullDown(channelid, dn, ma, ip,debug));
		} catch (Exception e) {
			logger.error("", e);
			result = "0";
		}
		logger.info("文件下载[渠道id:" + ci + ",ip:" + ip + ",disknum:" + dn + ",mac:" + ma
				+ ",上报成功:" + ("1".equals(result)?true:false) +",耗时：" + (System.currentTimeMillis() - start) + "ms]");
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

	public void setIsd(Integer isd) {
		this.isd = isd;
	}

	public Integer getIsd() {
		return isd;
	}

	public void setIfs(Integer ifs) {
		this.ifs = ifs;
	}

	public Integer getIfs() {
		return ifs;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}

	public String getDebug() {
		return debug;
	}

	public void setLaws(String laws) {
		this.laws = laws;
	}

	public String getLaws() {
		return laws;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
