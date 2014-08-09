package com.sltunion.cloudy.service;

import java.util.List;
import java.util.Map;


public interface IaudailylogService extends PagerService {

	List<Map<String, Object>> getChartResultList(Map<String, Object> params);
}
