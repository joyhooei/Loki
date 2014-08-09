package com.sltunion.cloudy.service;

import java.util.List;

import com.sltunion.cloudy.persistent.model.TFilesystemlog;


/**
 * 
 * @author sundial
 *
 */
public interface FilesystemlogService {
	public int updateTFilesystemlogCount(TFilesystemlog tFilesystemlog);

	public long countTFilesystemlogSum(Long userid, Long channelid);

	public List<TFilesystemlog> findTFilesystemlogSum(Long userid, Long channelid, long startIndex,
			long pageSize);
}
