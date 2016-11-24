package com.sharebo.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//订单表
public class OrderInfo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderid;             
	private String ordernum;//'订单号码（规则：年月日时分秒毫秒（13位）+随机数（5））',
	private Parkingspace park;//'车位ID（外键）',
	private Date placeorde_time;//'下订单时间（生成时间）',
	private Date supplierconfirm_time;//'供方确认时间',
	private Date payment_time;//'支付时间',
	private int payType;//'支付类型（0“余额支付，1：支付宝，2：微信支付）',
	private int order_state;//'订单状态（0：未支付，1：已支付，2：已完成，3.已拒绝',
	private String vehicleid;//'车牌号',
	private double money;//'订单金额',
	private UserInfo from_user;//'用户外键（需方）',
	private int chargeType;//'收费类型（0：按次收费 1：按小时收费）',
	private double typemoney;//单价
	
	public double getTypemoney() {
		return typemoney;
	}
	public void setTypemoney(double typemoney) {
		this.typemoney = typemoney;
	}
	private List<OrderTime> listordertime;//订单时间集合
	
	public List<OrderTime> getListordertime() {
		return listordertime;
	}
	public void setListordertime(List<OrderTime> listordertime) {
		this.listordertime = listordertime;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	
	public Parkingspace getPark() {
		return park;
	}
	public void setPark(Parkingspace park) {
		this.park = park;
	}
	public UserInfo getFrom_user() {
		return from_user;
	}
	public void setFrom_user(UserInfo fromUser) {
		from_user = fromUser;
	}
	
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int orderState) {
		order_state = orderState;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getChargeType() {
		return chargeType;
	}
	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}
	public Date getPlaceorde_time() {
		return placeorde_time;
	}
	public void setPlaceorde_time(Date placeordeTime) {
		placeorde_time = placeordeTime;
	}
	public Date getSupplierconfirm_time() {
		return supplierconfirm_time;
	}
	public void setSupplierconfirm_time(Date supplierconfirmTime) {
		supplierconfirm_time = supplierconfirmTime;
	}
	public Date getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(Date paymentTime) {
		payment_time = paymentTime;
	}
	public OrderInfo() {
	}
	public OrderInfo(String orderid) {
		this.orderid = orderid;
	}
}
