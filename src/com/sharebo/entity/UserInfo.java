package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userid;//'用户主键',
    private String userName;//'用户姓名',
    private String mobile;//'手机号码',
    private String password;//'登录密码',
    private int sex;//'性别(0:未填写， 1：男    2  女 )',
    private int age;//'年龄',
    private String headportrait ;//'头像路径',
    private String invitecode;//'邀请码',
    private String regid;//'消息推送，Id点对点',
    private String token;//'安全验证，为了防止多方登录',
    private int is_guard;// '是否为保安（0：不是，1：是保安）',
    private String id_card;//'身份证号码（保安）',
    private Date date_created;//  '注册时间，创建时间',
    private Date date_updated;//  '最后更新时间（手机号）',
    private String smscode;//'短信验证码',
    private String terminal;// '设备id（拼接）',
    private String bank_open_name;//'银行卡开户姓名',
    private String bank_open_no;//'银行卡卡号',
    private String bank_type;//'银行名字：（比如 招商银行）',
    private String payname;//'支付宝姓名',
    private String payno;//'支付宝号码',
    private String communityName;//'停车场/小区名字',
    private String community_address;//'小区地址（认证保安/代理）',
    private String entrance;//'小区入口（认证保安）'
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getHeadportrait() {
		return headportrait;
	}
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
	public String getInvitecode() {
		return invitecode;
	}
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getIs_guard() {
		return is_guard;
	}
	public void setIs_guard(int isGuard) {
		is_guard = isGuard;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String idCard) {
		id_card = idCard;
	}

	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date dateCreated) {
		date_created = dateCreated;
	}
	public Date getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(Date dateUpdated) {
		date_updated = dateUpdated;
	}
	public String getSmscode() {
		return smscode;
	}
	public void setSmscode(String smscode) {
		this.smscode = smscode;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	 
	public String getBank_open_no() {
		return bank_open_no;
	}
	public void setBank_open_no(String bankOpenNo) {
		bank_open_no = bankOpenNo;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bankType) {
		bank_type = bankType;
	}
	public String getPayname() {
		return payname;
	}
	public void setPayname(String payname) {
		this.payname = payname;
	}
	public String getPayno() {
		return payno;
	}
	public void setPayno(String payno) {
		this.payno = payno;
	}
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
	public String getBank_open_name() {
		return bank_open_name;
	}
	public void setBank_open_name(String bankOpenName) {
		bank_open_name = bankOpenName;
	}
	public UserInfo(String userid) {
		this.userid = userid;
	}
	public UserInfo() {
	}
}
