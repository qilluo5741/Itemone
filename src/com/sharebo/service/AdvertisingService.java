package com.sharebo.service;

import java.util.List;

import com.sharebo.entity.Advertising;

public interface AdvertisingService {
	/**
	 * 查询广告
	 * @return
	 */
	public List<Advertising> selectAdvertising();
}
