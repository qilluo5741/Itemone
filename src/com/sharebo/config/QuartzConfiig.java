package com.sharebo.config;

public class QuartzConfiig {
	//自动取消订单
	public static final String CANCEL_URL="http://localhost:8080/sharebo/quart/closeOrder.do";
	//触发自动取消订单事件
	public static final String AUTOMATIC_URL="http://localhost:8080/sharebo/quart/addAllOrder.do";
}
