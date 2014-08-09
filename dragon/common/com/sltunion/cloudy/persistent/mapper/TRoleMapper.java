package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;
import com.sltunion.cloudy.persistent.model.TRole;
/**
 * @author sundial
 * @date 2014-02-08 16:00:16
 */
public interface TRoleMapper {
	public int insertSelective(TRole record);

	public int updateByPrimaryKeySelective(TRole record);

	public int deleteByPrimaryKey(TRole primarykey);
	public TRole selectByPrimaryKey(TRole primarykey);

	public List<TRole> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);
}