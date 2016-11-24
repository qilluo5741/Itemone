package com.sharebo.entity.Dto;

import java.io.Serializable;

public class UserInfoDto implements  Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;//'用户姓名',
	private String mobile;//'手机号码',
	private int sex;//'性别(0:未填写， 1：男    2  女 )',
	private int age;//'年龄',
	private String payno;//'支付宝号码',
	private int certification_status;// '认证状态（0：未认证，1：已认证，2：拒绝认证）',
	private int totalVehicle;//车辆总数
	private String headportrait;//'头像路径',
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPayno() {
		return payno;
	}
	public void setPayno(String payno) {
		this.payno = payno;
	}
	public int getCertification_status() {
		return certification_status;
	}
	public void setCertification_status(int certificationStatus) {
		certification_status = certificationStatus;
	}
	public int getTotalVehicle() {
		return totalVehicle;
	}
	public void setTotalVehicle(int totalVehicle) {
		this.totalVehicle = totalVehicle;
	}
	public String getHeadportrait() {
		return headportrait;
	}
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
}
