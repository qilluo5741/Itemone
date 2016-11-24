package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;
/**
 *提现记录表 
 * @author niewei
 *
 */
public class WithdrawalRecord implements Serializable{
	private static final long serialVersionUID = 1L;
		private String wrid;//'提现ID',
		private double withdrawal_money;//'提现金额',
		private Date withdrawal_date;//'提现时间',
		private UserInfo user;//用户外键
		private int withdrawal_state;//'提现状态（0：申请中，1：已提现 2:其他）',
		private Date successfultime;//'提现审核时间。',
		private int is_delete;//'是否删除（0：未删除  1：已删除）',
		private String payName;//支付宝姓名
		private String payNo;//支付宝账号
		public String getWrid() {
			return wrid;
		}
		public void setWrid(String wrid) {
			this.wrid = wrid;
		}
		public double getWithdrawal_money() {
			return withdrawal_money;
		}
		public void setWithdrawal_money(double withdrawalMoney) {
			withdrawal_money = withdrawalMoney;
		}
		public Date getWithdrawal_date() {
			return withdrawal_date;
		}
		public void setWithdrawal_date(Date withdrawalDate) {
			withdrawal_date = withdrawalDate;
		}
		public UserInfo getUser() {
			return user;
		}
		public void setUser(UserInfo user) {
			this.user = user;
		}
		public int getWithdrawal_state() {
			return withdrawal_state;
		}
		public void setWithdrawal_state(int withdrawalState) {
			withdrawal_state = withdrawalState;
		}
		public Date getSuccessfultime() {
			return successfultime;
		}
		public void setSuccessfultime(Date successfultime) {
			this.successfultime = successfultime;
		}
		public int getIs_delete() {
			return is_delete;
		}
		public void setIs_delete(int isDelete) {
			is_delete = isDelete;
		}
		public String getPayName() {
			return payName;
		}
		public void setPayName(String payName) {
			this.payName = payName;
		}
		public String getPayNo() {
			return payNo;
		}
		public void setPayNo(String payNo) {
			this.payNo = payNo;
		}
		
}
