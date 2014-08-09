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
import com.sltunion.cloudy.persistent.model.TActivelog;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.TDriverdownlog;
import com.sltunion.cloudy.persistent.model.THomepagelocklog;
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
@Controller("initDataQuartz")
public class InitDataQuartz {

	@Resource
	private SourceService sourceService;
	@Resource
	private TargetService targetService;
	@Resource
	private StatisticService statisticService;
	@Resource
	private ChannelService channelService;

	/**
	 * 测试用
	 */
	public void mdoStatistics() {
		System.out.println("browserStatistics start");
		String[] currdateA = { "2013-11-14" };
		for (String currdate : currdateA) {
			initDataStatistics(currdate, null);
		}
		System.out.println("browserStatistics end");
	}

	public void doStatistics() {
		LogerUtil.logger.info("browserStatistics start");
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
		initDataStatistics(currdate, hour);
		LogerUtil.logger.info("browserStatistics end");
	}

	public void initDataStatistics(String currdate, Integer hour) {
		QuartzConst.cleanAll();
		List<TChannel> channelList = channelService.findAll();
		long install_sourceid = statisticService.findSourceid(Const.Table.T_INSTALLLOG,
				Const.Table.T_IAUDAILYLOG);
		long active_sourceid = statisticService.findSourceid(Const.Table.T_ACTIVELOG,
				Const.Table.T_IAUDAILYLOG);
		long uninstall_sourceid = statisticService.findSourceid(Const.Table.T_UNINSTALLLOG,
				Const.Table.T_IAUDAILYLOG);
		long down_sourceid = statisticService.findSourceid(Const.Table.T_DRIVERDOWN,
				Const.Table.T_IAUDAILYLOG);
		long homepage_sourceid = statisticService.findSourceid(Const.Table.T_HOMEPAGELOCKLOG,
				Const.Table.T_BROWSERLOG);

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
		List<TDriverdownlog> driverdownList = sourceService.findDriverdownlogByStatistics(param);

		param.put("id", homepage_sourceid);
		List<THomepagelocklog> homepagelocklogList = sourceService.findHomepagelocklogByStatistics(param);
		
		if (channelList != null && channelList.size() > 0) {
			QuartzConst.channelList.addAll(channelList);
		}
		if (installList != null && installList.size() > 0) {
			QuartzConst.installList.addAll(installList);
		}
		if (activeList != null && activeList.size() > 0) {
			QuartzConst.activeList.addAll(activeList);
		}
		if (uninstallList != null && uninstallList.size() > 0) {
			QuartzConst.uninstallList.addAll(uninstallList);
		}
		if (driverdownList != null && driverdownList.size() > 0) {
			QuartzConst.driverdownList.addAll(driverdownList);
		}
		if (homepagelocklogList != null && homepagelocklogList.size() > 0) {
			QuartzConst.homepagelocklogList.addAll(homepagelocklogList);
		}
	}

	protected void filter(List<THomepagelocklog> tHomepagelocklogList) {
		List<THomepagelocklog> removeList = new ArrayList<THomepagelocklog>();
		long sourceid = tHomepagelocklogList.get(0).getId();
		for (THomepagelocklog entity : tHomepagelocklogList) {
			sourceid = entity.getId();
			long ipc = statisticService.countDailyExist(entity.getIp(), entity.getCreatedate());
			long diskc = statisticService.countDailyExist(entity.getDisknum(), entity
					.getCreatedate());
			if (ipc > 0 || diskc > 0) {
				removeList.add(entity);
			} else {
				statisticService.saveDailyExist(entity.getIp(), entity.getCreatedate());
				statisticService.saveDailyExist(entity.getDisknum(), entity.getCreatedate());
			}
		}
		if (!removeList.isEmpty()) {
			tHomepagelocklogList.removeAll(removeList);
		}
		statisticService.updateSourceid(Const.Table.T_HOMEPAGELOCKLOG,
				Const.Table.T_HOMEPAGELOCKDAILY, sourceid);
	}

	protected int array2Int(String[] array, int index) {
		int ret = 0;
		if (array.length >= index + 1) {
			ret = Integer.valueOf(array[index]);
		}
		return ret;
	}
}
