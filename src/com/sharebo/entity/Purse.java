package com.sharebo.entity;
import java.io.Serializable;
/***
 * 钱包表
 * @author niewei
 *
 */
public class Purse  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  purseid;//钱包表主键ID',
	private double   balance;// '余额',
	private double   blocked_balances  ;// '冻结余额',
	private UserInfo user;//
	public String getPurseid() {
		return purseid;
	}
	public void setPurseid(String purseid) {
		this.purseid = purseid;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getBlocked_balances() {
		return blocked_balances;
	}
	public void setBlocked_balances(double blockedBalances) {
		blocked_balances = blockedBalances;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public Purse(String purseid) {
		this.purseid = purseid;
	}
	public Purse() {
	}
}
