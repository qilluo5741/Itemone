package com.sharebo.entity.Dto;

import java.io.Serializable;

public class InformationDto  implements  Serializable{
	private static final long serialVersionUID = 1L;
	private String parkid;
	private String userName;//用户姓名'
	private int sex;//'性别(0:未填写， 1：男    2  女 )'
	private String entrance;//入口地址--Parkingspace
	private int parkstate;//车位状态（0：空闲 1：繁忙）'
	private double money;//'停车费用',
	private int chargetype;//收费类型（0：按次收费 1：按小时收费）
	public String getParkid() {
		return parkid;
	}
	public void setParkid(String parkid) {
		this.parkid = parkid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public int getParkstate() {
		return parkstate;
	}
	public void setParkstate(int parkstate) {
		this.parkstate = parkstate;
	}
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
}