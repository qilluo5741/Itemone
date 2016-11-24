package com.sharebo.entity.Dto;

import java.io.Serializable;
/**
 * 车位管理查询
 * @author Administrator
 */
public class ManagementDto  implements  Serializable{
	private static final long serialVersionUID = 1L;
	private String parkid;
	private String parkNo;
	private String userName;
	private String mobile;
	private double money;
	private int chargetype;
	private int is_delete;
	private String releasedate;
	private String update_time;
	private String community_name;
	private String park_instructions;
	private String entrance;
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
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int isDelete) {
		is_delete = isDelete;
	}
	public String getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String updateTime) {
		update_time = updateTime;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String communityName) {
		community_name = communityName;
	}
	public String getPark_instructions() {
		return park_instructions;
	}
	public void setPark_instructions(String parkInstructions) {
		park_instructions = parkInstructions;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
}
