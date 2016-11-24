package com.sharebo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Community;
import com.sharebo.entity.Dayrules;
import com.sharebo.entity.OrderTime;
import com.sharebo.entity.Parkingspace;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.Dto.ParkCommunityDto;
import com.sharebo.entity.Dto.ParkDto;
import com.sharebo.entity.Dto.WeekrulesDto;
import com.sharebo.mapper.ProprietaryParkingMapper;
import com.sharebo.service.ProprietaryParkingService;
import com.sharebo.util.Log;
@Service
public class ProprietaryParkingServiceImpl implements ProprietaryParkingService {
	@Autowired
	private ProprietaryParkingMapper mapper;
	//判断车位编号是否存在
	public boolean isparkNoIsExisByUserId(Parkingspace ps) {
		// true 存在
		return mapper.isparkNoIsExisByUserId(ps)>0?true:false;
	}
	//添加停车场
	public int addParkingspace(Parkingspace ps) {
		return mapper.addParkingspace(ps);
	}
	//添加默认星期规则
	public int addWeekdayByParkInfo(Weekrules w) {
		return mapper.addWeekdayByParkInfo(w);
	}
	//修改车位信息
	public int updateParkingspaceByparkId(Parkingspace ps) {
		return mapper.updateParkingspaceByparkId(ps);
	}
	//根据userid查询所有车位
	public List<ParkDto> getParkIdByuserid(String userid) {
		return mapper.getParkIdByuserid(userid);
	}
	public List<Community> getCommunitybyLike(String cname) {
		return mapper.getCommunitybyLike(cname);
	}
	//根据车位id查询车位信息
	public ParkCommunityDto getParkInfoByParkId(String parkid) {
		return mapper.getParkInfoByParkId(parkid);
	}
	//根据车位i查询星期规则
	public WeekrulesDto getWeekRulesByParkId(String parkid) {
		return mapper.getWeekRulesByParkId(parkid);
	}
	//修改车位星期规则时间
	public int updateWeekrulesByWeekid(WeekrulesDto wk) {
		return mapper.updateWeekrulesByWeekid(wk);
	}
	public boolean isdisable(String today,String parkid) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("today", today);
		map.put("parkid",parkid);
		//判断这天是否是已经禁用
		boolean isd=mapper.isDayDisable(map)>0?true:false;//true 是 false 没禁用
		//真：直接返回
		if(isd){
			return false;//false  :代表禁用
		}
		//判断一天是否设置时间（没有禁用并且有时间）
		boolean iss=mapper.isDayNotDisable(map)>0?true:false;//true 是 false 没禁用
		if(iss){
			return true;//true ：代表用户已经设定时间
		}
		int week=DayToWeek(map.get("today").toString());
		//传参
		map.put("week",week);
		boolean isweekD=mapper.isdisableWeek(map)>0?false:true;//等于1 为禁用
		//System.out.println("时间："+today+"是否有时间："+iss+"是否禁用："+isd+"是否星期禁用:"+isweekD);
		return isweekD;
	}
	//传入日期，返回星期几
	public static int DayToWeek(String day){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar(); 
		try {
			calendarCountDays.setTime(sdf.parse(day));
		} catch (ParseException e) {
			Log.getInstance().error("转换时间失败!");
		}
		//得到当期那日期是星期几
		return calendarCountDays.get(Calendar.DAY_OF_WEEK);
	}
	//查询一天的时间
	public Dayrules getDayrules(Map<String, String> map) {
		Dayrules d=mapper.getDayrules(map);
		//如果当天没有设置时间，就按照星期规则计算
		if(null==d){ 
			d=new Dayrules();//初始化！
			d.setParkid(map.get("parkid"));
			int week=DayToWeek(map.get("day"));//通过日期得到星期几
			//通过星期查询可用时间段
			Weekrules w=mapper.weekTime(map.get("parkid"));
			//根据星期取值
			if(week==1){//星期日
				weekToDay(d,w.getSunday_begin(),w.getSunday_end(),1);
			}else if(week==2){//星期一
				weekToDay(d,w.getMonday_begin(),w.getMonday_end(),1);
			}else if(week==3){//星期二
				weekToDay(d,w.getTuesday_begin(),w.getTuesday_end(),1);		
			}else if(week==4){//星期三
				weekToDay(d,w.getWednesday_begin(),w.getWednesday_end(),1);
			}else if(week==5){//星期四
				weekToDay(d,w.getThursday_begin(),w.getThursday_end(),1);
			}else if(week==6){//星期五
				weekToDay(d,w.getFriday_begin(),w.getFriday_end(),1);
			}else if(week==7){//星期六
				weekToDay(d,w.getSaturday_begin(),w.getSaturday_end(),1);
			}
			d.setThisDay(map.get("day"));
		}
		//查询有没有时间段是否租用   存在的订单 根据时间处理
		List<OrderTime> ot=mapper.getOrdertime(map);
		if(null!=ot){
			//遍历订单时间表数据
			for (OrderTime orderTime : ot) {
				weekToDay(d,orderTime.getBegin_time(),orderTime.getEnd_time(),2);
			}
		}
		//System.out.println(d.toString());
		return d;
	}
	//通过星期规则转换时间
	public static  Dayrules weekToDay(Dayrules d,String begin,String end,int s){
		//截取前面两位成整型
		int b=Integer.parseInt(begin.substring(0,2));//开始时间
		int e=Integer.parseInt(end.substring(0,2));//结束时间
		if(b!=e){//判断两个是否相等，相等意味着禁用
			//把规定时间设为1
			for(int i=b;i<e;i++){
				if(i==0)d.setHours_24(s);
				if(i==1)d.setHours_01(s);
				if(i==2)d.setHours_02(s);
				if(i==3)d.setHours_03(s);
				if(i==4)d.setHours_04(s);
				if(i==5)d.setHours_05(s);
				if(i==6)d.setHours_06(s);
				if(i==7)d.setHours_07(s);
				if(i==8)d.setHours_08(s);
				if(i==9)d.setHours_09(s);
				if(i==10)d.setHours_10(s);
				if(i==11)d.setHours_11(s);
				if(i==12)d.setHours_12(s);
				if(i==13)d.setHours_13(s);
				if(i==14)d.setHours_14(s);
				if(i==15)d.setHours_15(s);
				if(i==16)d.setHours_16(s);
				if(i==17)d.setHours_17(s);
				if(i==18)d.setHours_18(s);
				if(i==19)d.setHours_19(s);
				if(i==20)d.setHours_20(s);
				if(i==21)d.setHours_21(s);
				if(i==22)d.setHours_22(s);
				if(i==23)d.setHours_23(s);
			}
		}
		return d;
	}
	public boolean addDayrules(Dayrules d) {
		return mapper.addDayrules(d)>0?true:false;
	}
	public boolean isExistxByDayrules(Dayrules d) {
		return mapper.isExistxByDayrules(d)>0?true:false;
	}
	public boolean updateDayByHours(Dayrules d) {
		return mapper.updateDayByHours(d)>0?true:false;
	}
	public boolean deletePark(String parkid) {
		return mapper.deletePark(parkid)>0?true:false;
	}
	public boolean valOrderIsExists(String parkid) {
		return mapper.valOrderIsExists(parkid)>0?true:false;
	}
	public boolean valParkInfoIsExists(String parkid) {
		return mapper.valParkInfoIsExists(parkid)>0?true:false;
	}
	//查询一个车位当月全部禁用的时间
	public List<String> selectParkMonthAllDayRlus(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.selectParkMonthAllDayRlus(map);
	}
	public Weekrules weekTime(String parkid) {
		// TODO Auto-generated method stub
		return mapper.weekTime(parkid);
	}
	public List<String> selectParkMonthAllNotDayRlus(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.selectparkMenthAllNotDayRlus(map);
	}
	
}
