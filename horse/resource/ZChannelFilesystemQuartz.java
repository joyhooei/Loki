//package com.sltunion.cloudy.quartz;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Controller;
//
//import com.sltunion.cloudy.business.Const;
//import com.sltunion.cloudy.common.utils.DateUtil;
//import com.sltunion.cloudy.common.utils.LogerUtil;
//import com.sltunion.cloudy.common.utils.ObjectUtil;
//import com.sltunion.cloudy.persistent.model.TChannel;
//import com.sltunion.cloudy.persistent.model.TFilesystemlog;
//import com.sltunion.cloudy.persistent.model.TInstalllog;
//import com.sltunion.cloudy.service.ChannelService;
//import com.sltunion.cloudy.service.SourceService;
//import com.sltunion.cloudy.service.StatisticService;
//import com.sltunion.cloudy.service.TargetService;
//
///**
// * 
// * @author sundial
// * 
// */
//@Controller("channelFilesystemQuartz")
//public class ZChannelFilesystemQuartz {
//	@Resource
//	private SourceService sourceService;
//	@Resource
//	private TargetService targetService;
//	@Resource
//	private ChannelService channelService;
//	@Resource
//	private StatisticService statisticService;
//
//	/**
//	 * 测试用
//	 */
//	public void mdoStatistics() {
//		LogerUtil.logger.info("filesystemQuartz start");
//		String[] currdateA = { "2013-11-14" };
//		for (String currdate : currdateA) {
//			filesystemStatistics(currdate, null);
//		}
//		LogerUtil.logger.info("filesystemQuartz end");
//	}
//
//	public void doStatistics() {
//		LogerUtil.logger.info("filesystemQuartz start");
//		String currdate = DateUtil.getDateStr();
//		String time = DateUtil.getTimeStr();
//		String[] timeArr = time.split(":");
//		int hour = array2Int(timeArr, 0);
//		int minute = array2Int(timeArr, 1);
//		int second = array2Int(timeArr, 2);
//		int deff = DateUtil.deff();
//		int minutedeff = DateUtil.deff(minute, second);
//		if (deff > (23 * 3600 + 59 * 60)) {
//			currdate = DateUtil.getDateStr(currdate, -1);
//			hour = 23;
//		} else {
//			if (minutedeff > (59 * 60)) {
//				hour = hour - 1;
//			}
//		}
//		filesystemStatistics(currdate, hour);
//		LogerUtil.logger.info("filesystemQuartz end");
//	}
//
//	protected void filesystemStatistics(String currdate, Integer hour) {
//		List<TChannel> channelList = channelService.findAll();
//		if (channelList != null && channelList.size() > 0) {
//			long sourceid = statisticService.findSourceid(Const.Table.T_INSTALLLOG,
//					Const.Table.T_FILESYSTEMLOG);
//
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("createdate", currdate);
//			param.put("hour", hour);
//			param.put("limit", Const.Statistics.LIMIT);
//
//			param.put("id", sourceid);
//			List<TInstalllog> installList = sourceService.findInstalllogByStatistics(param);
//			if (installList != null && installList.size() > 0) {
//				filter(installList);
//
//				for (TChannel tChannel : channelList) {
//					Long channelid = tChannel.getId();
//					Long userid = tChannel.getUserid();
//					TFilesystemlog tFilesystemlog = new TFilesystemlog();
//					tFilesystemlog.setUserid(userid);
//					tFilesystemlog.setChannelid(channelid);
//					tFilesystemlog.setCreatedate(currdate);
//
//					filesystemlogStatistics(tFilesystemlog, channelid, installList);
//
//					if (tFilesystemlog.getNtfsnum() > 0 || tFilesystemlog.getFat32num() > 0
//							|| tFilesystemlog.getFatnum() > 0 || tFilesystemlog.getOthernum() > 0) {
//						updateFilesystemlogCount(tFilesystemlog);
//					}
//				}
//			}
//		}
//	}
//
//	protected void filter(List<TInstalllog> installList) {
//		List<TInstalllog> removeList = new ArrayList<TInstalllog>();
//		long sourceid = installList.get(0).getId();
//		for (TInstalllog entity : installList) {
//			sourceid = entity.getId();
//			long ipc = statisticService.countExist(entity.getIp());
//			long diskc = statisticService.countExist(entity.getDisknum());
//			if (ipc > 0 || diskc > 0) {
//				removeList.add(entity);
//			} else {
//				continue;
//			}
//		}
//		if (!removeList.isEmpty()) {
//			installList.removeAll(removeList);
//		}
//		statisticService.updateSourceid(Const.Table.T_INSTALLLOG, Const.Table.T_FILESYSTEMLOG,
//				sourceid);
//	}
//
//	protected void filesystemlogStatistics(TFilesystemlog tFilesystemlog, Long channelid,
//			List<TInstalllog> installList) {
//		int ntfsnum = 0;
//		int fatnum = 0;
//		int fat32num = 0;
//		int othernum = 0;
//		if (installList != null && installList.size() > 0) {
//			for (TInstalllog tInstalllog : installList) {
//				if (channelid == tInstalllog.getChannelid()) {
//					String useragent = tInstalllog.getUseragent();
//					// 系统版本不能为空
//					if (ObjectUtil.isNotEmpty(useragent)) {
//						if (useragent.contains("FS NTFS;")) {
//							ntfsnum++;
//						} else if (useragent.contains("FS FAT32;")) {
//							fat32num++;
//						} else if (useragent.contains("FS FAT;")) {
//							fatnum++;
//						} else {
//							othernum++;
//						}
//					} else {
//						othernum++;
//					}
//				}
//			}
//		}
//		tFilesystemlog.setNtfsnum(ntfsnum);
//		tFilesystemlog.setFat32num(fat32num);
//		tFilesystemlog.setFatnum(fatnum);
//		tFilesystemlog.setOthernum(othernum);
//	}
//
//	protected int updateFilesystemlogCount(TFilesystemlog tFilesystemlog) {
//		return targetService.updateTFilesystemlogCount(tFilesystemlog);
//	}
//
//	protected int array2Int(String[] array, int index) {
//		int ret = 0;
//		if (array.length >= index + 1) {
//			ret = Integer.valueOf(array[index]);
//		}
//		return ret;
//	}
//}
