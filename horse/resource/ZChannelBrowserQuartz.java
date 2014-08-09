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
//import com.sltunion.cloudy.common.utils.PropertiesUtil;
//import com.sltunion.cloudy.persistent.model.TBrowserlog;
//import com.sltunion.cloudy.persistent.model.TChannel;
//import com.sltunion.cloudy.persistent.model.THomepagelocklog;
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
//@Controller("channelBrowserQuartz")
//public class ZChannelBrowserQuartz {
//
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
//		System.out.println("browserStatistics start");
//		String[] currdateA = { "2013-11-14" };
//		for (String currdate : currdateA) {
//			browserStatistics(currdate, null);
//		}
//		System.out.println("browserStatistics end");
//	}
//
//	public void doStatistics() {
//		LogerUtil.logger.info("browserStatistics start");
//		String currdate = DateUtil.getDateStr();
//		String time = DateUtil.getTimeStr();
//		String[] timeArr = time.split(":");
//		int hour = array2Int(timeArr, 0);
//		int minute = array2Int(timeArr, 1);
//		int second = array2Int(timeArr, 2);
//		int deff = DateUtil.deff();
//		int minutedeff = DateUtil.deff(minute, second);
//		if (deff > 23 * 3600 + 50 * 60) {
//			currdate = DateUtil.getDateStr(currdate, -1);
//			hour = 23;
//		} else {
//			if (minutedeff > 50 * 60) {
//				hour = hour - 1;
//			}
//		}
//		browserStatistics(currdate, hour);
//		LogerUtil.logger.info("browserStatistics end");
//	}
//
//	public void browserStatistics(String currdate, Integer hour) {
//		List<TChannel> channelList = channelService.findAll();
//		if (channelList != null && channelList.size() > 0) {
//			String[] browserflag = PropertiesUtil.getString("browserflag").split(",");
//			String[] browsername = PropertiesUtil.getString("browsername").split(",");
//			
//			long sourceid = statisticService.findSourceid(Const.Table.T_HOMEPAGELOCKLOG,
//					Const.Table.T_BROWSERLOG);
//
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("createdate", currdate);
//			param.put("hour", hour);
//			param.put("limit", Const.Statistics.LIMIT);
//
//			param.put("id", sourceid);
//			List<THomepagelocklog> tHomepagelocklogList = sourceService
//					.findHomepagelocklogByStatistics(param);
//			if (tHomepagelocklogList != null && tHomepagelocklogList.size() > 0) {
//				filter(tHomepagelocklogList);
//				for (TChannel tChannel : channelList) {
//					long homepageid = 0;
//					List<THomepagelocklog> channelHomepagelocklogList = new ArrayList<THomepagelocklog>();
//					List<THomepagelocklog> removeList = new ArrayList<THomepagelocklog>();
//					long channelid = tChannel.getId().longValue();
//
//					for (THomepagelocklog tHomepagelocklog : tHomepagelocklogList) {
//						if (channelid == tHomepagelocklog.getChannelid()) {
//							channelHomepagelocklogList.add(tHomepagelocklog);
//						}
//					}
//
//					int total = channelHomepagelocklogList.size();
//					if (total > 0) {
//						for (int i = 0; i < browserflag.length; i++) {
//							int times = 0;
//							for (THomepagelocklog tHomepagelocklog : channelHomepagelocklogList) {
//								if (homepageid == tHomepagelocklog.getHomepageid()) {
//									String temp = tHomepagelocklog.getDefaultbrowser();
//									if (ObjectUtil.isNotEmpty(temp)) {
//										temp = temp.toLowerCase();
//										if (temp.equals(browserflag[i])) {
//											times++;
//											removeList.add(tHomepagelocklog);
//										}
//									}
//								}
//							}
//							if (times > 0) {
//								float rate = (float) times / total * 100;
//								updateTBrowserlogCount(channelid, homepageid, browsername[i],
//										browserflag[i], currdate, times, rate);
//							}
//						}
//
//						// 未知浏览器
//						if (removeList.size() > 0
//								&& removeList.size() < channelHomepagelocklogList.size()) {
//							channelHomepagelocklogList.removeAll(removeList);
//							int times = channelHomepagelocklogList.size();
//							float rate = (float) times / total * 100;
//							updateTBrowserlogCount(channelid, homepageid, "未知", "unkown", currdate,
//									times, rate);
//						}
//					}
//				}
//			}
//		}
//	}
//
//	protected void filter(List<THomepagelocklog> tHomepagelocklogList) {
//		List<THomepagelocklog> removeList = new ArrayList<THomepagelocklog>();
//		long sourceid = tHomepagelocklogList.get(0).getId();
//		for (THomepagelocklog entity : tHomepagelocklogList) {
//			sourceid = entity.getId();
//			long diskc = statisticService.countDailyExist(entity.getDisknum(), entity
//					.getCreatedate());
//			if (diskc > 0) {
//				removeList.add(entity);
//			} else {
//				continue;
//			}
//		}
//		if (!removeList.isEmpty()) {
//			tHomepagelocklogList.removeAll(removeList);
//		}
//		statisticService.updateSourceid(Const.Table.T_HOMEPAGELOCKLOG, Const.Table.T_BROWSERLOG,
//				sourceid);
//	}
//
//	public int updateTBrowserlogCount(Long channelid, Long homepageid, String name, String flag,
//			String createdate, Integer times, Float rate) {
//		TBrowserlog tBrowserlog = new TBrowserlog();
//		tBrowserlog.setChannelid(channelid);
//		tBrowserlog.setHomepageid(homepageid);
//		tBrowserlog.setTimes(times);
//		tBrowserlog.setRate(rate);
//		tBrowserlog.setCreatedate(createdate);
//		tBrowserlog.setName(name);
//		tBrowserlog.setFlag(flag);
//		return targetService.updateTBrowserlogCount(tBrowserlog);
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
