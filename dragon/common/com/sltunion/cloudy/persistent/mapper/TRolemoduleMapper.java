package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import com.sltunion.cloudy.persistent.model.TRolemodule;
/**
 * @author sundial
 * @date 2014-02-15 22:15:27
 */
public interface TRolemoduleMapper {
	public int insertSelective(TRolemodule record);

	public int updateByPrimaryKeySelective(TRolemodule record);

	public int deleteByPrimaryKey(TRolemodule primarykey);
	public TRolemodule selectByPrimaryKey(TRolemodule primarykey);

	public List<TRolemodule> selectAll();

	public long countAll();

}