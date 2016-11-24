package com.sharebo.service;

import java.util.List;
import java.util.Map;
import com.sharebo.entity.Dto.ManagementDto;

public interface WebManagementService {
	/**
	 * 模糊查询分页
	 */
	public List<ManagementDto> getManagementisnotPager(Map<String,Object> map);
	/**
	 * 模糊查询车位总数
	 * @return
	 */
	public int getManagementnotcount(String mobile);
	public List<ManagementDto> getManagementPager(Map<String,Object> map);
	/**
	 * 查询车位总数
	 * @return
	 */
	public int getManagementcount();
}
