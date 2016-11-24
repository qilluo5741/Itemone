package com.sharebo.entity.Dto;

import java.io.Serializable;

public class FeedbackDto implements  Serializable{
	private static final long serialVersionUID = 1L;
	private String feedback_content;
	private String feedback_img;
	private String feedback_time;
	private String userName;
	private String mobile;
	private String age;
	private String sex;
	public String getFeedback_content() {
		return feedback_content;
	}
	public void setFeedback_content(String feedbackContent) {
		feedback_content = feedbackContent;
	}
	public String getFeedback_img() {
		return feedback_img;
	}
	public void setFeedback_img(String feedbackImg) {
		feedback_img = feedbackImg;
	}
	public String getFeedback_time() {
		return feedback_time;
	}
	public void setFeedback_time(String feedbackTime) {
		feedback_time = feedbackTime;
	}
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
