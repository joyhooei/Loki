package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import com.sltunion.cloudy.persistent.model.THomepagechannel;
/**
 * @author sundial
 * @date 2014-02-14 14:18:21
 */
public interface THomepagechannelMapper {
	public int insertSelective(THomepagechannel record);

	public int updateByPrimaryKeySelective(THomepagechannel record);

	public int deleteByPrimaryKey(THomepagechannel primarykey);
	public THomepagechannel selectByPrimaryKey(THomepagechannel primarykey);

	public List<THomepagechannel> selectAll();

	public long countAll();

}