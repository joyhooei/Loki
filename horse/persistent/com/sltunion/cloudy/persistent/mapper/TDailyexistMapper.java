package com.sltunion.cloudy.persistent.mapper;

import com.sltunion.cloudy.persistent.model.TDailyexist;

/**
 * @author sundial
 * @date 2014-02-23 23:52:16
 */
public interface TDailyexistMapper {
	public int insertSelective(TDailyexist record);

	public TDailyexist selectByPrimaryKey(TDailyexist primarykey);
}