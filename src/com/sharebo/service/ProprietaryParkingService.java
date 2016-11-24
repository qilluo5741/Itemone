package com.sharebo.service;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Dayrules;
import com.sharebo.entity.Parkingspace;
import com.sharebo.entity.Weekrules;

public interface ProprietaryParkingService {
	//通过车位id得到车位星期规则
	public Weekrules weekTime(String parkid);
	//查询一个车位当月全部禁用的时间
	public List<String> selectParkMonthAllDayRlus(Map<String,String> map);
	//查询一个车位当月全部非禁用的时间
	public List<String> selectParkMonthAllNotDayRlus(Map<String,String> map);
	//修改当天时间 通过parkid h和 日期
	public boolean updateDayByHours(com.sharebo.entity.Dayrules d);
	//添加一天时间
	public boolean addDayrules(com.sharebo.entity.Dayrules d);
	//询日期规则是否存在 
	public boolean isExistxByDayrules(com.sharebo.entity.Dayrules d);
	
	//查询一天的可用时间
	public Dayrules getDayrules(Map<String,String> map);
	//根据时间查询是否禁用
	public boolean isdisable(String today,String parkid); 
	
	//修改车位信息
	public int updateParkingspaceByparkId(Parkingspace ps);
	//查询车位编号是否存在
	public boolean isparkNoIsExisByUserId(Parkingspace ps);
	//添加车位
	public int addParkingspace(Parkingspace ps);
	//添加默认的星期规则
	public int addWeekdayByParkInfo(Weekrules w);
	//根据用户查询全部车位
	public List<com.sharebo.entity.Dto.ParkDto> getParkIdByuserid(String userid);
	//根据小区名字模糊查询小区集合
	public List<com.sharebo.entity.Community> getCommunitybyLike(String community_name);
	//根据车位id查询车位信息
	public com.sharebo.entity.Dto.ParkCommunityDto getParkInfoByParkId(String parkid);
	//根据车位i查询星期规则
	public com.sharebo.entity.Dto.WeekrulesDto getWeekRulesByParkId(String parkid);
	//修改车位星期规则时间
	public int updateWeekrulesByWeekid(com.sharebo.entity.Dto.WeekrulesDto wk);
	
	
	//验证车位是否存在
	public boolean valParkInfoIsExists(String parkid);
	//验证订单中是否存在未处理的订单（需方已经付款，还未处理） 
	public boolean  valOrderIsExists(String parkid);
	//删除车位（修改不车位状态）
	public boolean deletePark(String parkid);
}
