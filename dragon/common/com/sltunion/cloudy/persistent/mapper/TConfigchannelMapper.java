package com.sltunion.cloudy.persistent.mapper;
import java.util.List;

import com.sltunion.cloudy.persistent.model.TConfigchannel;
/**
 * @author sundial
 * @date 2014-02-15 22:11:47
 */

public interface TConfigchannelMapper {
	public int insertSelective(TConfigchannel record);

	public int updateByPrimaryKeySelective(TConfigchannel record);

	public int deleteByPrimaryKey(TConfigchannel primarykey);
	public TConfigchannel selectByPrimaryKey(TConfigchannel primarykey);

	public List<TConfigchannel> selectAll();

	public long countAll();

}