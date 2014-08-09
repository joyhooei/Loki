package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.sltunion.cloudy.persistent.model.THomepagelocklog;
/**
 * @author sundial
 * @date 2014-02-24 00:12:14
 */
public interface THomepagelocklogMapper {
	public int insertSelective(THomepagelocklog record);

	public int updateByPrimaryKeySelective(THomepagelocklog record);

	public int deleteByPrimaryKey(THomepagelocklog primarykey);
	public THomepagelocklog selectByPrimaryKey(THomepagelocklog primarykey);

	public List<THomepagelocklog> selectAll();

	public long countAll();

	public List<THomepagelocklog> selectByStatistics(Map<String, Object> param);

}