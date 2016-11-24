package com.sharebo.util;

/**
 * 状态返回
 * @author niewei
 *
 */
public  class ResultCode {
	//返回结果集
	private int status;
	private String msg;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ResultCode() {
	}
	/**
	 * 
	 * @param status 操作状态码  0代表成功！
	 * @param msg 状态码说明
	 */
	public ResultCode(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
}
