package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import com.sltunion.cloudy.persistent.model.TStatistics;
/**
 * @author sundial
 * @date 2014-02-24 01:19:49
 */
public interface TStatisticsMapper {
	public int insertSelective(TStatistics record);

	public int updateByPrimaryKeySelective(TStatistics record);

	public int deleteByPrimaryKey(TStatistics primarykey);
	public TStatistics selectByPrimaryKey(TStatistics primarykey);

	public List<TStatistics> selectAll();

	public long countAll();

	public TStatistics selectByTable(TStatistics entity);

	public int updateByTable(TStatistics entity);

}