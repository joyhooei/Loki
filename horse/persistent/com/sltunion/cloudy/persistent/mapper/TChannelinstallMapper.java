package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import com.sltunion.cloudy.persistent.model.TChannelinstall;
/**
 * @author sundial
 * @date 2014-02-24 00:13:24
 */
public interface TChannelinstallMapper {
	public int insertSelective(TChannelinstall record);

	public int updateByPrimaryKeySelective(TChannelinstall record);

	public int deleteByPrimaryKey(TChannelinstall primarykey);
	public TChannelinstall selectByPrimaryKey(TChannelinstall primarykey);

	public List<TChannelinstall> selectAll();

	public long countAll();

}