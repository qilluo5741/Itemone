package com.sharebo.entity.Dto;

import java.io.Serializable;
import java.util.List;

public class XuOrder implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
      private String orderid;
      private String ordernum  ;
      private String order_state ;
      private double money ;
      private String placeorde_time ;
      private String vehicleno ;
      private String scommunity_name;
      public List<Times> times;
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
	public String getPlaceorde_time() {
		return placeorde_time;
	}
	public void setPlaceorde_time(String placeordeTime) {
		placeorde_time = placeordeTime;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getScommunity_name() {
		return scommunity_name;
	}
	public void setScommunity_name(String scommunityName) {
		scommunity_name = scommunityName;
	}
	
	public List<Times> getTimes() {
		return times;
	}
	public void setTimes(List<Times> times) {
		this.times = times;
	}
	public XuOrder(String orderid, String ordernum, String orderState,
			double money, String placeordeTime, String vehicleno,
			String scommunityName, List<Times> times) {
		this.orderid = orderid;
		this.ordernum = ordernum;
		order_state = orderState;
		this.money = money;
		placeorde_time = placeordeTime;
		this.vehicleno = vehicleno;
		scommunity_name = scommunityName;
		this.times = times;
	}
	public XuOrder() {
	}
}
