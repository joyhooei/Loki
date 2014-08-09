package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;
import com.sltunion.cloudy.persistent.model.TModule;
/**
 * @author sundial
 * @date 2014-02-10 09:37:22
 */
public interface TModuleMapper {
	public int insertSelective(TModule record);

	public int updateByPrimaryKeySelective(TModule record);

	public int deleteByPrimaryKey(TModule primarykey);
	public TModule selectByPrimaryKey(TModule primarykey);

	public List<TModule> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);

	public List<TModule> selectModuleByRoleid(Long roleid);

	public List<TModule> selectListByPid(long pid);

	public List<TModule> selectListByParams(Map<String, Object> params);
}