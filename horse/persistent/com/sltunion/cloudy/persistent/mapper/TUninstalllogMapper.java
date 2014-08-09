package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.sltunion.cloudy.persistent.model.TUninstalllog;
/**
 * @author sundial
 * @date 2014-02-24 00:10:57
 */
public interface TUninstalllogMapper {
	public int insertSelective(TUninstalllog record);

	public int updateByPrimaryKeySelective(TUninstalllog record);

	public int deleteByPrimaryKey(TUninstalllog primarykey);
	public TUninstalllog selectByPrimaryKey(TUninstalllog primarykey);

	public List<TUninstalllog> selectAll();

	public long countAll();
	
	public List<TUninstalllog> selectByStatistics(Map<String, Object> param);

}