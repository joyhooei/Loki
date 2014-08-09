package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.sltunion.cloudy.persistent.model.TUser;
/**
 * @author sundial
 * @date 2014-02-07 11:09:24
 */
public interface TUserMapper {
	@TriggersRemove(cacheName = "tUser", removeAll = true)
	public int insertSelective(TUser record);

	@TriggersRemove(cacheName = "tUser", removeAll = true)
	public int updateByPrimaryKeySelective(TUser record);

	@TriggersRemove(cacheName = "tUser", removeAll = true)
	public int deleteByPrimaryKey(TUser primarykey);
	
	public TUser selectByPrimaryKey(TUser primarykey);
	
	@Cacheable(cacheName = "tUser")
	public List<TUser> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);

	public TUser selectUserByUsername(String username);
}