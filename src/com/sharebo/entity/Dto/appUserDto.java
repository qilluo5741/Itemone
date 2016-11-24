package com.sharebo.entity.Dto;

import java.io.Serializable;


public class appUserDto implements  Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String userName;//'用户姓名',
		private String mobile  ;//'手机号码',
		private int sex     ;//'性别(0:未填写， 1：男    2  女 )',
		private String headportrait ;//'头像路径',
		private String invitecode   ;//'邀请码',
		private int is_guard     ;// '是否为保安（0：不是，1：是保安）',
		private String date_created ;//  '注册时间，创建时间',
		private String date_updated ;//  '最后更新时间（手机号）',
		private String payname      ;//'支付宝姓名',
		private String payno        ;//'支付宝号码',
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
		public int getIs_guard() {
			return is_guard;
		}
		public void setIs_guard(int isGuard) {
			is_guard = isGuard;
		}
		public String getDate_created() {
			return date_created;
		}
		public void setDate_created(String dateCreated) {
			date_created = dateCreated;
		}
		public String getDate_updated() {
			return date_updated;
		}
		public void setDate_updated(String dateUpdated) {
			date_updated = dateUpdated;
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
}
