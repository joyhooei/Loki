package com.sltunion.cloudy.persistent.mapper;

import java.util.List;
import java.util.Map;
import com.sltunion.cloudy.persistent.model.TCostlog;

/**
 * @author sundial
 * @date 2014-05-15 21:56:59
 */
public interface TCostlogMapper {
	public int insertSelective(TCostlog record);

	public int updateByPrimaryKeySelective(TCostlog record);

	public int deleteByPrimaryKey(TCostlog primarykey);

	public TCostlog selectByPrimaryKey(TCostlog primarykey);

	public List<TCostlog> selectAll();

	public long countAll();

	public List<Map<String, Object>> selectPager(Map<String, Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);

	public int insertByStatisticsSelective(Map<String, Object> param);
}