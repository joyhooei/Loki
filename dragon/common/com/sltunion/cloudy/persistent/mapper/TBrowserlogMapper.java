package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;
import com.sltunion.cloudy.persistent.model.TBrowserlog;
/**
 * @author sundial
 * @date 2014-03-06 03:03:45
 */
public interface TBrowserlogMapper {
	public int insertSelective(TBrowserlog record);

	public int updateByPrimaryKeySelective(TBrowserlog record);

	public int deleteByPrimaryKey(TBrowserlog primarykey);
	public TBrowserlog selectByPrimaryKey(TBrowserlog primarykey);

	public List<TBrowserlog> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);
	
	public int updateByStatisticsSelective(TBrowserlog tBrowserlog);

	public List<Map<String, Object>> getChartResultList(Map<String, Object> params);
}