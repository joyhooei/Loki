package com.sltunion.cloudy.quartz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.common.utils.DateUtil;
import com.sltunion.cloudy.common.utils.LogerUtil;
import com.sltunion.cloudy.common.utils.ObjectUtil;
import com.sltunion.cloudy.persistent.model.TActivelog;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.TDriverdownlog;
import com.sltunion.cloudy.persistent.model.TFilesystemlog;
import com.sltunion.cloudy.persistent.model.TIaudailylog;
import com.sltunion.cloudy.persistent.model.TInstalllog;
import com.sltunion.cloudy.persistent.model.TUninstalllog;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.SourceService;
import com.sltunion.cloudy.service.StatisticService;
import com.sltunion.cloudy.service.TargetService;

/**
 * 
 * @author sundial
 * 
 */
@Controller("iauDailyQuartz")
public class IAUStatisticsQuartz {
	@Resource
	private SourceService sourceService;
	@Resource
	private TargetService targetService;
	@Resource
	private ChannelService channelService;
	@Resource
	private StatisticService statisticService;

	/**
	 * 测试用
	 */
	public void mdoStatistics() {
		LogerUtil.logger.info("iauDailyStatistics start");
		String[] currdateA = { "2014-03-04","2014-03-05" };
		int[] hourA = {0,2,4};
		for (String currdate : currdateA) {
			for (int hour : hourA) {
				iauDailyStatistics(currdate, hour);
			}
		}
		LogerUtil.logger.info("iauDailyStatistics end");
	}

	public void doStatistics() {
		LogerUtil.logger.info("iauDailyStatistics start");
		String currdate = DateUtil.getDateStr();
		String time = DateUtil.getTimeStr();
		String[] timeArr = time.split(":");
		int hour = array2Int(timeArr, 0);
		int minute = array2Int(timeArr, 1);
		int second = array2Int(timeArr, 2);
		int deff = DateUtil.deff();
		int minutedeff = DateUtil.deff(minute, second);
		if (deff > (23 * 3600 + 59 * 60)) {
			currdate = DateUtil.getDateStr(currdate, -1);
			hour = 23;
		} else {
			if (minutedeff > (59 * 60)) {
				hour = hour - 1;
			}
		}
		iauDailyStatistics(currdate, hour);
		LogerUtil.logger.info("iauDailyStatistics end");
	}

	protected void iauDailyStatistics(String currdate, Integer hour) {
		List<TChannel> channelList = channelService.findAll();
		List<TInstalllog> vmList = new ArrayList<TInstalllog>();
		if (channelList != null && channelList.size() > 0) {
			long install_sourceid = statisticService.findSourceid(Const.Table.T_INSTALLLOG,
					Const.Table.T_IAUDAILYLOG);
			long active_sourceid = statisticService.findSourceid(Const.Table.T_ACTIVELOG,
					Const.Table.T_IAUDAILYLOG);
			long uninstall_sourceid = statisticService.findSourceid(Const.Table.T_UNINSTALLLOG,
					Const.Table.T_IAUDAILYLOG);
			long down_sourceid = statisticService.findSourceid(Const.Table.T_DRIVERDOWN,
					Const.Table.T_IAUDAILYLOG);

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("createdate", currdate);
			param.put("hour", hour);
			param.put("limit", Const.Statistics.LIMIT);

			param.put("id", install_sourceid);
			List<TInstalllog> installList = sourceService.findInstalllogByStatistics(param);

			param.put("id", active_sourceid);
			List<TActivelog> activeList = sourceService.findActivelogByStatistics(param);

			param.put("id", uninstall_sourceid);
			List<TUninstalllog> uninstallList = sourceService.findUninstalllogByStatistics(param);

			param.put("id", down_sourceid);
			List<TDriverdownlog> driverdownlogList = sourceService.findDriverdownlogByStatistics(param);
			if (((installList != null && installList.size() > 0)
					|| (activeList != null && activeList.size() > 0)
					|| (uninstallList != null && uninstallList.size() > 0) || (driverdownlogList != null && driverdownlogList
					.size() > 0))) {
				filter(installList, vmList, activeList, uninstallList, driverdownlogList);

				// 文件系统统计 FAT FAT32 NTFS
				filesystemlogStatistics(installList, currdate);

				for (TChannel tChannel : channelList) {
					Long channelid = tChannel.getId();
					Integer status = tChannel.getStatus();
					if (status != Const.Status.DISABLE) {
						Long userid = tChannel.getUserid();
						float deduct = tChannel.getDeduct().floatValue();
						int startnum = tChannel.getStartnum().intValue();
						int total = statisticService.findChannelInstallNum(channelid);

						TIaudailylog tIaudailylog = new TIaudailylog();
						tIaudailylog.setUserid(userid);
						tIaudailylog.setChannelid(channelid);
						tIaudailylog.setCreatedate(currdate);
						tIaudailylog.setHour(hour);

						installStatistics(tIaudailylog, installList, status, deduct, startnum,
								total, vmList);
						activeStatistics(tIaudailylog, activeList);

						uninstallStatistics(tIaudailylog, uninstallList, status, deduct, startnum,
								total);

						driverdownStatistics(tIaudailylog, driverdownlogList);

						if (tIaudailylog.getInstallnum() > 0 || tIaudailylog.getActivenum() > 0
								|| tIaudailylog.getUninstallnum() > 0
								|| tIaudailylog.getDriverdown() > 0 || tIaudailylog.getVmnum() > 0) {
							updateIAUDailyCount(tIaudailylog);
						}
					}
				}
			}
		}
	}

	protected void filter(List<TInstalllog> installList, List<TInstalllog> vmList,
			List<TActivelog> activeList, List<TUninstalllog> uninstallList,
			List<TDriverdownlog> driverdownList) {
		if (installList != null && !installList.isEmpty()) {
			long sourceid = installList.get(0).getId();
			for (TInstalllog entity : installList) {
				sourceid = entity.getId();
				long ipc = statisticService.countExist(entity.getIp(),Const.Table.T_INSTALLEXIST);
				long diskc = statisticService.countExist(entity.getDisknum(),Const.Table.T_INSTALLEXIST);
				if (ipc > 0 || diskc > 0) {
					vmList.add(entity);
					if (diskc <= 0) {
						statisticService.saveExist(entity.getDisknum(),Const.Table.T_INSTALLEXIST);
					}else if(ipc <= 0){
						statisticService.saveExist(entity.getIp(),Const.Table.T_INSTALLEXIST);
					}
				} else {
					statisticService.saveExist(entity.getIp(),Const.Table.T_INSTALLEXIST);
					statisticService.saveExist(entity.getDisknum(),Const.Table.T_INSTALLEXIST);
				}
			}
			if (!vmList.isEmpty()) {
				installList.removeAll(vmList);
			}
			statisticService.updateSourceid(Const.Table.T_INSTALLLOG, Const.Table.T_IAUDAILYLOG,
					sourceid);
		}
		if (activeList != null && !activeList.isEmpty()) {
			List<TActivelog> removeList2 = new ArrayList<TActivelog>();
			long sourceid = activeList.get(0).getId();
			for (TActivelog entity : activeList) {
				sourceid = entity.getId();
				long ipc = statisticService.countExist(entity.getIp(),Const.Table.T_ACTIVEEXIST);
				long diskc = statisticService.countExist(entity.getDisknum(),Const.Table.T_ACTIVEEXIST);
				if (ipc > 0 || diskc > 0) {
					removeList2.add(entity);
					if (diskc <= 0) {
						statisticService.saveExist(entity.getDisknum(),Const.Table.T_ACTIVEEXIST);
					}else if(ipc <= 0){
						statisticService.saveExist(entity.getIp(),Const.Table.T_ACTIVEEXIST);
					}
				} else {
					statisticService.saveExist(entity.getIp(),Const.Table.T_ACTIVEEXIST);
					statisticService.saveExist(entity.getDisknum(),Const.Table.T_ACTIVEEXIST);
				}
			}
			if (!removeList2.isEmpty()) {
				activeList.removeAll(removeList2);
			}
			statisticService.updateSourceid(Const.Table.T_ACTIVELOG, Const.Table.T_IAUDAILYLOG,
					sourceid);
		}
		if (uninstallList != null && !uninstallList.isEmpty()) {
			List<TUninstalllog> removeList3 = new ArrayList<TUninstalllog>();
			long sourceid = uninstallList.get(0).getId();
			for (TUninstalllog entity : uninstallList) {
				sourceid = entity.getId();
				long ipc = statisticService.countExist(entity.getIp(),Const.Table.T_UNINSTALLEXIST);
				long diskc = statisticService.countExist(entity.getDisknum(),Const.Table.T_UNINSTALLEXIST);
				if (ipc > 0 || diskc > 0) {
					removeList3.add(entity);
					if (diskc <= 0) {
						statisticService.saveExist(entity.getDisknum(),Const.Table.T_UNINSTALLEXIST);
					}else if(ipc <= 0){
						statisticService.saveExist(entity.getIp(),Const.Table.T_UNINSTALLEXIST);
					}
				} else {
					statisticService.saveExist(entity.getIp(),Const.Table.T_UNINSTALLEXIST);
					statisticService.saveExist(entity.getDisknum(),Const.Table.T_UNINSTALLEXIST);
				}
			}
			if (!removeList3.isEmpty()) {
				uninstallList.removeAll(removeList3);
			}
			statisticService.updateSourceid(Const.Table.T_UNINSTALLLOG, Const.Table.T_IAUDAILYLOG,
					sourceid);
		}
		if (driverdownList != null && !driverdownList.isEmpty()) {
			List<TDriverdownlog> removeList4 = new ArrayList<TDriverdownlog>();
			long sourceid = driverdownList.get(0).getId();
			for (TDriverdownlog entity : driverdownList) {
				sourceid = entity.getId();
				long ipc = statisticService.countExist(entity.getIp(),Const.Table.T_DRIVERDOWNXIST);
				long diskc = statisticService.countExist(entity.getDisknum(),Const.Table.T_DRIVERDOWNXIST);
				if (ipc > 0 || diskc > 0) {
					removeList4.add(entity);
					if (diskc <= 0) {
						statisticService.saveExist(entity.getDisknum(),Const.Table.T_DRIVERDOWNXIST);
					}else if(ipc <= 0){
						statisticService.saveExist(entity.getIp(),Const.Table.T_DRIVERDOWNXIST);
					}
				} else {
					statisticService.saveExist(entity.getIp(),Const.Table.T_DRIVERDOWNXIST);
					statisticService.saveExist(entity.getDisknum(),Const.Table.T_DRIVERDOWNXIST);
				}
			}
			if (!removeList4.isEmpty()) {
				driverdownList.removeAll(removeList4);
			}
			statisticService.updateSourceid(Const.Table.T_DRIVERDOWN, Const.Table.T_IAUDAILYLOG,
					sourceid);
		}
	}

	protected void installStatistics(TIaudailylog tIaudailylog, List<TInstalllog> installList,
			int status, float deduct, int startnum, int total, List<TInstalllog> vmList) {
		int installnum = 0;
		int xpnum = 0;
		int win732num = 0;
		int win764num = 0;
		int win832num = 0;
		int win864num = 0;
		int othernum = 0;
		int vmnum = 0;
		long channelid = tIaudailylog.getChannelid();
		long userid = tIaudailylog.getUserid();
		List<TInstalllog> removeList = new ArrayList<TInstalllog>();
		
		if (installList != null && installList.size() > 0) {
			for (TInstalllog tInstalllog : installList) {
				if (channelid == tInstalllog.getChannelid().longValue()) {
					installnum++;
					removeList.add(tInstalllog);
					String useragent = tInstalllog.getUseragent();
					// 系统版本不能为空
					if (ObjectUtil.isNotEmpty(useragent)) {
						useragent = useragent.toLowerCase();
						if (useragent.contains("vm true;")) {
							vmnum++;
							installnum--;
						} else if (useragent.contains("windows nt 5.1;")) {
							xpnum++;
						} else if (useragent.contains("windows nt 6.1;")) {
							if (useragent.toLowerCase().contains("wow64;")) {
								win764num++;
							} else {
								win732num++;
							}
						} else if (useragent.contains("windows nt 6.2;")) {
							if (useragent.toLowerCase().contains("wow64;")) {
								win864num++;
							} else {
								win832num++;
							}
						} else {
							othernum++;
						}
					} else {
						othernum++;
					}
				}
			}
			if (installnum > 0) {
				statisticService.updateInstallNum(channelid, userid, installnum + total);
				if (status == Const.Status.ENABLE && deduct > 0 && total > startnum) {
					installnum = Math.round(installnum * (100 - deduct) / 100);
					xpnum = Math.round(xpnum * (100 - deduct) / 100);
					win732num = Math.round(win732num * (100 - deduct) / 100);
					win764num = Math.round(win764num * (100 - deduct) / 100);
					win832num = Math.round(win832num * (100 - deduct) / 100);
					win864num = Math.round(win864num * (100 - deduct) / 100);
					othernum = Math.round(othernum * (100 - deduct) / 100);
				}
				
				// 如果这批数据中存在某一渠道的数据，那么统计之后则从当前数据列表中踢出
				installList.removeAll(removeList);
				removeList.clear();
			}
		}

		for (TInstalllog tInstalllog : vmList) {
			if (channelid == tInstalllog.getChannelid().longValue()) {
				vmnum++;
				removeList.add(tInstalllog);
			}
		}
		
		if(vmnum>0){
			// 如果这批数据中存在某一渠道的数据，那么统计之后则从当前数据列表中踢出
			vmList.removeAll(removeList);
			removeList.clear();
		}

		tIaudailylog.setInstallnum(installnum);
		tIaudailylog.setXpnum(xpnum);
		tIaudailylog.setWin732num(win732num);
		tIaudailylog.setWin764num(win764num);
		tIaudailylog.setWin832num(win832num);
		tIaudailylog.setWin864num(win864num);
		tIaudailylog.setOthernum(othernum);
		tIaudailylog.setVmnum(vmnum);
	}

	protected void activeStatistics(TIaudailylog tIaudailylog, List<TActivelog> activeList) {
		int activenum = 0;
		long channelid = tIaudailylog.getChannelid();
		if (activeList != null && activeList.size() > 0) {
			List<TActivelog> removeList = new ArrayList<TActivelog>();
			for (TActivelog tActivelog : activeList) {
				if (channelid == tActivelog.getChannelid().longValue()) {
					activenum++;
					removeList.add(tActivelog);
				}
			}
			if (activenum > 0) {
				// 如果这批数据中存在某一渠道的数据，那么统计之后则从当前数据列表中踢出
				activeList.removeAll(removeList);
				removeList.clear();
			}
		}
		tIaudailylog.setActivenum(activenum);
	}

	protected void uninstallStatistics(TIaudailylog tIaudailylog,
			List<TUninstalllog> uninstallList, int status, float deduct, int startnum, int total) {
		int uninstallnum = 0;
		long channelid = tIaudailylog.getChannelid();
		if (uninstallList != null && uninstallList.size() > 0) {
			List<TUninstalllog> removeList = new ArrayList<TUninstalllog>();
			for (TUninstalllog tUninstalllog : uninstallList) {
				if (channelid == tUninstalllog.getChannelid().longValue()) {
					uninstallnum++;
					removeList.add(tUninstalllog);
				}
			}
			if (uninstallnum > 0) {
				if (status == Const.Status.ENABLE && deduct > 0 && total > startnum) {
					uninstallnum = Math.round(uninstallnum * (100 - deduct) / 100);
				}
				
				// 如果这批数据中存在某一渠道的数据，那么统计之后则从当前数据列表中踢出
				uninstallList.removeAll(removeList);
				removeList.clear();
			}
		}
		tIaudailylog.setUninstallnum(uninstallnum);
	}

	protected void driverdownStatistics(TIaudailylog tIaudailylog,
			List<TDriverdownlog> driverdownlogList) {
		int driverdown = 0;
		long channelid = tIaudailylog.getChannelid();
		if (driverdownlogList != null && driverdownlogList.size() > 0) {
			List<TDriverdownlog> removeList = new ArrayList<TDriverdownlog>();
			for (TDriverdownlog tDriverdownlog : driverdownlogList) {
				if (channelid == tDriverdownlog.getChannelid().longValue()) {
					driverdown++;
					removeList.add(tDriverdownlog);
				}
			}
			if (driverdown > 0) {
				// 如果这批数据中存在某一渠道的数据，那么统计之后则从当前数据列表中踢出
				driverdownlogList.removeAll(removeList);
				removeList.clear();
			}
		}
		tIaudailylog.setDriverdown(driverdown);
	}

	protected void filesystemlogStatistics(List<TInstalllog> installList, String currdate) {
		if (installList != null && installList.size() > 0) {
			long channelid = 1;
			long userid = 1;
			TFilesystemlog tFilesystemlog = new TFilesystemlog();
			tFilesystemlog.setUserid(userid);
			tFilesystemlog.setChannelid(channelid);
			tFilesystemlog.setCreatedate(currdate);

			int ntfsnum = 0;
			int fatnum = 0;
			int fat32num = 0;
			int othernum = 0;

			for (TInstalllog tInstalllog : installList) {
				String useragent = tInstalllog.getUseragent();
				// 系统版本不能为空
				if (ObjectUtil.isNotEmpty(useragent)) {
					if (useragent.contains("FS NTFS;")) {
						ntfsnum++;
					} else if (useragent.contains("FS FAT32;")) {
						fat32num++;
					} else if (useragent.contains("FS FAT;")) {
						fatnum++;
					} else {
						othernum++;
					}
				} else {
					othernum++;
				}
			}

			tFilesystemlog.setNtfsnum(ntfsnum);
			tFilesystemlog.setFat32num(fat32num);
			tFilesystemlog.setFatnum(fatnum);
			tFilesystemlog.setOthernum(othernum);

			updateFilesystemlogCount(tFilesystemlog);
		}
	}

	protected int updateFilesystemlogCount(TFilesystemlog tFilesystemlog) {
		return targetService.updateTFilesystemlogCount(tFilesystemlog);
	}

	protected int updateIAUDailyCount(TIaudailylog tIaudailylog) {
		return targetService.updateTIaudailyCount(tIaudailylog);
	}

	protected int array2Int(String[] array, int index) {
		int ret = 0;
		if (array.length >= index + 1) {
			ret = Integer.valueOf(array[index]);
		}
		return ret;
	}
}
