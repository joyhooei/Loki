package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import com.sltunion.cloudy.persistent.model.TDriverchannel;
/**
 * @author sundial
 * @date 2014-03-04 04:21:51
 */
public interface TDriverchannelMapper {
	public int insertSelective(TDriverchannel record);

	public int updateByPrimaryKeySelective(TDriverchannel record);

	public int deleteByPrimaryKey(TDriverchannel primarykey);
	public TDriverchannel selectByPrimaryKey(TDriverchannel primarykey);

	public List<TDriverchannel> selectAll();

	public long countAll();

}