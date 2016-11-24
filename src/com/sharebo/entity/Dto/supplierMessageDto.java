package com.sharebo.entity.Dto;

import java.io.Serializable;

/**
 *供方假实体
 */
public class supplierMessageDto implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double money;
	private int chargetype;
	private String community_address;
	private String community_name;
	private int order_state;
	private String vehicleno;
	private String userName;
	private String orderid;
	private String msgContent;
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getChargetype() {
		return chargetype;
	}
	public void setChargetype(int chargetype) {
		this.chargetype = chargetype;
	}
	public String getCommunity_address() {
		return community_address;
	}
	public void setCommunity_address(String communityAddress) {
		community_address = communityAddress;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String communityName) {
		community_name = communityName;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int orderState) {
		order_state = orderState;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
}
