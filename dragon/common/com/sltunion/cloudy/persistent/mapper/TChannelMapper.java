package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.sltunion.cloudy.persistent.model.TChannel;
/**
 * @author sundial
 * @date 2014-02-07 15:30:54
 */
public interface TChannelMapper {
	@TriggersRemove(cacheName = "tChannel", removeAll = true)
	public int insertSelective(TChannel record);
	
	@TriggersRemove(cacheName = "tChannel", removeAll = true)
	public int updateByPrimaryKeySelective(TChannel record);
	
	@TriggersRemove(cacheName = "tChannel", removeAll = true)
	public int deleteByPrimaryKey(TChannel primarykey);
	
	public TChannel selectByPrimaryKey(TChannel primarykey);
	
	@Cacheable(cacheName = "tChannel")
	public List<TChannel> selectAll();
	
	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);
	
	@Cacheable(cacheName = "tChannel")
	public List<TChannel> selectChannelListByUserid(Long userid);

	public List<TChannel> selectIdleChannelListByTablename(@Param(value="name") String name);
}