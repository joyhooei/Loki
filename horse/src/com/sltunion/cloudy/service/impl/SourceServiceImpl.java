package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.persistent.mapper.TActivelogMapper;
import com.sltunion.cloudy.persistent.mapper.TDriverdownlogMapper;
import com.sltunion.cloudy.persistent.mapper.THomepagelocklogMapper;
import com.sltunion.cloudy.persistent.mapper.TInstalllogMapper;
import com.sltunion.cloudy.persistent.mapper.TUninstalllogMapper;
import com.sltunion.cloudy.persistent.model.TActivelog;
import com.sltunion.cloudy.persistent.model.TDriverdownlog;
import com.sltunion.cloudy.persistent.model.THomepagelocklog;
import com.sltunion.cloudy.persistent.model.TInstalllog;
import com.sltunion.cloudy.persistent.model.TUninstalllog;
import com.sltunion.cloudy.service.SourceService;

@Service("sourceService")
public class SourceServiceImpl implements SourceService {
	@Resource
	private TInstalllogMapper tInstalllogMapper;
	@Resource
	private TActivelogMapper tActivelogMapper;
	@Resource
	private TUninstalllogMapper tUninstalllogMapper;
	@Resource
	private TDriverdownlogMapper tDriverdownlogMapper;
	@Resource
	private THomepagelocklogMapper tHomepagelocklogMapper;
	
	@Override
	public List<TInstalllog> findInstalllogByStatistics(Map<String, Object> param) {
		return tInstalllogMapper.selectByStatistics(param);
	}

	@Override
	public List<TActivelog> findActivelogByStatistics(Map<String, Object> param) {
		return tActivelogMapper.selectByStatistics(param);
	}
	
	@Override
	public List<TUninstalllog> findUninstalllogByStatistics(Map<String, Object> param) {
		return tUninstalllogMapper.selectByStatistics(param);
	}

	@Override
	public List<TDriverdownlog> findDriverdownlogByStatistics(Map<String, Object> param) {
		return tDriverdownlogMapper.selectByStatistics(param);
	}

	@Override
	public List<THomepagelocklog> findHomepagelocklogByStatistics(Map<String, Object> param) {
		return tHomepagelocklogMapper.selectByStatistics(param);
	}
}
