package com.sltunion.cloudy.persistent.mapper;
import java.util.List;
import com.sltunion.cloudy.persistent.model.TFilesystemlog;
/**
 * @author sundial
 * @date 2014-02-24 00:13:02
 */
public interface TFilesystemlogMapper {
	public int insertSelective(TFilesystemlog record);

	public int updateByPrimaryKeySelective(TFilesystemlog record);

	public int deleteByPrimaryKey(TFilesystemlog primarykey);
	public TFilesystemlog selectByPrimaryKey(TFilesystemlog primarykey);

	public List<TFilesystemlog> selectAll();

	public long countAll();

	public int updateByStatisticsSelective(TFilesystemlog tFilesystemlog);

}