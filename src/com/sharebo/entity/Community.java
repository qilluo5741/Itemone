package com.sharebo.entity;

import java.io.Serializable;


//小区表
public class Community  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String communityId;         // '小区表主键ID',
	private String community_name;      // '小区名字',
	private String community_address;   // '小区地址',
	private String administrative;      // '行政区',
	private String addtime;             // '录入时间',
	private int isaudit;             // '是否审核（0：未审核，1,：已审核）',
	private int gaodeId;             // '高德id',
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String communityName) {
		community_name = communityName;
	}
	public String getCommunity_address() {
		return community_address;
	}
	public void setCommunity_address(String communityAddress) {
		community_address = communityAddress;
	}
	public String getAdministrative() {
		return administrative;
	}
	public void setAdministrative(String administrative) {
		this.administrative = administrative;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public int getIsaudit() {
		return isaudit;
	}
	public void setIsaudit(int isaudit) {
		this.isaudit = isaudit;
	}
	public int getGaodeId() {
		return gaodeId;
	}
	public void setGaodeId(int gaodeId) {
		this.gaodeId = gaodeId;
	}
}
