package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.TouchBalance;
import com.sharebo.mapper.TouchBalanceMapper;
import com.sharebo.service.TouchBalanceService;
@Service
public class TouchBalanceServiceImpl implements TouchBalanceService {
	@Autowired
	private TouchBalanceMapper mapper;
	/**
	 * 添加明细记录
	 */
	public int addTouchBalance(TouchBalance touchbalance) {
		return mapper.addTouchBalance(touchbalance);
	}
	/**
	 * 根据用户id查询purseid
	 */
	public String getpurseBypurseid(String userid) {
		return mapper.getpurseBypurseid(userid);
	}
}
