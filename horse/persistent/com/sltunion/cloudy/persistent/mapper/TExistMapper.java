package com.sltunion.cloudy.persistent.mapper;
import com.sltunion.cloudy.persistent.model.TExist;
/**
 * @author sundial
 * @date 2014-02-23 23:50:52
 */
public interface TExistMapper {
	public int insertSelective(TExist record);

	public TExist selectByPrimaryKey(TExist primarykey);
}