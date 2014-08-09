package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.googlecode.ehcache.annotations.Cacheable;
import com.sltunion.cloudy.persistent.model.TConfigini;
/**
 * @author sundial
 * @date 2014-02-16 01:21:59
 */
public interface TConfiginiMapper {
	public int insertSelective(TConfigini record);

	public int updateByPrimaryKeySelective(TConfigini record);

	public int deleteByPrimaryKey(TConfigini primarykey);
	public TConfigini selectByPrimaryKey(TConfigini primarykey);

	public List<TConfigini> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);
	
	@Cacheable(cacheName="tConfigini")
	public TConfigini selectByPull(Map<String, Object> param);
}