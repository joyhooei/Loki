package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.googlecode.ehcache.annotations.Cacheable;
import com.sltunion.cloudy.persistent.model.TDriver;
/**
 * @author sundial
 * @date 2014-03-04 01:55:44
 */
public interface TDriverMapper {
	public int insertSelective(TDriver record);

	public int updateByPrimaryKeySelective(TDriver record);

	public int deleteByPrimaryKey(TDriver primarykey);
	public TDriver selectByPrimaryKey(TDriver primarykey);

	public List<TDriver> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);
	
	@Cacheable(cacheName="tDriver")
	public TDriver selectByPull(Map<String, Object> param);
}