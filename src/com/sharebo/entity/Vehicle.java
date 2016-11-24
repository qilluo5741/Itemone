package com.sharebo.entity;
/**
 * 车辆表
 * @author Administrator
 *
 */
public class Vehicle implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  vehicleid;//'车辆主键id',
	private String  vehicleno;//'车牌号',
	private UserInfo user;
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public Vehicle() {
	}
	public Vehicle(String vehicleid) {
		this.vehicleid = vehicleid;
	}
}
