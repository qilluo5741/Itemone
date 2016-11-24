package com.sharebo.entity.Dto;

import java.io.Serializable;

/**
 * 车辆表
 * @author Administrator
 *
 */
public class VehicleDto implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  vehicleid;//'车辆主键id',
	private String  vehicleno;//'车牌号',
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
}
