package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;
import com.sltunion.cloudy.persistent.model.TIaudailylog;
/**
 * @author sundial
 * @date 2014-02-08 10:56:22
 */
public interface TIaudailylogMapper {
	public int insertSelective(TIaudailylog record);

	public int updateByPrimaryKeySelective(TIaudailylog record);

	public int deleteByPrimaryKey(TIaudailylog primarykey);
	public TIaudailylog selectByPrimaryKey(TIaudailylog primarykey);

	public List<TIaudailylog> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);

	public List<Map<String, Object>> getChartResultList(Map<String, Object> params);

	public int updateByStatisticsSelective(TIaudailylog tIaudailylog);
}