package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;
//车主认证表
public class Certification  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String certificationId;      //'车辆认证表主键',
	private UserInfo user;              // '用户ID(外键)',
	private Vehicle vehicle;            //'车辆ID(外键)',
	private String driver_license_positive;//    '驾驶证（正面）',
	private String driver_license_reverse;  //   '驾驶证（反面）',
	private String vehicle_license_positive;//   '行驶证（正面）',
	private String vehicle_license_reverse; //   '行驶证（背面）',
	private String email;                //邮箱
	private int certification_status;    // '认证状态（0：未认证，1：已认证，2：拒绝认证）',
	private Date thistime;          //'认证时间',
	private Date audittime;//审核时间
	public String getCertificationId() {
		return certificationId;
	}
	public void setCertificationId(String certificationId) {
		this.certificationId = certificationId;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public String getDriver_license_positive() {
		return driver_license_positive;
	}
	public void setDriver_license_positive(String driverLicensePositive) {
		driver_license_positive = driverLicensePositive;
	}
	public String getDriver_license_reverse() {
		return driver_license_reverse;
	}
	public void setDriver_license_reverse(String driverLicenseReverse) {
		driver_license_reverse = driverLicenseReverse;
	}
	public String getVehicle_license_positive() {
		return vehicle_license_positive;
	}
	public void setVehicle_license_positive(String vehicleLicensePositive) {
		vehicle_license_positive = vehicleLicensePositive;
	}
	public String getVehicle_license_reverse() {
		return vehicle_license_reverse;
	}
	public void setVehicle_license_reverse(String vehicleLicenseReverse) {
		vehicle_license_reverse = vehicleLicenseReverse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCertification_status() {
		return certification_status;
	}
	public void setCertification_status(int certificationStatus) {
		certification_status = certificationStatus;
	}
	public Date getThistime() {
		return thistime;
	}
	public void setThistime(Date thistime) {
		this.thistime = thistime;
	}
	public Date getAudittime() {
		return audittime;
	}
	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}              
}
