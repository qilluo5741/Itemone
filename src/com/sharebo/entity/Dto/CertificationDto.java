package com.sharebo.entity.Dto;

import java.io.Serializable;

public class CertificationDto implements  Serializable{
	private static final long serialVersionUID = 1L;
	private String certificationId;
	private String driver_license_positive;
	private String driver_license_reverse;
	private String vehicle_license_positive;
	private String vehicle_license_reverse;
	private String email;
	private String audittime;
	private String thistime;
	private String userName;
	private String mobile;
	private int certification_status;
	public String getCertificationId() {
		return certificationId;
	}
	public void setCertificationId(String certificationId) {
		this.certificationId = certificationId;
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
	public String getAudittime() {
		return audittime;
	}
	public void setAudittime(String audittime) {
		this.audittime = audittime;
	}
	public String getThistime() {
		return thistime;
	}
	public void setThistime(String thistime) {
		this.thistime = thistime;
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
	public int getCertification_status() {
		return certification_status;
	}
	public void setCertification_status(int certificationStatus) {
		certification_status = certificationStatus;
	}
}
