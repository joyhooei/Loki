package com.sltunion.cloudy.web.action.interfaces;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;

import com.sltunion.cloudy.common.utils.DateUtil;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.service.IAUService;
import com.sltunion.cloudy.web.action.BaseAction;

/**
 * 
 * @author sundial
 * 
 */
public class IAUReportAction extends BaseAction {

	private static final long serialVersionUID = -6849497894167673017L;
	private String ci;
	private String ma;
	private String ip;
	private String op;
	private String cn;
	private Integer st;
	private String sv;
	private String i6;
	private String fa;// 0 ntfs 1 fat32 2 fat 3 其他
	private String sn;// 磁盘序列号
	private Integer iv;// 是否虚拟机 0 真机 1 虚拟机
	private Integer hour;
	private String createdate;
	private String createtime;
	private int result;

	@Resource
	protected IAUService iauService;

	public String iaureport() {
		ip = getIPAddr();
		hour = DateUtil.getHour();
		createdate = DateUtil.getDateStr();
		createtime = DateUtil.getDatetime();
		Long channelid = 1l;// 渠道
		Integer sver = 0;
		Integer is64 = 0;
		Integer fat = 0;
		
		long start = System.currentTimeMillis();
		if (ObjectUtil.isNotEmpty(ci) && ObjectUtil.isNotEmpty(sn)) {
			ma = ObjectUtil.toBlank(ma);
			sn = ObjectUtil.toBlank(sn);

			try {
				ci = ci.split("\\D")[0];
				channelid = Long.parseLong(ci.trim());
			} catch (Exception e) {
				channelid = 1l;
			}
			try {
				sver = Integer.valueOf(sv);
			} catch (Exception e) {
				sver = 0;
			}
			try {
				is64 = Integer.valueOf(i6);
			} catch (Exception e) {
				is64 = 0;
			}
			try {
				fat = Integer.valueOf(fa);
			} catch (Exception e) {
				fat = 3;
			}
			if(iv==null){
				iv = 0;
			}
			try {
				result = iauService.iauReport(channelid, ma, ip, op, cn, st, sver, is64, fat, sn,
						iv, hour, createdate, createtime);
			} catch (DuplicateKeyException e) {
				logger.error(e.getMessage());
			} catch (Exception e) {
				logger.error("", e);
			}
			logger.info("安装活跃卸载上报[渠道id:" + ci + ",disknum:" + sn + ",mac:" + ma + ",operatestatus:"
					+ op + ",isvm:" + iv + ",result:" + result + ",耗时："
					+ (System.currentTimeMillis() - start) + "ms]");
		}
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

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public Integer getSt() {
		return st;
	}

	public void setSt(Integer st) {
		this.st = st;
	}

	public String getSv() {
		return sv;
	}

	public void setSv(String sv) {
		this.sv = sv;
	}

	public String getI6() {
		return i6;
	}

	public void setI6(String i6) {
		this.i6 = i6;
	}

	public void setFa(String fa) {
		this.fa = fa;
	}

	public String getFa() {
		return fa;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getSn() {
		return sn;
	}

	public void setIv(Integer iv) {
		this.iv = iv;
	}

	public Integer getIv() {
		return iv;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
