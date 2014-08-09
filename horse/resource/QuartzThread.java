package com.sltunion.cloudy.quartz;

import com.sltunion.cloudy.common.utils.LogerUtil;

public class QuartzThread extends Thread{
	private boolean stop;
	FilesystemQuartz iauDailyQuartz;
	
	@Override
	public void run() {
		try {
			while (!isStop()) {
				try {
					iauDailyQuartz.doStatistics();
				} catch (Exception e) {
					LogerUtil.logger.error("",e);
				}finally{
					try {
						Thread.sleep(60*1000);
					} catch (Exception e) {
						LogerUtil.logger.error("",e);
					}
				}
			}
		} catch (Exception e) {
			LogerUtil.logger.error("",e);
		}
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean isStop() {
		return stop;
	}
}
