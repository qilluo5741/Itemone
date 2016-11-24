package com.sharebo.entity.Dto;

import java.io.Serializable;

public class OrderDto  implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderid;             
	private double money;//'订单金额',
	private String from_userid;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getFrom_userid() {
		return from_userid;
	}
	public void setFrom_userid(String fromUserid) {
		from_userid = fromUserid;
	}
}
