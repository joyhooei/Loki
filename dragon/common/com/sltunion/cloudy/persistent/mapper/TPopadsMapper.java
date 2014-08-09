package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.googlecode.ehcache.annotations.Cacheable;
import com.sltunion.cloudy.persistent.model.TPopads;
/**
 * @author sundial
 * @date 2014-03-04 01:54:31
 */
public interface TPopadsMapper {
	public int insertSelective(TPopads record);

	public int updateByPrimaryKeySelective(TPopads record);

	public int deleteByPrimaryKey(TPopads primarykey);
	public TPopads selectByPrimaryKey(TPopads primarykey);

	public List<TPopads> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);
	
	@Cacheable(cacheName="tPopads")
	public List<TPopads> selectByPull(Map<String, Object> param);
}