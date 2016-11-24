package com.sharebo.mapper;

import java.util.List;

import com.sharebo.entity.Advertising;

public interface advertisingMapper {
	/**
	 * 查询广告
	 * @return
	 */
	public List<Advertising> selectAdvertising();
}
