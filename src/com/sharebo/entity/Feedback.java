package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户反馈表
 * @author Administrator
 */
public class Feedback  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String feedbackid;//'用户反馈主键',
	private String feedback_content;//'用户反馈内容',
	private String feedback_img;//'反馈图片（只支持一张图）',、
	private Date feedback_time;
	private UserInfo  user;//'反馈的用户'
	public String getFeedbackid() {
		return feedbackid;
	}
	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}
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
	public Date getFeedback_time() {
		return feedback_time;
	}
	public void setFeedback_time(Date feedbackTime) {
		feedback_time = feedbackTime;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
