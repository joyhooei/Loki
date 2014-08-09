package com.sltunion.cloudy.service;

import java.util.List;
import java.util.Map;


public interface HomepagelockdailyService extends PagerService {

	List<Map<String, Object>> getChartResultList(Map<String, Object> params);
}
