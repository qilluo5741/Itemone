package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Dto.ParkingDto;
import com.sharebo.entity.Dto.ParkingdetailDto;
public interface parkingspaceMapper {
	//通过小区id分页查询车位
	public List<ParkingDto> pagertbyList(Map<String,Object> map);
	public int pagerbyCount();//总数
	//通过车位id查询车位详情（车位状态、收费类型、费用、说明、类型、入口地址、是否收藏）
	public ParkingdetailDto selectById(String pid);
	//判断车位是否别收藏
	public int iscollect(Map<String, Object> map);
}
