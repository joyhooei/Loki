package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.sltunion.cloudy.persistent.model.TDriverdownlog;
/**
 * @author sundial
 * @date 2014-02-24 00:14:44
 */
public interface TDriverdownlogMapper {
	public int insertSelective(TDriverdownlog record);

	public int updateByPrimaryKeySelective(TDriverdownlog record);

	public int deleteByPrimaryKey(TDriverdownlog primarykey);
	public TDriverdownlog selectByPrimaryKey(TDriverdownlog primarykey);

	public List<TDriverdownlog> selectAll();

	public long countAll();

	public List<TDriverdownlog> selectByStatistics(Map<String, Object> param);
}