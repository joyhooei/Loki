package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.googlecode.ehcache.annotations.Cacheable;
import com.sltunion.cloudy.persistent.model.THomepagelock;
/**
 * @author sundial
 * @date 2014-02-14 15:09:05
 */
public interface THomepagelockMapper {
	public int insertSelective(THomepagelock record);

	public int updateByPrimaryKeySelective(THomepagelock record);

	public int deleteByPrimaryKey(THomepagelock primarykey);
	public THomepagelock selectByPrimaryKey(THomepagelock primarykey);

	public List<THomepagelock> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);
	
	@Cacheable(cacheName="tHomepagelock")
	public THomepagelock selectByPull(Map<String, Object> param);
}