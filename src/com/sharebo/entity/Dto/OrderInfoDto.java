package com.sharebo.entity.Dto;

import java.io.Serializable;
import java.util.List;

public class OrderInfoDto  implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderid; //订单id
	private String ordernum; //订单流水号
	private String vehicleid;//车牌号
	private double money;//总金额
	private int order_state ;//订单状态
	private double typemoney;//单价
	private int chargetype;//收费类型
	private String community_name;//小区名字
	private String community_address;//小区地址
	private String parkNo;//车位号
	private int parkCount;//停车次数/时间
	
	public int getParkCount() {
		return parkCount;
	}
	public void setParkCount(int parkCount) {
		this.parkCount = parkCount;
	}
	private List<com.sharebo.entity.Dto.OrderTimeGetOrderDto> ordertime;
	
	public String getParkNo() {
		return parkNo;
	}
	public void setParkNo(String parkNo) {
		this.parkNo = parkNo;
	}
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
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getTypemoney() {
		return typemoney;
	}
	public void setTypemoney(double typemoney) {
		this.typemoney = typemoney;
	}
	
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int orderState) {
		order_state = orderState;
	}
	public int getChargetype() {
		return chargetype;
	}
	public void setChargetype(int chargetype) {
		this.chargetype = chargetype;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String communityName) {
		community_name = communityName;
	}
	public String getCommunity_address() {
		return community_address;
	}
	public void setCommunity_address(String communityAddress) {
		community_address = communityAddress;
	}
	public List<com.sharebo.entity.Dto.OrderTimeGetOrderDto> getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(
			List<com.sharebo.entity.Dto.OrderTimeGetOrderDto> ordertime) {
		this.ordertime = ordertime;
	}
	
}
