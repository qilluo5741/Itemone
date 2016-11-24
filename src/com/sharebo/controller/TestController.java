package com.sharebo.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.Dayrules;
import com.sharebo.entity.TestInfo;
import com.sharebo.service.testService;
import com.sharebo.util.Log;
import com.sharebo.util.Pager;
import com.sharebo.util.RestDto;
@RestController
@RequestMapping("{timestamp}/test")
public class TestController {
	@Autowired
	private testService service;
	 
	//查询一天的时间情况
	@RequestMapping("dayrules")
	public @ResponseBody Dayrules dayrules(){
		Map<String,String> map=new HashMap<String, String>();
		map.put("day","2016-04-28");
		return service.getDayrules(map);
	} 
	
	//供方用户添加一个禁用日期
	@RequestMapping("addd")
	public @ResponseBody String addDayrules(){
		String strDate="2016-04-30"; 
		Map<String,String> map=new HashMap<String, String>();
		map.put("id",UUID.randomUUID().toString());//id
		map.put("thisDay", strDate);
		int i=service.addDayrules(map);
		System.out.println(i);
		return "true";
	}
	//http://localhost:8080/sharebo/20160328112533/test/aa.do   查询用户的时间
/*	@RequestMapping("aa")
	public @ResponseBody List<ResDate> t(){
		//查询用户哪些时间显示拒绝出租
		//传入年月
		String strDate="2016-04";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendarCountDays = new GregorianCalendar(); 
		try {
			calendarCountDays.setTime(sdf.parse(strDate));
		} catch (ParseException e) {
			System.out.println("时间格式有误~");
		}
		int theYear=calendarCountDays.get(Calendar.YEAR);//传入时间年份
		int theMonth=calendarCountDays.get(Calendar.MONTH)+1;//传入时间的月份
		int days=calendarCountDays.getActualMaximum(Calendar.DAY_OF_MONTH);//传入月的总天数
		//////////////////////////////////////////////////
		Calendar calendar= Calendar.getInstance(); 
		int thisYear=calendar.get(Calendar.YEAR);///今天的年份
		int thisMonth=calendar.get(Calendar.MONTH)+1;///今天的月份
		int thisDay=calendar.get(Calendar.DATE);//今天是几号
		
		List<ResDate> dateList=new ArrayList<ResDate>();
		//判断用户选择的月份 是否是本月
		if(strDate.equals(thisYear+"-"+((thisMonth<10)?"0"+thisMonth:thisMonth))){	
			//是本月分！
			//遍历从今天开始遍历
			for(int i=1;i<=days;i++){
				if(i>=thisDay){//如果今天小于开始天数，处理
					//如果是为true说明没有针对单个日期做更改  接下来验证是否有星期规则
					dateList.add(new ResDate(theYear,theMonth, i, service.isdisable(strDate+"-"+i),true));
					//如果为false说明已经针对单个日期做处理了 不验证星期规则
				}else{
					//System.out.println("今天是"+i+"日     小于当前日期不做处理");
					dateList.add(new ResDate(theYear,theMonth, i,false,false));
				}
			}
		}
		else if((theYear<thisYear)||(theYear==thisYear&&theMonth<thisMonth)){
			//不是本月份！也不是有效时间
			for(int i=1;i<=days;i++){
				//System.out.println("不是有效时间——今天是"+i+"号");
				dateList.add(new ResDate(theYear,theMonth, i, service.isdisable(strDate+"-"+i),false));
			}
		}
		else{
			//不是本月份！但是有效时间
			for(int i=1;i<=days;i++){
				//System.out.println("有效时间——今天是"+i+"号");
				dateList.add(new ResDate(theYear,theMonth, i, service.isdisable(strDate+"-"+i),true));
			}
		}
		return dateList;
	}*/
	//通过日期验证星期是否是禁用的
	
	public static boolean isWeekDisable(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar(); 
		calendarCountDays.setTime(sdf.parse(date));
		@SuppressWarnings("unused")
		int week=calendarCountDays.get(Calendar.DAY_OF_WEEK);
		return false;
	}
	//http://localhost:8080/sharebo/20160328112533/test/json-test.do
	@RequestMapping("/json-test")
	public  List<TestInfo> jsonTest(@PathVariable String timestamp,String test) throws SQLException{
		System.out.println(timestamp+"aaaa"+test);
		List<TestInfo> list=service.getTestInfo();
		return list;
	}
	/**
	 * 插入
	 * @param timestamp
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/json-insert")
	public Map<String, Object> jsoninsertTest(@PathVariable String timestamp) throws SQLException{
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		String id=UUID.randomUUID().toString();
		String name="张三";
		TestInfo t= new TestInfo();
		t.setId(id);
		t.setName(name);
		int i=service.insertTestInfo(t);
		if(i>0){
			mapRtn.put(RestDto.SUCCESS,true);
		}else{
			mapRtn.put(RestDto.SUCCESS,false);
		}
		return mapRtn;
	}
	/**
	 * 修改
	 * @param timestamp
	 * @param test
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/json-update")
	public  Map<String, Object>  jsonupdateTest(@PathVariable String timestamp,String test) throws SQLException{
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		String id="24486201880739845";
		String name="李四";
		TestInfo t= new TestInfo();
		t.setId(id);
		t.setName(name);
		int i=service.updateTestInfo(t);
		if(i>0){
			mapRtn.put(RestDto.SUCCESS,true);
		}else{
			mapRtn.put(RestDto.SUCCESS,false);
		}
		return mapRtn;
	}
	/**
	 * 日志记录
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping("log4")
	public Map<String,Object> log4jTest() throws InterruptedException{
		Map<String,Object> map=new HashMap<String, Object>();
		Log.getInstance().equals("aaaa");
		for (int i = 0; i <500; i++) {
			List<TestInfo> list=service.getTestInfo();
			System.out.println("    =="+i+"===:"+list.size());
			Thread.sleep(100);
		}
		return map;
	}
	/**
	 * 测试
	 * @return
	 */
	@RequestMapping("test")
	public @ResponseBody Pager<TestInfo> Test(int pageIndex,int pageSize){
		return service.pagerTestbyList(pageIndex,pageSize);
	}
	
	/**
	 * 
	 * @param xml 请求的参数名叫  xml
	 * @return  返回xml 
	 */
	@RequestMapping("访问路径名字")
	public @ResponseBody String testXml(String xml){
		//....处理
		return "xml..";
	}
	@RequestMapping("regId")
	public @ResponseBody String regID(String regId){
		System.out.println(regId+"_____________________________");
		return "true";
	}
}
