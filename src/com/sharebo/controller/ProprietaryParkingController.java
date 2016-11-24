package com.sharebo.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sharebo.entity.Dayrules;
import com.sharebo.entity.Parkingspace;
import com.sharebo.entity.ResDate;
import com.sharebo.entity.ResMonth;
import com.sharebo.entity.ResultDto;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.Dto.WeekrulesDto;
import com.sharebo.service.ProprietaryParkingService;
import com.sharebo.util.Log;
/***
 * 专有车位_供方
 * @author niewei
 */
@RestController
@RequestMapping("/{sign}/ppc")
public class ProprietaryParkingController {
	@Autowired
	private ProprietaryParkingService service;
	//删除车位
	@RequestMapping("deletePark")
	public ResultDto aadeletePark(String parkid){
		//验证车位id是否为空
		if(parkid==null){
			return new ResultDto(1004,"参数不合法！");
		}
		//验证车位是否存在
		if(!service.valParkInfoIsExists(parkid)){
			return new ResultDto(10019,"车位不存在！");
		}
		//验证订单中是否存在未处理的订单（需方已经付款，还未处理）
		if(service.valOrderIsExists(parkid)){
			return new ResultDto(10018,"该车位还有订单未处理！");
		}
		//删除车位（修改车位状态  ）
		if(service.deletePark(parkid)){
			return new ResultDto(200,"删除成功！");
		}
		return new ResultDto(10017,"删除失败！");
	}
	
	
	//修改一天的小时时间
	@RequestMapping("updateHours")
	public ResultDto updateHours(String day){
		if(day==null){
			return new ResultDto(1004,"参数不合法！");
		}
		Dayrules d=null;
		//解析json
		try {
			d=(Dayrules) JSONObject.toBean(JSONObject.fromObject(day), Dayrules.class);
		} catch (Exception e) {
			return new ResultDto(1004,"参数不合法！");
		}
		//判断dayid是否为空
		if(d.getDayid()==null){
			d.setDayid(UUID.randomUUID().toString());
			//查询是否存在  存在就说已经添加啦！
			if(service.isExistxByDayrules(d)){
				return new ResultDto(10015,"已经该数据已经存在！请注意是否修改！请检查dayid是否有误！");
			}
			//执行添加
			if(service.addDayrules(d)){
				return new ResultDto(200,"操作成功！");
			}
			return new ResultDto(10016,"操作失败！");
		}
		else{
			//执行修改
			if(service.updateDayByHours(d)){
				return new ResultDto(200,"操作成功！");
			}
			return new ResultDto(10016,"操作失败！");
		}
	}
	//查询一天的时间情况
	@RequestMapping("dayrules")
	public ResultDto dayrules(String day,String parkid){
		if(day==null||parkid==null){
			return new ResultDto(1004,"参数不合法！");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar(); 
		try {
			calendarCountDays.setTime(sdf.parse(day));
		} catch (ParseException e) {
			return new ResultDto(10014,"时间格式有误！");
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("day",day);
		map.put("parkid",parkid);
		return new ResultDto(200,service.getDayrules(map));
	} 
	/**
	 *传入日期   车位id 
	 * 用过日期为条件 查询出一个月的时间规则（判断是否当天为禁用）
	 * 通过车位id查询星期规则（当天不禁用，再验证星期是否禁用）
	 * */
	@RequestMapping("getMonth")
	public ResultDto getMonth_2(String strDate,String parkid){
		if(strDate==null||parkid==null){
			return new ResultDto(1004,"参数不合法！");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendarCountDays = new GregorianCalendar(); 
		try {
			calendarCountDays.setTime(sdf.parse(strDate));
		} catch (ParseException e) {
			return new ResultDto(10014,"时间格式有误！");
		}
		int theYear=calendarCountDays.get(Calendar.YEAR);//传入时间年份
		int theMonth=calendarCountDays.get(Calendar.MONTH)+1;//传入时间的月份
		int days=calendarCountDays.getActualMaximum(Calendar.DAY_OF_MONTH);//传入月的总天数
		//*******************************************************************************************//
		Calendar calendar= Calendar.getInstance(); 
		int thisYear=calendar.get(Calendar.YEAR);///今天的年份
		int thisMonth=calendar.get(Calendar.MONTH)+1;///今天的月份
		int thisDay=calendar.get(Calendar.DATE);//今天是几号
		ResMonth rm=new ResMonth();//设置年月
		//传入的月份
		//System.out.println(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth));
		System.out.println("传入的年份："+theYear+"现在的年份："+thisYear);
		//判断传入月份是否大于等于当前月份
		if(theYear<thisYear){
			return new ResultDto(1058,"年份不能小于今年哟~");
		}else{
			if(theYear==thisYear)
			if(theMonth<thisMonth){
				return new ResultDto(1058,"月份不能小于当前月哟~");
			}
		}
		/******************************************/
		//查询出车位id和当前月的禁用时间
		Map<String,String> disableDayMap=new HashMap<String, String>();
		disableDayMap.put("parkid",parkid);
		disableDayMap.put("begin",theYear+"-"+theMonth+"-"+"01");
		disableDayMap.put("end",(theMonth==12?theYear+1:theYear)+"-"+(theMonth==12?1:theMonth+1)+"-"+"01");
		//禁用的日期
		List<String> disableDayList=service.selectParkMonthAllDayRlus(disableDayMap);
		for (String string : disableDayList) {
			disableDayMap.put(string, string);//存入集合
		}
		//独立设置的日期（未禁用的日期）
		List<String> disableNotDayList=service.selectParkMonthAllNotDayRlus(disableDayMap);
		for (String string : disableNotDayList) {
			disableDayMap.put(string+"_not", string);//存入集合
		}
		Weekrules weekr=service.weekTime(parkid);
		/******************************************/
		rm.setYear(theYear);
		rm.setMonth(theMonth);
		List<ResDate> rdList=new ArrayList<ResDate>();
		//循环一个月的天数
		for(int i=1;i<=days;i++){
			//如果是当前月 小于当天就不做处理
				if(i<thisDay&&theYear==thisYear){
					rdList.add(new ResDate(i,false,false));
				}else{
					//当前月份  可用时间
					if(disableDayMap.get(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth)+"-"+(i<10?"0"+i:i))==null){
						//------没有针对当天日期禁用
						if(disableDayMap.get(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth)+"-"+(i<10?"0"+i:i)+"_not")==null){
							//没有针对当天日期启用
							//验证用户的星期规则
							boolean isd=WeekIsdisable(weekr,DayToWeek(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth)+"-"+(i<10?"0"+i:i)));
							rdList.add(new ResDate(i,isd,true));
						}else{
							rdList.add(new ResDate(i,true,true));
						}
					}else{
						//存在代表禁用
						rdList.add(new ResDate(i,false,true));
					}
				}
		}
		rm.setRd(rdList);
		/******************************************/
		return new ResultDto(200,rm);
	}
	//传入星期几和星期规则 返回是否禁用
	public static boolean WeekIsdisable(Weekrules w,int week){
		String begin=null;
		String end=null;
		if(week==1){//星期日
			begin=w.getSunday_begin();end=w.getSunday_end();
		}else if(week==2){//星期一
			begin=w.getMonday_begin();end=w.getMonday_end() ;
		}else if(week==3){//星期二
			begin=w.getTuesday_begin();end=w.getTuesday_end() ;
		}else if(week==4){//星期三
			begin=w.getWednesday_begin();end=w.getWednesday_end(); 
		}else if(week==5){//星期四
			begin=w.getThursday_begin();end=w.getThursday_end();
		}else if(week==6){//星期五
			begin=w.getFriday_begin();end=w.getFriday_end();
		}else if(week==7){//星期六
			begin=w.getSaturday_begin();end=w.getSaturday_end();
		}
		if(end.equals("23:59:00")){
			return true;
		}
		System.out.println(end);
		begin=begin.substring(0,2);
		
		end=end.substring(0,2);
		if(begin.equals(end)){//相等时禁用
			return false;
		}
		return true;
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
	//根据parkid 和时间查询一个月的情况
	@RequestMapping("getMonth2")
	public ResultDto getMonth(String strDate,String parkid){
		if(strDate==null||parkid==null){
			return new ResultDto(1004,"参数不合法！");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendarCountDays = new GregorianCalendar(); 
		try {
			calendarCountDays.setTime(sdf.parse(strDate));
		} catch (ParseException e) {
			return new ResultDto(10014,"时间格式有误！");
		}
		int theYear=calendarCountDays.get(Calendar.YEAR);//传入时间年份
		int theMonth=calendarCountDays.get(Calendar.MONTH)+1;//传入时间的月份
		int days=calendarCountDays.getActualMaximum(Calendar.DAY_OF_MONTH);//传入月的总天数
		//*******************************************************************************************//
		Calendar calendar= Calendar.getInstance(); 
		int thisYear=calendar.get(Calendar.YEAR);///今天的年份
		int thisMonth=calendar.get(Calendar.MONTH)+1;///今天的月份
		int thisDay=calendar.get(Calendar.DATE);//今天是几号
		
		ResMonth rm=new ResMonth();
		rm.setYear(theYear);
		rm.setMonth(theMonth);
		List<ResDate> rdList=new ArrayList<ResDate>();
		//判断用户选择的月份 是否是本月
		if(strDate.equals(thisYear+"-"+((thisMonth<10)?"0"+thisMonth:thisMonth))){	
			//是本月分！
			//遍历从今天开始遍历
			for(int i=1;i<=days;i++){
				if(i>=thisDay){//如果今天小于开始天数，处理
					//如果是为true说明没有针对单个日期做更改  接下来验证是否有星期规则
					rdList.add(new ResDate(i, service.isdisable(strDate+"-"+i,parkid),true));
					//如果为false说明已经针对单个日期做处理了 不验证星期规则
				}else{
					//System.out.println("今天是"+i+"日     小于当前日期不做处理");
					rdList.add(new ResDate(i,false,false));
				}
			}
		}
		else if((theYear<thisYear)||(theYear==thisYear&&theMonth<thisMonth)){
			//不是本月份！也不是有效时间
			for(int i=1;i<=days;i++){
				//System.out.println("不是有效时间——今天是"+i+"号");
				rdList.add(new ResDate(i, service.isdisable(strDate+"-"+i,parkid),false));
			}
		}
		else{
			//不是本月份！但是有效时间
			for(int i=1;i<=days;i++){
				//System.out.println("有效时间：今天是"+i+"号");
				rdList.add(new ResDate(i, service.isdisable(strDate+"-"+i,parkid),true));
			}
		}
		rm.setRd(rdList);
		return new ResultDto(200,rm);
	}
	//修改车位星期规则
	@RequestMapping("updateWeekTimeByParkId")
	public ResultDto updateWeekTimeByParkId(String weeks){
		if(weeks==null){
			return new ResultDto(1004,"参数不合法！");
		}
		WeekrulesDto wd=null;
		try {
			//解析json
			wd=(WeekrulesDto) JSONObject.toBean(JSONObject.fromObject(weeks), WeekrulesDto.class);
		} catch (Exception e) {
			return new ResultDto(1004,"参数不合法！");
		}
		//进行修改
		if(service.updateWeekrulesByWeekid(wd)>0)
		return new ResultDto(200,"修改成功！");
		return new ResultDto(10013,"修改失败！");
		
	}
	//根据车位id查询星期设置
	@RequestMapping("getWeekTimeByParkId")
	public ResultDto getWeekTimeByParkId(String parkid){
		if(parkid==null){
			return new ResultDto(1004,"参数不合法！");
		}
		return new ResultDto(200,service.getWeekRulesByParkId(parkid));
	}
	
	//根据车位id查询车位信息
	@RequestMapping("getParkInfoByParkId")
	public ResultDto getParkInfo(String parkid){
		if(parkid==null){
			return new ResultDto(1004,"参数不合法！");
		}
		return new ResultDto(200,service.getParkInfoByParkId(parkid));
	}
	
	/**
	 * 根据小区名字模糊查询
	 * @param cname
	 * @return
	 * /{sign}/ppc/getCommunity.do?cname=安西小区
	 */
	@RequestMapping("getCommunity")
	public ResultDto getCommunity(String cname){
		cname="%"+cname+"%";
		System.out.println(cname+"----------------------");
		return new ResultDto(200,service.getCommunitybyLike(cname));
	}
	//根据用户id查询车位 
	@RequestMapping("getPark")
	public ResultDto getPark(String userid){
		if(userid==null){
			return new ResultDto(1004,"参数不合法！");
		}
		return new ResultDto(200,service.getParkIdByuserid(userid));
	}
	//修改车位
	@RequestMapping("updatepark")
	public ResultDto updatePark(Parkingspace ps){
		//验证数据 
		String e=ps.getEntrance();// 入口
		String pt=ps.getParkType();// 车位类型
		String pno=ps.getParkNo();// 车位号
		double money=ps.getMoney();// 停车费用
		String parkType=ps.getParkType();//车位类型
		String parkid=ps.getParkid();
		if(parkid==null||e==null||pt==null||pno==null||money==0||parkType==null){
			return new ResultDto(1004,"参数不合法！");
		}
		//验证车位号是否存在
		if(service.isparkNoIsExisByUserId(ps)){
			return new ResultDto(10011,"该车位编号您已录入了！");
		}
		//修改车位
		if(service.updateParkingspaceByparkId(ps)>0)
		return new ResultDto(200,"修改成功");
		return new ResultDto(10012,"修改失败！");
	}
	//发布车位
	@RequestMapping("addPark")
	public ResultDto addParkingSpace(Parkingspace ps){
		try {
			//需要的参数：
			//小区编号  入口  类型 车位编号  停车说明 星期时间  用户  收费类型 费用
			//验证数据
			if(ps.getCommunity()==null||ps.getUser()==null){
				return new ResultDto(1004,"参数不合法！");
			}
			String cid=ps.getCommunity().getCommunityId();//小区主键
			String userid=ps.getUser().getUserid();
			String e=ps.getEntrance();//入口
			String pt=ps.getParkType();//车位类型
			String pno=ps.getParkNo();//车位号
			double money=ps.getMoney();//停车费用
			String parkType=ps.getParkType();//车位类型
			if(cid==null||e==null||pt==null||pno==null||userid==null||money==0||parkType==null){
				return new ResultDto(1004,"参数不合法！");
			}
			//==================================添加车位===============================
			//验证车位是否已经存在
			if(service.isparkNoIsExisByUserId(ps)){
				return new ResultDto(10011,"该车位编号您已录入了！");
			}
			//添加车位数据
			String parkid=UUID.randomUUID().toString();
			ps.setParkid(parkid);//设置id
			if(service.addParkingspace(ps)>0){
				//添加成功
				Weekrules weekrules=new Weekrules();
				weekrules.setWeekid(UUID.randomUUID().toString());
				weekrules.setPark(ps);
				if(service.addWeekdayByParkInfo(weekrules)==0){
					Log.getInstance().error("初始化星期规则失败！");
				}
				//创建星期时间表 默认为0
				return new ResultDto(200,"保存成功！",parkid);
			}
		} catch (Exception e) {
			Log.getInstance().error(e.getMessage()+"失败！");
			return new ResultDto(10010,"参数异常，服务器超时！");
		}
		return null;
	}
}
