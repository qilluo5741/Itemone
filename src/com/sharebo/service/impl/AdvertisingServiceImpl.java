package com.sharebo.service.impl;

import java.util.List;
/**
 * 广告查询
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Advertising;
import com.sharebo.mapper.advertisingMapper;
import com.sharebo.service.AdvertisingService;
@Service
public class AdvertisingServiceImpl implements AdvertisingService {
	@Autowired
	private advertisingMapper mapper;
	public List<Advertising>  selectAdvertising() {
		return mapper.selectAdvertising();
	}
}
