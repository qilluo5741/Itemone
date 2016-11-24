package com.sharebo.entity.Dto;

import java.io.Serializable;
import java.util.Date;

/***
 * 取消订单查询实体
 * @author niewei
 *
 */
public class OrderCancelDto  implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderid;
	private Date placeorde_time;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Date getPlaceorde_time() {
		return placeorde_time;
	}
	public void setPlaceorde_time(Date placeordeTime) {
		placeorde_time = placeordeTime;
	}
	
}
