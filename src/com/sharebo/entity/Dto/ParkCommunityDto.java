package com.sharebo.entity.Dto;

import java.io.Serializable;

public class ParkCommunityDto implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String communityId;         // '小区表主键ID',
	private String community_name;      // '小区名字',
	private String community_address;   // '小区地址',
	//入口
	//类型
	//车位编号
	//单价
	//收费类型
	//停车说明
	private String parkNo;//'车位编号',
	private String park_instructions;//'车位具体说明',
	private double money;//停车费用
	private int chargetype;//'收费类型（0：按次收费 1：按小时收费）',
	private String entrance;//入口
	private String parkType;//车位类型
	private String parkid;//'车位id',
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
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
	public String getParkNo() {
		return parkNo;
	}
	public void setParkNo(String parkNo) {
		this.parkNo = parkNo;
	}
	public String getPark_instructions() {
		return park_instructions;
	}
	public void setPark_instructions(String parkInstructions) {
		park_instructions = parkInstructions;
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
	public String getParkid() {
		return parkid;
	}
	public void setParkid(String parkid) {
		this.parkid = parkid;
	}
	
}
