package com.sltunion.cloudy.quartz;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.sltunion.cloudy.common.utils.DateUtil;
import com.sltunion.cloudy.common.utils.LogerUtil;
import com.sltunion.cloudy.service.TargetService;

@Controller("costlogQuqrtz")
public class CostlogQuqrtz {
	@Resource
	private TargetService targetService;

	/**
	 * 测试用
	 */
	public void mdoStatistics() {
		LogerUtil.logger.info("costlogStatistics start");
		String[] currdateA = { "2014-05-21","2014-05-22","2014-05-23","2014-05-24","2014-05-25","2014-05-26","2014-05-27","2014-05-28","2014-05-29","2014-05-30","2014-05-31","2014-06-01","2014-06-02","2014-06-03","2014-06-04","2014-06-05","2014-06-06","2014-06-07","2014-06-08","2014-06-09","2014-06-10" ,"2014-06-11","2014-06-12","2014-06-13","2014-06-14","2014-06-15","2014-06-16"};
//		int[] hourA = {0,2,4};
		for (String currdate : currdateA) {
//			for (int hour : hourA) {
				costlogStatistics(currdate, 0);
//			}
		}
		LogerUtil.logger.info("costlogStatistics end");
	}

	public void doStatistics() {
		LogerUtil.logger.info("costlogStatistics start");
		String currdate = DateUtil.getDateStr(DateUtil.getDateStr(),-1);
		String time = DateUtil.getTimeStr();
		String[] timeArr = time.split(":");
		int hour = array2Int(timeArr, 0);
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
		costlogStatistics(currdate, hour);
		LogerUtil.logger.info("costlogStatistics end");
	}

	public void costlogStatistics(String currdate, Integer hour) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("createdate", currdate);
		targetService.updateTCostlog(param);
	}

	protected int array2Int(String[] array, int index) {
		int ret = 0;
		if (array.length >= index + 1) {
			ret = Integer.valueOf(array[index]);
		}
		return ret;
	}
}
