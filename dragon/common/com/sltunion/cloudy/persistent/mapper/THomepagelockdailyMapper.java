package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;
import com.sltunion.cloudy.persistent.model.THomepagelockdaily;
/**
 * @author sundial
 * @date 2014-02-07 15:54:50
 */
public interface THomepagelockdailyMapper {
	public int insertSelective(THomepagelockdaily record);

	public int updateByPrimaryKeySelective(THomepagelockdaily record);

	public int deleteByPrimaryKey(THomepagelockdaily primarykey);
	public THomepagelockdaily selectByPrimaryKey(THomepagelockdaily primarykey);

	public List<THomepagelockdaily> selectAll();

	public long countAll();

	public List<Map<String,Object>> selectPager(Map<String,Object> params);

	public long countPager(Map<String, Object> params);

	public Map<String, Object> selectFooter(Map<String, Object> params);

	public List<Map<String, Object>> getChartResultList(Map<String, Object> params);

	public int updateByStatisticsSelective(THomepagelockdaily tHomepagelockdaily);
}