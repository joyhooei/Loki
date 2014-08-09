package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import java.util.Map;

import com.sltunion.cloudy.persistent.model.TActivelog;
/**
 * @author sundial
 * @date 2014-02-24 00:11:30
 */
public interface TActivelogMapper {
	public int insertSelective(TActivelog record);

	public int updateByPrimaryKeySelective(TActivelog record);

	public int deleteByPrimaryKey(TActivelog primarykey);
	public TActivelog selectByPrimaryKey(TActivelog primarykey);

	public List<TActivelog> selectAll();

	public long countAll();
	
	public List<TActivelog> selectByStatistics(Map<String, Object> param);

}