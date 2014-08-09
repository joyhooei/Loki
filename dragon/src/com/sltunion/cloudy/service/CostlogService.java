package com.sltunion.cloudy.service;

public interface CostlogService extends PagerService {
	void changeStatus(Long id, Byte status);
}
