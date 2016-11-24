package com.sharebo.entity.Dto;

import java.io.Serializable;


public class ParkingdetailDto implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String park_instructions;//'车位具体说明',
	private int parkstate;//'车位状态（0：空闲 1：繁忙）',
	private double money;//停车费用
	private int chargetype;//'收费类型（0：按次收费 1：按小时收费）',
	private String entrance;//入口
	private String parkType;//车位类型
	private String community_address;//'小区地址',
	private int is_collect;//是否收藏
	public String getPark_instructions() {
		return park_instructions;
	}
	public void setPark_instructions(String parkInstructions) {
		park_instructions = parkInstructions;
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
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getParkType() {
		return parkType;
	}
	public void setParkType(String parkType) {
		this.parkType = parkType;
	}
	public String getCommunity_address() {
		return community_address;
	}
	public void setCommunity_address(String communityAddress) {
		community_address = communityAddress;
	}
	public int getIs_collect() {
		return is_collect;
	}
	public void setIs_collect(int isCollect) {
		is_collect = isCollect;
	}
}
