package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;
//记录好友
public class InvitedRecord  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String invId;//  '邀请表主键ID',
	private String to_phone;// '被邀请人电话',
	private Date inviteTime;//'邀请时间',
	private int inviteState;//'邀请状态（0：已发送，1：已注册）',
	private UserInfo user;//  '用户ID（外键）',
	public String getInvId() {
		return invId;
	}
	public void setInvId(String invId) {
		this.invId = invId;
	}
	public String getTo_phone() {
		return to_phone;
	}
	public void setTo_phone(String toPhone) {
		to_phone = toPhone;
	}
	public Date getInviteTime() {
		return inviteTime;
	}
	public void setInviteTime(Date inviteTime) {
		this.inviteTime = inviteTime;
	}
	public int getInviteState() {
		return inviteState;
	}
	public void setInviteState(int inviteState) {
		this.inviteState = inviteState;
	}
	
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
}
