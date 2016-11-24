package com.sharebo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sharebo.entity.OrderInfo;
import com.sharebo.entity.Purse;
import com.sharebo.entity.TaskInfo;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.Dto.GongOrder;
import com.sharebo.entity.Dto.OrderCancelDto;
import com.sharebo.entity.Dto.OrderDto;
import com.sharebo.entity.Dto.OrderInfoDto;
import com.sharebo.entity.Dto.OrderTimeDto;
import com.sharebo.entity.Dto.OrderTimeGetOrderDto;
import com.sharebo.entity.Dto.Times;
import com.sharebo.entity.Dto.TwoOrder;
import com.sharebo.entity.Dto.XuOrder;
import com.sharebo.mapper.OrderMapper;
import com.sharebo.quartz.TaskTimer;
import com.sharebo.service.OrderService;
import com.sharebo.util.Log;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class OrderServiceImpl implements OrderService {
	@Resource
	private OrderMapper mapper;
	//验证该订单时间是否可以预约,通过验证车位时间
	public List<String> valOrderTime(List<OrderTimeDto> list,String parkid) {
		List<String> reslist=new  ArrayList<String>();
		//验证日期规则
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("parkid", parkid);
			//遍历订单中的天数
			for(int i=0;i<list.size();i++){

				String begin_time=list.get(i).getBegin_time();
				String end_time=list.get(i).getEnd_time();
				String thisDay=list.get(i).getThisDate();
				map.put("thisDay",thisDay);
				//验证时间段是否有效
				if(Integer.valueOf(begin_time.substring(0,begin_time.indexOf(":"))).equals(Integer.valueOf(end_time.substring(0,end_time.indexOf(":"))))){
					reslist.add(map.get("thisDay").toString());
					continue;
				}
				//验证小时规则是否存在
				if(mapper.valHoursIsExists(map)>0){
					//存在
					//System.out.println("存在");
					//编写自定义sql验证数据是否合法
					String sql=val_sql(parkid, thisDay, begin_time, end_time);
					if(sql==null){
						//时间有误！
						reslist.add(map.get("thisDay").toString());
						System.out.println("时间："+map.get("thisDay")+"预约时间不合法！");
						continue;
					}
					map.put("sql",sql);//可以覆盖
					System.out.println(sql);
					if(mapper.valDay(map)==0){
						//不可以预约
						reslist.add(map.get("thisDay")+",当前时间段包含不开放时间段！请重新选择");
						//System.out.println("时间："+map.get("thisDay")+"当前时间段包含不开放时间段！");
					}
				}
				else{
					//不存在
						//System.out.println("bu存在");
						//验证星期规则
						//根据日期得到星期几
						int week=DayToWeek(map.get("thisDay").toString());
						map.put("week",week);//可以覆盖
						if(mapper.isdisableWeek(map)>0){
							//禁用
							reslist.add(map.get("thisDay")+"");//+",车位当天不开放！"
							//System.out.println(week+" 不开放");
						}
						else{
							//预约的时间
							int b=Integer.parseInt(begin_time.substring(0,begin_time.indexOf(":")));//开始时间
							int e=Integer.parseInt(end_time.substring(0,end_time.indexOf(":")));//结束时间
							if(b<0||e>24){//判断小时是否合法
								reslist.add(map.get("thisDay")+"");//+",预约时间不合法！"
								continue;
							}
							Map<String,String> m=new HashMap<String, String>();
							//没有禁用
							//验证时间段是否合法
							//查询出当前车位的数据
							Weekrules w=mapper.weekTime(parkid);
							if(w==null){
								reslist.add(map.get("thisDay")+"");//+",用户车位信息异常！"
								Log.getInstance().error("用户车位信息异常："+"车位："+parkid);
								return reslist;
							}
							//处理
							m=toBedinEnd(m,week, w);
							//System.out.println(m.get("begin")+";"+m.get("end"));
							///判断两个时间是否存在交集
							int begin=Integer.valueOf(m.get("begin").substring(0,m.get("begin").indexOf(":")));//星期时间
							int end=Integer.valueOf(m.get("end").substring(0,m.get("end").indexOf(":")));//星期时间
							
							
							//System.out.println(begin+":"+end+"    "+b+":"+e);
							//System.out.println(begin<b);
							//判断开始和结束时间是否包含在设置时间内
							if(!(begin<=b&&end>=e)){
								reslist.add(map.get("thisDay")+"");//+",日期超出了可用时间！"
							}
						}
				}
			}

		return reslist;
	}
	//传入星期，开始时间，结束时间，时间对象 处理对象
	public static Map<String,String> toBedinEnd(Map<String,String> map,int week,Weekrules w){
		String begin=null;
		String end=null;
		if(week==1){//星期日
			begin=w.getSunday_begin();end=w.getSunday_end();
		}else if(week==2){//星期一
			begin=w.getMonday_begin();end=w.getMonday_end();
		}else if(week==3){//星期二
			begin=w.getTuesday_begin();end=w.getTuesday_end();		
		}else if(week==4){//星期三
			begin=w.getWednesday_begin();end=w.getWednesday_end();
		}else if(week==5){//星期四
			begin=w.getThursday_begin();end=w.getThursday_end();
		}else if(week==6){//星期五
			begin=w.getFriday_begin();end=w.getFriday_end();
		}else if(week==7){//星期六
			begin=w.getSaturday_begin();end=w.getSaturday_end();
		}
		map.put("begin", begin);
		map.put("end", end);
		return map;
	}
	//根据开始时间，结束时间 parkid生成sql语句   目的：查询结果为空
	public static String val_sql(String parkid,String thisDate,String begin,String end){
		int b=Integer.parseInt(begin.substring(0,begin.indexOf(":")));//开始时间
		int e=Integer.parseInt(end.substring(0,end.indexOf(":")));//结束时间
		StringBuffer sb=new StringBuffer();
		sb.append("parkid='"+parkid+"' and thisDay='"+thisDate+"'");
		if(b<0||e>24){//判断小时是否合法
			return  null;
		}
		for(int i=b;i<e;i++){
			//编写sql
			if(i==0){
				sb.append(" and hours_24=1");
			}
			else{
				sb.append(" and hours_"+(i<10?"0"+i:i)+"=1");
			}
		}
		return sb.toString();
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
	//验证一组时间是否存在交集（已经预约）
	public List<String> valOtherTime(List<OrderTimeDto> list, String parkid) {
		List<String> reslist=new  ArrayList<String>();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("parkid", parkid);
		String sql=valOtheTimeSql(list);
		if(sql==null){
			reslist.add("订单时间不合法！");
			return reslist;
		}
		map.put("sql", sql);
		reslist=mapper.valOrderTimeIsExistx(map);
		return reslist;
	}
	//拼接sql
	public static String valOtheTimeSql(List<OrderTimeDto> otd){
		if(otd.size()==0){
			return null;
		}
		StringBuffer sb=new StringBuffer();
		sb.append("and(");
		for (int i=0;i<otd.size();i++) {
			OrderTimeDto od=otd.get(i);
			sb.append("	(	thisdate='"+od.getThisDate()+"' and   ( (begin_time>='"+od.getBegin_time()+"' and begin_time<='"+od.getEnd_time()+"') or  (begin_time<='"+od.getBegin_time()+"' and end_time>=  '"+od.getEnd_time()+"') or (end_time>=  '"+od.getBegin_time()+"' and end_time<=    '"+od.getEnd_time()+"')))");
			 if(i<otd.size()-1){
				 sb.append(" or ");
			 }
		}
		sb.append(")");
		//System.out.println(sb.toString());
		return sb.toString();
	}
	//添加订单
	public boolean addOrder(List<OrderTimeDto> list,OrderInfo order,String orderId){
		Map<String, Object> map=new HashMap<String, Object>();
		//计算费用
		//0：按次收费 1：按小时收费
		int type=mapper.getchargetypeByParkid(order.getPark().getParkid());
		double money=mapper.getMoneyByparkid(order.getPark().getParkid());
		order.setChargeType(type);//收费类型
		order.setTypemoney(money);//单价
		order.setMoney(computationalCost(type,list,money));//总价
		map.put("order", order);
		String sql=valOtheTimeSql(list);
		map.put("sql", sql);
		//先添加订单
		try {
			if(mapper.addOrder(map)==0){//未添加成功！
				return false;
			}
		} catch (Exception e) {
			//订单添加失败！ 错误原因：订单时间有冲突
			return false;
		}
		
		try {
			//然后添加订单时间
			String addOrderTimeSql=addOrderTime_Sql(list,orderId);
			map.put("ordertimesql", addOrderTimeSql);
			int row=mapper.addOrdertime(map);
			if(row!=list.size()){
				mapper.deleteOrder(orderId);
			}
			//添加成功
		} catch (Exception e) {
			//删除订单（自动会回滚）
			mapper.deleteOrder(orderId);
			return false;
		}
		//添加订单计时器
		TaskTimer.addJob(new TaskInfo(orderId,orderId), com.sharebo.quartz.CancelTheOrder.class, 60*5);
		return true;
	}
	//计算费用
	public static double computationalCost(int type,List<OrderTimeDto> list,double money){
		if(type==0){//按次数收费
			return money*list.size();
		}
		else{
			int hours=0;
			//按小时收费
			for(int i=0;i<list.size();i++){
				OrderTimeDto otd=list.get(i);
				//得到开始时间，结束时间
				int b=Integer.parseInt(otd.getBegin_time().substring(0,otd.getBegin_time().indexOf(":")));//开始时间
				int e=Integer.parseInt(otd.getEnd_time().substring(0,otd.getEnd_time().indexOf(":")));//结束时间
				hours+=e-b;//计算小时
			}
			return hours*money;
		}
	} 
	//拼接添加订单时间的sql语句
	public static String addOrderTime_Sql(List<OrderTimeDto> list,String orderId){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<list.size();i++){
			OrderTimeDto o=list.get(i);
			sb.append("('"+UUID.randomUUID().toString()+"','"+o.getBegin_time()+"','"+o.getEnd_time()+"','"+o.getThisDate()+"','"+orderId+"')");
			if(i<list.size()-1){
				sb.append(",");
			}
		}
		return sb.toString();
	}
	//验证车位是否存在
	public int valParkIsExistsByparkid(String parkid) {
		return mapper.valParkIsExistsByparkid(parkid);
	}
	//验证时间是否合法
	public boolean valOrdetimeisoverdue(List<OrderTimeDto> list){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (OrderTimeDto orderTimeDto : list) {
			String time=orderTimeDto.getThisDate()+" "+orderTimeDto.getBegin_time();
			try {
				Date ot=sdf.parse(time);//订单时间
				if(!new Date().before(ot)){//订单时间小于当前时间（不合格）
					return false;
				}
			} catch (ParseException e) {
				return false;
			}
			
		}
		return true;
	}
	/**
	 * 修改订单状态为已取消
	 */
	public int updateOrderState(String orderid){
		return mapper.updateOrderState(orderid);
	}
	/**
	 * 查询全部未支付的订单
	 */
	public List<OrderCancelDto> getOrderInfoByDisNotPay() {
		return mapper.getOrderInfoByDisNotPay();
	}
	/**
	 * 根据订单id 查询订单详情
	 */
	public OrderInfoDto getOrderInfo(String orderid) {
		return mapper.getOrderInfo(orderid);
	}
	/**
	 * 根据订单id 查询订单时间表
	 */
	public List<OrderTimeGetOrderDto> getOrderTimeInfo(String orderid) {
		return mapper.getOrderTimeInfo(orderid);
	}
	public List<GongOrder> queryGongOrder(Map<String,Object> map) {
		return mapper.queryGongOrder(map);
	}
	public List<Times> queryTimes(String orderid) {
		return mapper.queryTimes(orderid);
	}
	public List<TwoOrder> queryTwoOrder(String userid) {
		return mapper.queryTwoOrder(userid);
	}
	public List<XuOrder> qureyXuOrder(Map<String,Object> map) {
		return mapper.qureyXuOrder(map);
	}
	
	public int pagerbyXuCount(String orderid) {
		return mapper.pagerbyXuCount(orderid);
	}
	public int pagerbyGongCount(String parkid) {
		return mapper.pagerbyGongCount(parkid);
	}
	public String queryByUserid(String userid) {
		return mapper.queryByUserid(userid);
	}
	public List<String> queryParkid(String userid) {
		return mapper.queryParkid(userid);
	}
	/**
	 * 修改订单状态
	 * @param Order
	 * @return
	 */
	public int updateOrderInfoByordernum(OrderInfo Order) {
		return mapper.updateOrderInfoByordernum(Order);
	}
	/**
	 * 查询订单号用户id
	 */
	public String getOrdernumByUserid(String ordernum) {
		return mapper.getOrdernumByUserid(ordernum);
	}
	/**
	 * 通过订单号码查询订单id
	 */
	public String getOrderIdByOrderNum(String ordernum) {
		return mapper.getOrderIdByOrderNum(ordernum);
	}
	/**
	 * 修改接受拒绝状态
	 */
	public int updatestateByordernum(OrderInfo Order) {
		return mapper.updatestateByordernum(Order);
	}
	/**
	 *  查询订单id查询需要退款的的金额和查询用户id
	 */
	public OrderDto getorderidByorder(String orderid) {
		return mapper.getorderidByorder(orderid);
	}
	/**
	 * 退还用户支付的金额
	 * @param orderid
	 * @return
	 */
	public int updateBybalances(Purse purse) {
		return mapper.updateBybalances(purse);
	}
	/***
	 * 查询供方的订单
	 */
	public List<GongOrder> queryGongOrder(String orderid) {
		return mapper.queryGongOrder(orderid);
	}
	/**
	 * 根据订单号查询手机号码
	 */
	public String getmobileByOrderNum(String ordernum) {
		return mapper.getmobileByOrderNum(ordernum);
	}
	/**
	 * 根据支付返回的订单号查询车牌
	 */
	public String getvehicleidByOrderNum(String ordernum) {
		return mapper.getvehicleidByOrderNum(ordernum);
	}
	/**
	 * 根据订单主键id查询userid
	 * @param orderid
	 * @return
	 */
	public String getmobileByOrderid(String orderid) {
		return mapper.getmobileByOrderid(orderid);
	}
}

