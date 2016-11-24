package com.sharebo.entity.Dto;

import java.io.Serializable;

public class OwnerorderDto implements  Serializable{
	private static final long serialVersionUID = 1L;
	private String ordernum;
	private String parkno;
	private String placeorde_time;
	private String supplierconfirm_time;
	private String payment_time;
	private String payType;
	private String order_state;
	private String vehicleid;
	private String userName;
	private String mobile;
	private String money;
	private String chargetype;
	public String getOrdernum(){
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getParkno() {
		return parkno;
	}
	public void setParkno(String parkno) {
		this.parkno = parkno;
	}
	public String getPlaceorde_time() {
		return placeorde_time;
	}
	public void setPlaceorde_time(String placeordeTime) {
		placeorde_time = placeordeTime;
	}
	public String getSupplierconfirm_time() {
		return supplierconfirm_time;
	}
	public void setSupplierconfirm_time(String supplierconfirmTime) {
		supplierconfirm_time = supplierconfirmTime;
	}
	public String getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(String paymentTime) {
		payment_time = paymentTime;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String orderState) {
		order_state = orderState;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getChargetype() {
		return chargetype;
	}
	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}
}
