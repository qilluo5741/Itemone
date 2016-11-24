package com.sharebo.entity.Dto;

import java.io.Serializable;


public class UserInfoAllDto implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;//用户ID
	private String userName;//用户姓名
	private String userAccount;//用户账号
	private int userStatus;//用户状态
	private String userTime;//用户注册时间
	private String userRemark;//用户说明（介绍）
	private String roleName;//角色名称
	private String roleId;//角色id
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getUserTime() {
		return userTime;
	}
	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
