package com.sltunion.cloudy.quartz;

import java.util.ArrayList;
import java.util.List;

import com.sltunion.cloudy.business.Const;
import com.sltunion.cloudy.persistent.model.TActivelog;
import com.sltunion.cloudy.persistent.model.TChannel;
import com.sltunion.cloudy.persistent.model.TDriverdownlog;
import com.sltunion.cloudy.persistent.model.THomepagelocklog;
import com.sltunion.cloudy.persistent.model.TInstalllog;
import com.sltunion.cloudy.persistent.model.TUninstalllog;

public class QuartzConst {
	public static List<TInstalllog> installList = new ArrayList<TInstalllog>(Const.Statistics.LIMIT);
	public static List<TActivelog> activeList = new ArrayList<TActivelog>(Const.Statistics.LIMIT);
	public static List<TUninstalllog> uninstallList = new ArrayList<TUninstalllog>(Const.Statistics.LIMIT);
	public static List<TDriverdownlog> driverdownList = new ArrayList<TDriverdownlog>(Const.Statistics.LIMIT);
	public static List<THomepagelocklog> homepagelocklogList = new ArrayList<THomepagelocklog>(Const.Statistics.LIMIT);
	public static List<TChannel> channelList = new ArrayList<TChannel>(Const.Statistics.LIMIT);
	
	public static void cleanAll(){
		if(!installList.isEmpty()){
			installList.clear();
		}
		if(!activeList.isEmpty()){
			activeList.clear();
		}
		if(!uninstallList.isEmpty()){
			uninstallList.clear();
		}
		if(!driverdownList.isEmpty()){
			driverdownList.clear();
		}
		if(!homepagelocklogList.isEmpty()){
			homepagelocklogList.clear();
		}
		if(!channelList.isEmpty()){
			channelList.clear();
		}
	}
}
