package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;
//车位表
public class Parkingspace  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String parkid;//'车位主键ID',
	private String parkNo;//'车位编号',
	private String park_instructions;//'车位具体说明',
	private Date releasedate;//'发布时间',
	private Date dapdate_time;//'车位更新时间',
	private int parkstate;//'车位状态（0：空闲 1：繁忙）',
	private double money;//停车费用
	private int chargetype;//'收费类型（0：按次收费 1：按小时收费）',
	private UserInfo user;
	private Community community;//'小区外键',
	private String entrance;//入口
	private String parkType;//车位类型
	private int is_delete;//'是否删除（0：未删除 1：已经删除）',
	
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
	public Date getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}
	public Date getDapdate_time() {
		return dapdate_time;
	}
	public void setDapdate_time(Date dapdateTime) {
		dapdate_time = dapdateTime;
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
	
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public Community getCommunity() {
		return community;
	}
	public void setCommunity(Community community) {
		this.community = community;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int isDelete) {
		is_delete = isDelete;
	}
	public Parkingspace() {
	}
	public Parkingspace(String parkid) {
		this.parkid = parkid;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	@Override
	public String toString() {
		return "Parkingspace [chargetype=" + chargetype + ", community="
				+ community + ", dapdate_time=" + dapdate_time + ", is_delete="
				+ is_delete + ", money=" + money + ", parkNo=" + parkNo
				+ ", park_instructions=" + park_instructions + ", parkid="
				+ parkid + ", parkstate=" + parkstate + ", releasedate="
				+ releasedate + ", user=" + user + "]";
	}
}
