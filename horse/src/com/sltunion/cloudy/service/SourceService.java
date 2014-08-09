package com.sltunion.cloudy.service;

import java.util.List;
import java.util.Map;

import com.sltunion.cloudy.persistent.model.TActivelog;
import com.sltunion.cloudy.persistent.model.TDriverdownlog;
import com.sltunion.cloudy.persistent.model.THomepagelocklog;
import com.sltunion.cloudy.persistent.model.TInstalllog;
import com.sltunion.cloudy.persistent.model.TUninstalllog;

public interface SourceService {

	public List<TInstalllog> findInstalllogByStatistics(Map<String, Object> param);

	public List<TActivelog> findActivelogByStatistics(Map<String, Object> param);

	public List<TUninstalllog> findUninstalllogByStatistics(Map<String, Object> param);

	public List<THomepagelocklog> findHomepagelocklogByStatistics(Map<String, Object> param);

	public List<TDriverdownlog> findDriverdownlogByStatistics(Map<String, Object> param);
}
