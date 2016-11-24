package com.sharebo.entity.Dto;

import java.io.Serializable;


public class ParkingDto implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int parkstate;//'车位状态（0：空闲 1：繁忙）',
	private int chargetype;//'收费类型（0：按次收费 1：按小时收费）',
	private String username;//姓名
	private String mobile ;//手机号
	private String parkid;//车位id;
	private String money;//单价
	
	public String getParkid() {
		return parkid;
	}
	public void setParkid(String parkid) {
		this.parkid = parkid;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public ParkingDto() {
	}
	public int getParkstate() {
		return parkstate;
	}
	public void setParkstate(int parkstate) {
		this.parkstate = parkstate;
	}
	public int getChargetype() {
		return chargetype;
	}
	public void setChargetype(int chargetype) {
		this.chargetype = chargetype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
