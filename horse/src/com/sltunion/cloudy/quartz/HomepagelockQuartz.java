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
import com.sltunion.cloudy.common.utils.PropertiesUtil;
import com.sltunion.cloudy.persistent.model.TBrowserlog;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.THomepagelockdaily;
import com.sltunion.cloudy.persistent.model.THomepagelocklog;
import com.sltunion.cloudy.service.ChannelService;
import com.sltunion.cloudy.service.SourceService;
import com.sltunion.cloudy.service.StatisticService;
import com.sltunion.cloudy.service.TargetService;

/**
 * 
 * @author sundial
 * 
 */
@Controller("homepagelockQuartz")
public class HomepagelockQuartz {

	@Resource
	private ChannelService channelService;
	@Resource
	private SourceService sourceService;
	@Resource
	private TargetService targetService;
	@Resource
	private StatisticService statisticService;

	/**
	 * 测试用
	 */
	public void mdoStatistics() {
		LogerUtil.logger.info("homepagelockStatistics start");
		String[] currdateA = { "2014-03-04","2014-03-05" };
		int[] hourA = {0,2,4};
		for (String currdate : currdateA) {
			for (int hour : hourA) {
				homepagelockStatistics(currdate, hour);
			}
		}
		LogerUtil.logger.info("homepagelockStatistics end");
	}

	public void doStatistics() {
		LogerUtil.logger.info("homepagelockStatistics start");
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
		homepagelockStatistics(currdate, hour);
		LogerUtil.logger.info("homepagelockStatistics end");
	}

	public void homepagelockStatistics(String currdate, Integer hour) {
		List<TChannel> channelList = channelService.findAll();
		long sourceid = statisticService.findSourceid(Const.Table.T_HOMEPAGELOCKLOG,
				Const.Table.T_HOMEPAGELOCKDAILY);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("createdate", currdate);
		param.put("hour", hour);
		param.put("limit", Const.Statistics.LIMIT);

		param.put("id", sourceid);
		List<THomepagelocklog> tHomepagelocklogList = sourceService
				.findHomepagelocklogByStatistics(param);
		if (tHomepagelocklogList != null && tHomepagelocklogList.size() > 0) {
			filter(tHomepagelocklogList);
			if (channelList != null && channelList.size() > 0 && tHomepagelocklogList.size() > 0) {
				List<THomepagelocklog> homepagelocklogList = new ArrayList<THomepagelocklog>();
				homepagelocklogList.addAll(tHomepagelocklogList);
				List<THomepagelocklog> removeList = new ArrayList<THomepagelocklog>();
				for (TChannel tChannel : channelList) {
					Long channelid = tChannel.getId();
					long homepageid = 0;
					if (homepagelocklogList.size() > 0) {
						homepagelockStatistics(homepagelocklogList, removeList, channelid,
								homepageid, currdate, hour);
					} else {
						break;
					}
				}
			}
			browserStatistics(tHomepagelocklogList, currdate, hour);
		}
	}

	protected void filter(List<THomepagelocklog> tHomepagelocklogList) {
		List<THomepagelocklog> removeList = new ArrayList<THomepagelocklog>();
		long sourceid = tHomepagelocklogList.get(0).getId();
		for (THomepagelocklog entity : tHomepagelocklogList) {
			sourceid = entity.getId();
			long ipc = statisticService.countDailyExist(entity.getIp(), entity.getCreatedate(),Const.Table.T_HOMEPAGELOCKEXIST);
			long diskc = statisticService.countDailyExist(entity.getDisknum(), entity
					.getCreatedate(),Const.Table.T_HOMEPAGELOCKEXIST);
			if (ipc > 0 || diskc > 0) {
				removeList.add(entity);
				if (diskc <= 0) {
					statisticService.saveDailyExist(entity.getDisknum(), entity.getCreatedate(),Const.Table.T_HOMEPAGELOCKEXIST);
				}else if(ipc <= 0){
					statisticService.saveDailyExist(entity.getIp(), entity.getCreatedate(),Const.Table.T_HOMEPAGELOCKEXIST);
				}
			} else {
				statisticService.saveDailyExist(entity.getIp(), entity.getCreatedate(),Const.Table.T_HOMEPAGELOCKEXIST);
				statisticService.saveDailyExist(entity.getDisknum(), entity.getCreatedate(),Const.Table.T_HOMEPAGELOCKEXIST);
			}
		}
		if (!removeList.isEmpty()) {
			tHomepagelocklogList.removeAll(removeList);
		}
		statisticService.updateSourceid(Const.Table.T_HOMEPAGELOCKLOG,
				Const.Table.T_HOMEPAGELOCKDAILY, sourceid);
	}

	public void homepagelockStatistics(List<THomepagelocklog> homepagelocklogList,
			List<THomepagelocklog> removeList, Long channelid, Long homepageid, String currdate,
			Integer hour) {

		int xpnum = 0;
		int win732num = 0;
		int othernum = 0;
		int num360 = 0;

		for (THomepagelocklog tHomepagelocklog : homepagelocklogList) {
			if (channelid.longValue() == tHomepagelocklog.getChannelid().longValue()) {
				String useragent = tHomepagelocklog.getUseragent();
				Byte is360 = tHomepagelocklog.getIs360();
				is360 = (is360 == null ? 0 : is360);

				if (ObjectUtil.isNotEmpty(useragent)) {
					useragent = useragent.toLowerCase();
					if (useragent.contains("windows nt 5.1")) {
						xpnum++;
					} else if (useragent.contains("windows nt 6.1")) {
						win732num++;
					} else {
						othernum++;
					}
				} else {
					othernum++;
				}

				if (1 == is360.byteValue()) {
					num360++;
				}
				removeList.add(tHomepagelocklog);
			}
		}
		if (xpnum > 0 || win732num > 0 || othernum > 0) {
			
			updateTHomepagelockdailyCount(channelid, homepageid, currdate, hour, xpnum, win732num,
					othernum, num360);
			
			// 如果这批数据中存在某一渠道的数据，那么统计之后则从当前数据列表中踢出
			homepagelocklogList.removeAll(removeList);
			removeList.clear();
		}
	}

	public void browserStatistics(List<THomepagelocklog> tHomepagelocklogList, String currdate,
			Integer hour) {
		if (tHomepagelocklogList != null && tHomepagelocklogList.size() > 0) {
			String[] browserflag = PropertiesUtil.getString("browserflag").split(",");
			String[] browsername = PropertiesUtil.getString("browsername").split(",");

			long homepageid = 1;
			long channelid = 1;
			List<THomepagelocklog> removeList = new ArrayList<THomepagelocklog>();

			int total = tHomepagelocklogList.size();
			for (int i = 0; i < browserflag.length; i++) {
				if (tHomepagelocklogList.size() > 0) {
					int times = 0;
					for (THomepagelocklog tHomepagelocklog : tHomepagelocklogList) {
						String temp = tHomepagelocklog.getDefaultbrowser();
						if (ObjectUtil.isNotEmpty(temp)) {
							temp = temp.toLowerCase();
							if (temp.equals(browserflag[i])) {
								times++;
								removeList.add(tHomepagelocklog);
							}
						}
					}
					if (times > 0) {
						float rate = (float) times / total * 100;
						updateTBrowserlogCount(channelid, homepageid, browsername[i],
								browserflag[i], currdate, times, rate);
						// 如果这批数据中存在某一浏览器的数据，那么统计之后则从当前数据列表中踢出
						tHomepagelocklogList.removeAll(removeList);
						removeList.clear();
					}
				} else {
					break;
				}
			}

			// 未知浏览器
			if (tHomepagelocklogList.size() > 0) {
				int times = tHomepagelocklogList.size();
				float rate = (float) times / total * 100;
				updateTBrowserlogCount(channelid, homepageid, "未知", "unkown", currdate, times, rate);
			}
		}
	}

	public int updateTHomepagelockdailyCount(Long channelid, Long homepageid, String createdate,
			Integer hour, Integer xpnum, Integer win732num, Integer othernum, Integer num360) {
		THomepagelockdaily tHomepagelockdaily = new THomepagelockdaily();
		tHomepagelockdaily.setChannelid(channelid);
		tHomepagelockdaily.setHomepageid(homepageid);
		tHomepagelockdaily.setXpnum(xpnum);
		tHomepagelockdaily.setWin732num(win732num);
		tHomepagelockdaily.setOthernum(othernum);
		tHomepagelockdaily.setCreatedate(createdate);
		tHomepagelockdaily.setHour(hour);
		return targetService.updateTHomepagelockdailyCount(tHomepagelockdaily);
	}

	public int updateTBrowserlogCount(Long channelid, Long homepageid, String name, String flag,
			String createdate, Integer times, Float rate) {
		TBrowserlog tBrowserlog = new TBrowserlog();
		tBrowserlog.setChannelid(channelid);
		tBrowserlog.setHomepageid(homepageid);
		tBrowserlog.setTimes(times);
		tBrowserlog.setRate(rate);
		tBrowserlog.setCreatedate(createdate);
		tBrowserlog.setName(name);
		tBrowserlog.setFlag(flag);
		return targetService.updateTBrowserlogCount(tBrowserlog);
	}

	protected int array2Int(String[] array, int index) {
		int ret = 0;
		if (array.length >= index + 1) {
			ret = Integer.valueOf(array[index]);
		}
		return ret;
	}
}
