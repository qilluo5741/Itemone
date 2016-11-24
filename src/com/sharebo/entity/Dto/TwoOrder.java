package com.sharebo.entity.Dto;

import java.io.Serializable;

public class TwoOrder implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String headportrait;
	private String community_name;
	private String orderid;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadportrait() {
		return headportrait;
	}
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String communityName) {
		community_name = communityName;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public TwoOrder(String userName, String headportrait, String communityName,
			String orderid) {
		this.userName = userName;
		this.headportrait = headportrait;
		community_name = communityName;
		this.orderid = orderid;
	}
	public TwoOrder() {
	}
	
}
