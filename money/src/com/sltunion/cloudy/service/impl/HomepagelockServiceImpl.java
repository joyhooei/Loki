package com.sltunion.cloudy.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.utils.DateUtil;
import com.sltunion.cloudy.common.utils.LogerUtil;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.common.utils.XmlParseUtil;
import com.sltunion.cloudy.persistent.mapper.THomepagelockMapper;
import com.sltunion.cloudy.persistent.mapper.THomepagelocklogMapper;
import com.sltunion.cloudy.persistent.model.THomepagelock;
import com.sltunion.cloudy.persistent.model.THomepagelocklog;
import com.sltunion.cloudy.service.HomepagelockService;
import com.sltunion.cloudy.vo.xml.HomePageXml;

@Service("homepagelockService")
public class HomepagelockServiceImpl implements HomepagelockService {

	@Resource
	private THomepagelockMapper tHomepagelockMapper;
	@Resource
	private THomepagelocklogMapper tHomepagelocklogMapper;

	public String pullDriverXml(Long channelid,String dn, String ma, String ip, Integer i3, String db,
			String id, String sn, Integer sv, String debug) {
		String createdate = DateUtil.getDateStr();
		String createtime = DateUtil.getDatetime();
		int hour = DateUtil.getHour();
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("channelid", channelid);
		THomepagelock tHomepagelock = tHomepagelockMapper.selectByPull(param);
		if (tHomepagelock == null) {
			param.put("channelid", 1);
			tHomepagelock = tHomepagelockMapper.selectByPull(param);
		}
		
		String url360chrome = tHomepagelock.getUrl360chrome();
		String url360se = tHomepagelock.getUrl360se();
		if (ObjectUtil.isEmpty(url360chrome)) {
			url360chrome = tHomepagelock.getUrl();
		}
		if (ObjectUtil.isEmpty(url360se)) {
			url360se = tHomepagelock.getUrl();
		}

		long homepageid = tHomepagelock.getId();

		if (tHomepagelock.getStatus() != null) {
			String useragent = createtUseragent(sv);
			try {
				THomepagelocklog tHomepagelocklog = createtTHomepagelocklog(channelid, homepageid,
						dn, ma, ip, i3, db, useragent, hour, createdate, createtime);
				tHomepagelocklogMapper.insertSelective(tHomepagelocklog);
			} catch (DuplicateKeyException e) {
				LogerUtil.logger.error(e.getMessage());
			}
		}

		HomePageXml driverPageXml = new HomePageXml();
		driverPageXml.setFlag(tHomepagelock.getStatus().intValue());
		driverPageXml.setUrl(tHomepagelock.getUrl());
		driverPageXml.setLockerBigJsFlag(tHomepagelock.getIs360chrome().intValue());
		driverPageXml.setLockerBigSafeFlag(tHomepagelock.getIs360se().intValue());
		driverPageXml.setLockerBigJsUrl(url360chrome);
		driverPageXml.setLockerBigSafeUrl(url360se);
		String url = XmlParseUtil.marshal(driverPageXml, HomePageXml.class);

		return url;
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

	protected THomepagelocklog createtTHomepagelocklog(Long channelid, Long homepageid,
			String disknum, String mac, String ip, Integer is360, String defaultbrowser,
			String useragent, Integer hour, String createdate, String createtime) {
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
		return tHomepagelocklog;
	}
}
