package com.sharebo.service;

import com.sharebo.entity.Feedback;
/**
 * 用户反馈
 * @author Administrator
 */
public interface FeedbackService {
	/**
	 * 插入
	 * @return
	 */
	public int insertfeedback(Feedback feed);
}
