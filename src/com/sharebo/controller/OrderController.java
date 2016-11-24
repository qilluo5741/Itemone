package com.sharebo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.Message;
import com.sharebo.entity.OrderInfo;
import com.sharebo.entity.Parkingspace;
import com.sharebo.entity.Purse;
import com.sharebo.entity.ResultDto;
import com.sharebo.entity.TouchBalance;
import com.sharebo.entity.UserInfo;
import com.sharebo.entity.Dto.OrderDto;
import com.sharebo.entity.Dto.OrderInfoDto;
import com.sharebo.entity.Dto.OrderTimeDto;
import com.sharebo.entity.Dto.OrderTimeGetOrderDto;
import com.sharebo.entity.Dto.TwoOrder;
import com.sharebo.entity.Dto.XuOrder;
import com.sharebo.service.MessageService;
import com.sharebo.service.OrderService;
import com.sharebo.service.PurseService;
import com.sharebo.service.TouchBalanceService;
import com.sharebo.util.Pager;
import com.sharebo.util.SmsSendClientExample;

@RestController
@RequestMapping("/{sign}/order")
public class OrderController {
	@Resource
	private OrderService service;
	@Resource
	private MessageService msgService;
	@Autowired
	private PurseService purseService;
	@Autowired
	private TouchBalanceService touchService;
	//http://localhost:8080/sharebo/{sign}/order/addOrder.do
	//提交订单vehicleName=沪A45678&userid=11111111111&parkid=111111&ordertime=[{"begin_time":"7:00","end_time":"20:00","thisDate":"2016-5-12"},{"begin_time":"9:00","end_time":"20:00","thisDate":"2016-5-13"}]
	@SuppressWarnings("unchecked")
	@RequestMapping("addOrder")
	public ResultDto addOrder(String vehicleName, String userid, String parkid,
			String ordertime) {
		System.out.println(ordertime);
		if (vehicleName == null || userid == null || parkid == null
				|| ordertime == null) {
			return new ResultDto(1004, "参数不合法！");
		}
		// 验证预约时间是否正常（本地数据）
		// （不可预约：1.设置小时状态为0 2.订单时间表已经存在时间段）。
		// 验证数据是否有效（本地数据）
		List<OrderTimeDto> list = JSONArray.toList(JSONArray
				.fromObject(ordertime), new OrderTimeDto(), new JsonConfig());
		/**************************** 验证订单时间是否合法 ***********************************************/
		if (!service.valOrdetimeisoverdue(list)) {
			return new ResultDto(10020, "预约时间不能小于当前时间！");// 车位不存在
		}
		// 车牌号
		/**************** 验证该车位是否存在 *****************/
		//
		if (service.valParkIsExistsByparkid(parkid) == 0) {
			// 车位不存在
			return new ResultDto(10021, "车位不存在");// 车位不存在
		}
		/***************************************************************************/
		// 验证预约时间是否大于0
		List<String> listStr = service.valOrderTime(list, parkid);// 时间和 parkid
		if (listStr.size() > 0) {// 如果大于0 说明有地方不规范
			return new ResultDto(10022, "已经超出可预约时间", listStr);// 预约时间不合法，或者已经超出可用时间
		}
		/***************************************************************************/
		// 验证是否已经预约（数据库数据）
		listStr = service.valOtherTime(list, parkid);
		if (listStr.size() > 0) {// 如果大于0 说明有地方不规范
			return new ResultDto(10023, "预约时间无效，或者是已经被预约", listStr);// 预约时间无效，或者是已经被预约
		}
		String orderId = UUID.randomUUID().toString();
		OrderInfo order = new OrderInfo();
		Parkingspace park = new Parkingspace();
		park.setParkid(parkid);
		order.setPark(park);
		// 年月日时分秒毫秒（13位）+随机数（4）
		String orderNum = new Date().getTime()
				+ RandomStringUtils.randomNumeric(4);
		order.setOrdernum(orderNum);
		order.setOrderid(orderId);
		order.setVehicleid(vehicleName);
		order.setFrom_user(new UserInfo(userid));
		if (service.addOrder(list, order, orderId)) {
			// 添加一个消息记录
			msgService.AddMessage(new Message(UUID.randomUUID().toString(), 1,
					"", new UserInfo(userid), new OrderInfo(orderId),
					new Date()));
			return new ResultDto(200, "订单生成成功！", orderId);
		}
		return new ResultDto(10024, "订单生成失败！");
	}

	// 查询订单
	@RequestMapping("getOrder")
	public ResultDto getOrder(String orderid) {
		if (orderid == null) {
			return new ResultDto(1004, "参数不合法！");
		}
		OrderInfoDto od = service.getOrderInfo(orderid);
		if (od == null) {
			return new ResultDto(00000, "查询失败！请稍后再试！");
		}
		// 查询订单时间
		List<OrderTimeGetOrderDto> ordertime = service.getOrderTimeInfo(od.getOrderid());
		// 计算时间 次数
		od.setParkCount(computationalCost(od.getChargetype(), ordertime));
		od.setOrdertime(ordertime);
		return new ResultDto(200, od);
	}

	// 计算次数
	public static int computationalCost(int type,List<OrderTimeGetOrderDto> list) {
		if (type == 0) {// 按次数收费
			return list.size();
		} else {
			int hours = 0;
			// 按小时收费
			for (int i = 0; i < list.size(); i++) {
				OrderTimeGetOrderDto otd = list.get(i);
				// 得到开始时间，结束时间
				int b = Integer.parseInt(otd.getBegin_time().substring(0,
						otd.getBegin_time().indexOf(":")));// 开始时间
				int e = Integer.parseInt(otd.getEnd_time().substring(0,
						otd.getEnd_time().indexOf(":")));// 结束时间
				hours += e - b;// 计算小时
			}
			return hours;
		}
	}

	// 需方订单查询
	@RequestMapping("qureyXuOrder")
	public ResultDto qureyXuOrder(String userid, int pageIndex, int pageSize) {
		if (userid == null || pageIndex < 1 || pageSize == 0) {
			return new ResultDto(4001, "参数异常");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int pageBegin = (pageIndex - 1) * pageSize;
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pageSize);
		map.put("userid", userid);
		List<XuOrder> os = service.qureyXuOrder(map);
		if (os != null) {
			for (XuOrder xo : os) {
				xo.setTimes(this.service.queryTimes(xo.getOrderid()));
			}
		}
		Pager<XuOrder> p = new Pager<XuOrder>();
		p.setPageSize(pageSize);
		p.setPageIndex(pageIndex);
		p.setTotalRecords(service.pagerbyXuCount(userid));
		p.setTotalPages();// 设置总页数
		// 设置数据
		p.setList(os);
		return new ResultDto(200,p);
	}

	// 查看最近的两条订单
	@RequestMapping("queryTwoorder")
	public ResultDto queryGongOrder(String userid) {
		if (userid == null) {
			return new ResultDto(4001, "参数异常");
		}
		List<TwoOrder> os = this.service.queryTwoOrder(userid);
		if (os != null) {
			return new ResultDto(200, os);
		} else {
			return new ResultDto(4001, "获取值异常");
		}
	}
	//查询供方的订单
	@RequestMapping("getGongOrder")
	public ResultDto getGongOrderInfo(String userid){
		//根据userid查询供方的车位id 
		//通过车位id 查询订单 订单状态不能为0  和 3
		return new ResultDto(200,service.queryGongOrder(userid));
	}
	
	//订单确认：订单id  确认状态（接受拒绝）
	@RequestMapping("confirmOrder")
	public ResultDto confirmOrder(String orderid,int state){
		try {
			//验证订单是否存在(以及订单状态是否为已经支付)
			if(state==3){
				OrderInfo Order=new OrderInfo();
				Order.setOrder_state(state);//订单要修改的状态
				Order.setOrderid(orderid);//订单id
				int i=service.updatestateByordernum(Order);//执行操作
				String mobile=service.getmobileByOrderid(orderid);
				SmsSendClientExample.sendisMessage(mobile,"");
				return new ResultDto(200,"接受成功！",i);
			}else if(state==4){
				OrderInfo Order=new OrderInfo();
				Order.setOrder_state(state);//订单要修改的状态
				Order.setOrderid(orderid);//订单id
				int i=service.updatestateByordernum(Order);
				OrderDto order=	service.getorderidByorder(orderid);
				double money=order.getMoney();//得到退款金额
				String userid=order.getFrom_userid();//得到用户id
				double balance=purseService.getPurseBybalance(userid);//用户账户余额
				double balances=balance+money;//计算金额
				Purse purse=new Purse();
				purse.setBalance(balances);
				purse.setUser(new UserInfo(userid));
				//退还拒绝订单金额
				int b=service.updateBybalances(purse);//执行操作
				Message message=new Message();
				message.setMsgid(UUID.randomUUID().toString().replaceAll("-", ""));
				message.setMsgType(1);//文字消息
				message.setMsgContent(null);
				message.setUser(new UserInfo(userid));
				message.setOrder(new OrderInfo(orderid));
				message.setMessageTime(new Date());
				int m=msgService.AddMessage(message);
				/**
				 * 根据用户id查询purseid
				 */
				String purseid =touchService.getpurseBypurseid(userid);
				//创建明细记录空对象
				TouchBalance touchbalance=new TouchBalance();
				touchbalance.setTbid(UUID.randomUUID().toString().replaceAll("-", ""));//创建uuid并且去掉含有-的	
				touchbalance.setMoney(money);//回调中金额
				touchbalance.setOperation_time(new Date());//支付时间
				touchbalance.setTradtype(3);//0支付宝1微信2余额3退款
				touchbalance.setPurse(new Purse(purseid));
				int j=touchService.addTouchBalance(touchbalance);//添加余额明细记录
				System.out.println(i+b+m+j);
				String mobile=service.getmobileByOrderid(orderid);
				SmsSendClientExample.sendisMessage(mobile,"");
				return new ResultDto(200,"拒绝成功！");
			}else{
				return new ResultDto(2002,"请求参数为空！");
			}
			//更改状态，退款，，以及（推送），以及添加消息。判断是否以及接受
		} catch (Exception e) {
			return new ResultDto(2002,"请求参数为空！");
		}
	}
}
