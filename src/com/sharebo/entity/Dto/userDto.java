package com.sharebo.entity.Dto;

import java.io.Serializable;

public class userDto implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private String communityName;//'停车场/小区名字',
	 private String community_address ;//'小区地址（认证保安/代理）',
	 private String entrance  ;//'小区入口（认证保安）'
	 private String id_card      ;//'身份证号码（保安）', 
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getCommunity_address() {
		return community_address;
	}
	public void setCommunity_address(String communityAddress) {
		community_address = communityAddress;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String idCard) {
		id_card = idCard;
	}
	public userDto() {
	}
	public userDto(String communityName, String communityAddress,
			String entrance, String idCard) {
		this.communityName = communityName;
		community_address = communityAddress;
		this.entrance = entrance;
		id_card = idCard;
	}
	 
}
