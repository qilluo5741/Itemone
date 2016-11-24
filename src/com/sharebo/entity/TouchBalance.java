package com.sharebo.entity;
import java.util.Date;
/**
 * 余额明细表
 * @author Administrator
 */
public class TouchBalance implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tbid;//余额明细表Id
	private double money;//'金额（正数或者负数；支出或者收入）',
	private Date operation_time;//'时间',
	private int tradtype;//'交易类型（0：支付宝，1：微信，2余额支付，3，代理收益，4.，自动退款）',
	private Purse purse;//'钱包外键',
	public String getTbid() {
		return tbid;
	}
	public void setTbid(String tbid) {
		this.tbid = tbid;
	}

	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getOperation_time() {
		return operation_time;
	}
	public void setOperation_time(Date operationTime) {
		operation_time = operationTime;
	}
	
	public int getTradtype() {
		return tradtype;
	}
	public void setTradtype(int tradtype) {
		this.tradtype = tradtype;
	}
	public Purse getPurse() {
		return purse;
	}
	public void setPurse(Purse purse) {
		this.purse = purse;
	}
	
}
