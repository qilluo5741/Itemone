package com.sharebo.entity;
import java.io.Serializable;
public class TaskInfo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  jobName;//任务名
	private String orderNo;
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public TaskInfo() {
	}

	public TaskInfo(String jobName, String orderNo) {
		this.jobName = jobName;
		this.orderNo = orderNo;
	}

	
	
	
}
