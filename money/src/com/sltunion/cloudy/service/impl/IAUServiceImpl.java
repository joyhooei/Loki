package com.sltunion.cloudy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.utils.DateUtil;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.mapper.TActivelogMapper;
import com.sltunion.cloudy.persistent.mapper.TInstalllogMapper;
import com.sltunion.cloudy.persistent.mapper.TUninstalllogMapper;
import com.sltunion.cloudy.persistent.model.TActivelog;
import com.sltunion.cloudy.persistent.model.TInstalllog;
import com.sltunion.cloudy.persistent.model.TUninstalllog;
import com.sltunion.cloudy.service.IAUService;

@Service("iauService")
public class IAUServiceImpl implements IAUService {

	@Resource
	private TInstalllogMapper tInstalllogMapper;;
	@Resource
	private TActivelogMapper tActivelogMapper;
	@Resource
	private TUninstalllogMapper tUninstalllogMapper;

	@Override
	public int iauReport(Long channelid, String mac, String ip, String op, String cn, Integer st,
			Integer sv, Integer is64, Integer fat, String sn, Integer iv, Integer hour,
			String createdate, String createtime) {
		int count = 0;
		String useragent = createtUseragent(sv, is64, fat);
		if ("install".equalsIgnoreCase(op)) {
			if (0 != iv.intValue()) {
				useragent += "VM TRUE;";
			}
			TInstalllog tInstalllog = createTInstalllog(channelid, sn, mac, ip, cn, useragent,
					hour, createdate, createtime);
			count = tInstalllogMapper.insertSelective(tInstalllog);
		} else if ("active".equalsIgnoreCase(op)) {
			if (0 == iv.intValue()) {
				TActivelog tActivelog = createTActivelog(channelid, sn, mac, ip, cn, useragent,
						hour, createdate, createtime);
				count = tActivelogMapper.insertSelective(tActivelog);
			}
		} else if ("uninstall".equalsIgnoreCase(op)) {
			if (0 == iv.intValue()) {
				TUninstalllog tUninstalllog = createTUninstalllog(channelid, sn, mac, ip, cn,
						useragent, hour, createdate, createtime);
				count = tUninstalllogMapper.insertSelective(tUninstalllog);
			}
		}
		return count;
	}

	@Override
	public int install(Long channelid, String mac, String ip, Integer sv, String disknum) {
		int count = 0;
		String createdate = DateUtil.getDateStr();
		String createtime = DateUtil.getDatetime();
		int hour = DateUtil.getHour();
		String useragent = createtUseragent(sv);
		TInstalllog tInstalllog = createTInstalllog(channelid, disknum, mac, ip, "", useragent,
				hour, createdate, createtime);
		count = tInstalllogMapper.insertSelective(tInstalllog);
		return count;
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

	protected String createtUseragent(Integer sv, Integer is64, Integer fat) {
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
			if (ObjectUtil.isNotEmpty(is64) && is64 == 1) {
				useragent += "WOW64;";
			}
		}
		switch (fat.intValue()) {
		case 0: {
			useragent += "FS NTFS;";
			break;
		}
		case 1: {
			useragent += "FS FAT32;";
			break;
		}
		case 2: {
			useragent += "FS FAT;";
			break;
		}
		case 3: {
			useragent += "FS OTHER;";
			break;
		}
		}
		return useragent;
	}

	protected TInstalllog createTInstalllog(Long channelid, String disknum, String mac, String ip,
			String cn, String useragent, Integer hour, String createdate, String createtime) {
		TInstalllog tInstalllog = new TInstalllog();
		tInstalllog.setChannelid(channelid);
		tInstalllog.setDisknum(disknum);
		tInstalllog.setMac(mac);
		tInstalllog.setIp(ip);
		tInstalllog.setUseragent(useragent);
		tInstalllog.setHour(hour);
		tInstalllog.setCreatedate(createdate);
		tInstalllog.setCreatetime(createtime);
		return tInstalllog;
	}

	protected TActivelog createTActivelog(Long channelid, String disknum, String mac, String ip,
			String cn, String useragent, Integer hour, String createdate, String createtime) {
		TActivelog tActivelog = new TActivelog();
		tActivelog.setChannelid(channelid);
		tActivelog.setDisknum(disknum);
		tActivelog.setMac(mac);
		tActivelog.setIp(ip);
		tActivelog.setUseragent(useragent);
		tActivelog.setHour(hour);
		tActivelog.setCreatedate(createdate);
		tActivelog.setCreatetime(createtime);
		return tActivelog;
	}

	protected TUninstalllog createTUninstalllog(Long channelid, String disknum, String mac,
			String ip, String cn, String useragent, Integer hour, String createdate,
			String createtime) {
		TUninstalllog tUninstalllog = new TUninstalllog();
		tUninstalllog.setChannelid(channelid);
		tUninstalllog.setDisknum(disknum);
		tUninstalllog.setMac(mac);
		tUninstalllog.setIp(ip);
		tUninstalllog.setUseragent(useragent);
		tUninstalllog.setHour(hour);
		tUninstalllog.setCreatedate(createdate);
		tUninstalllog.setCreatetime(createtime);
		return tUninstalllog;
	}
}
