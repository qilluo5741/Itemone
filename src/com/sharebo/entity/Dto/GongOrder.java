package com.sharebo.entity.Dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GongOrder  implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderid;
	   private String ordernum;
	   private String order_state;
	   private double typemoney;//单价
	   private double money;//总价
	   private String mobile;
	   private Date placeorde_time;//支付时间
	   private String vehicleno;
	   List<Times> times;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String orderState) {
		order_state = orderState;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	
	public List<Times> getTimes() {
		return times;
	}
	public void setTimes(List<Times> times) {
		this.times = times;
	}
	public GongOrder() {
	}
	public double getTypemoney() {
		return typemoney;
	}
	public void setTypemoney(double typemoney) {
		this.typemoney = typemoney;
	}
	public Date getPlaceorde_time() {
		return placeorde_time;
	}
	public void setPlaceorde_time(Date placeordeTime) {
		placeorde_time = placeordeTime;
	}

	  
}
