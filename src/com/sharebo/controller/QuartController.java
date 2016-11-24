package com.sharebo.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.ResultDto;
import com.sharebo.entity.TaskInfo;
import com.sharebo.entity.Dto.OrderCancelDto;
import com.sharebo.quartz.TaskTimer;
import com.sharebo.service.OrderService;
/***
 * 计时器处理
 * 订单超时处理
 * @author niewei
 */
@RestController
@RequestMapping("quart")
public class QuartController{
	@Resource
	private OrderService service;
	
	@RequestMapping("closeOrder")
	public String test(TaskInfo t){
		//订单超时业务处理
		System.out.println("订单id："+t.getOrderNo());
		if(service.updateOrderState(t.getOrderNo())>0){
			//订单已经取消
			return "true";
		}
		return "false";
	}
	//启动项目查询所有处于未支付的订单，添加计时器处理
	@PostConstruct
	public String addAllOrder(){
		//查询所有的未支付的订单
		List<OrderCancelDto> list=service.getOrderInfoByDisNotPay();
		//遍历所有的订单
		for (OrderCancelDto ocd : list) {
			//得到相差秒
			int  ss=getIntervalDays(ocd.getPlaceorde_time());
			//5分钟的秒数：
			int ss_5=5*60;
			int counts=ss-ss_5;
			if(counts<0){//还有剩余时间
				TaskTimer.addJob(new TaskInfo(ocd.getOrderid(),ocd.getOrderid()), com.sharebo.quartz.CancelTheOrder.class,Math.abs(counts));
			}else{//直接添加任务
				TaskTimer.addJob(new TaskInfo(ocd.getOrderid(),ocd.getOrderid()), com.sharebo.quartz.CancelTheOrder.class,2);
			}
		}
		return "true";
	}
	@RequestMapping("500")
	public ResultDto _500(){
		return new ResultDto(500,"请求处理异常，请稍后再试！");
	}
	@RequestMapping("404")
	public ResultDto _404(){
		return new ResultDto(404,"请求路径不存在！");
	}
	/**
	 * 计算一个时间 与当前时间两个时间差： 秒
	 * @param date
	 * @return
	 */
	public static int getIntervalDays(Date othertime) {
		//获取当前时间
		Date thisDate=new Date();
		long between=( thisDate.getTime() - othertime.getTime())/1000;//除以1000是为了转换成秒
	    return (int) between;
	}
}

