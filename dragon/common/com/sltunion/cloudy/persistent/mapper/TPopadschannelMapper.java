package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import com.sltunion.cloudy.persistent.model.TPopadschannel;
/**
 * @author sundial
 * @date 2014-03-04 04:21:26
 */
public interface TPopadschannelMapper {
	public int insertSelective(TPopadschannel record);

	public int updateByPrimaryKeySelective(TPopadschannel record);

	public int deleteByPrimaryKey(TPopadschannel primarykey);
	public TPopadschannel selectByPrimaryKey(TPopadschannel primarykey);

	public List<TPopadschannel> selectAll();

	public long countAll();

}