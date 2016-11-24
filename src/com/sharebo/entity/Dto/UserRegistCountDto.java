package com.sharebo.entity.Dto;

import java.io.Serializable;

public class UserRegistCountDto implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dateupdate;//修改时间
	private int count;//总数
	public String getDateupdate() {
		return dateupdate;
	}
	public void setDateupdate(String dateupdate) {
		this.dateupdate = dateupdate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
