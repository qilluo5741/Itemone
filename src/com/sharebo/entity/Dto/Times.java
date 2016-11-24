package com.sharebo.entity.Dto;

import java.io.Serializable;
import java.util.Date;

public class Times implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date thisDate;
	private String begin_time;
	private String  end_time;
	
	public Times() {
	}
	public Times(String beginTime, String endTime) {
		super();
		begin_time = beginTime;
		end_time = endTime;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String beginTime) {
		begin_time = beginTime;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String endTime) {
		end_time = endTime;
	}
	public Date getThisDate() {
		return thisDate;
	}
	public void setThisDate(Date thisDate) {
		this.thisDate = thisDate;
	}
	
}
