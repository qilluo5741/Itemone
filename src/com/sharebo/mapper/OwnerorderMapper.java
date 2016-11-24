package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Dto.OwnerorderDto;

public interface OwnerorderMapper {
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<OwnerorderDto> getOwnerorderByPager(Map<String,Object> map);
	/**
	 * 查询总数
	 * @return
	 */
	public int getOwnerorder();
	/**
	 * 查询车位全部
	 * @param map
	 * @return
	 */
	public List<OwnerorderDto> getOwnerorderByAllPager(Map<String,Object> map);
	/**
	 * 查询车位全部总数
	 * @return
	 */
	public int getOwnerorderAll();
}
