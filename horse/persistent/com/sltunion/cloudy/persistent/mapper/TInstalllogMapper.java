package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.sltunion.cloudy.persistent.model.TInstalllog;
/**
 * @author sundial
 * @date 2014-02-24 00:10:34
 */
public interface TInstalllogMapper {
	public int insertSelective(TInstalllog record);

	public int updateByPrimaryKeySelective(TInstalllog record);

	public int deleteByPrimaryKey(TInstalllog primarykey);
	public TInstalllog selectByPrimaryKey(TInstalllog primarykey);

	public List<TInstalllog> selectAll();

	public long countAll();
	
	public List<TInstalllog> selectByStatistics(Map<String, Object> param);
}