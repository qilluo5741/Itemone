package com.sharebo.mapper;

import com.sharebo.entity.TouchBalance;

public interface TouchBalanceMapper {
	/**
	 * 添加明显记录
	 * @param message
	 * @return
	 */
	public int addTouchBalance(TouchBalance touchbalance);
	/**
	 * 根据用户id查询purseid
	 */
	public String getpurseBypurseid(String userid);
}
