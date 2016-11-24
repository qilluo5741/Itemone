package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Dayrules;
import com.sharebo.entity.OrderTime;
import com.sharebo.entity.Parkingspace;
import com.sharebo.entity.Weekrules;

public interface ProprietaryParkingMapper {
	//查询一个车位当月全部非禁用的时间
	public List<String> selectparkMenthAllNotDayRlus(Map<String,String> map);
	//查询一个车位当月全部禁用的时间
	public List<String> selectParkMonthAllDayRlus(Map<String,String> map);
	//修改当天时间 通过parkid h和 日期
	public int updateDayByHours(com.sharebo.entity.Dayrules d);
	//添加一天时间
	public int addDayrules(com.sharebo.entity.Dayrules d);
	//询日期规则是否存在 
	public int isExistxByDayrules(com.sharebo.entity.Dayrules d);
	
	//查询订单时间表 通过日期 和车位Id
	public List<OrderTime> getOrdertime(Map<String,String> map);
	//查询星期规则
	public Weekrules weekTime(String parkid);
	//查询一天的可用时间
	public Dayrules getDayrules(Map<String,String> map);
	//根据车位id 日期 查询是否为禁用
	public int isdisableWeek(Map<String, Object> map);
	//根据时间查询是否没有禁用
	public int isDayNotDisable(Map<String,Object> map); 
	//根据某天查询是否禁用
	public int isDayDisable(Map<String,Object> map);
	
	//修改车位信息
	public int updateParkingspaceByparkId(Parkingspace ps);
	//查询车位编号是否存在
	public int isparkNoIsExisByUserId(Parkingspace ps);
	//添加车位
	public int addParkingspace(Parkingspace ps);
	//添加默认的星期规则
	public int addWeekdayByParkInfo(Weekrules w);
	//根据用户查询全部车位
	public List<com.sharebo.entity.Dto.ParkDto> getParkIdByuserid(String userid);
	//根据小区名字模糊查询小区集合
	public List<com.sharebo.entity.Community> getCommunitybyLike(String cname);
	//根据车位id查询车位信息
	public com.sharebo.entity.Dto.ParkCommunityDto getParkInfoByParkId(String parkid);
	//根据车位i查询星期规则
	public com.sharebo.entity.Dto.WeekrulesDto getWeekRulesByParkId(String parkid);
	//修改车位星期规则时间
	public int updateWeekrulesByWeekid(com.sharebo.entity.Dto.WeekrulesDto wk);
	
	//验证车位是否存在
	public int valParkInfoIsExists(String parkid);
	//验证订单中是否存在未处理的订单（需方已经付款，还未处理） 
	public int  valOrderIsExists(String parkid);
	//删除车位（修改不车位状态）
	public int deletePark(String parkid);
}
