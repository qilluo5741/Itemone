package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sharebo.entity.Feedback;
import com.sharebo.mapper.FeedbackMapper;
import com.sharebo.service.FeedbackService;
/**
 * 用户反馈
 * @author Administrator
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackMapper mapper;
	/**
	 * 插入
	 * @return
	 */
	public int insertfeedback(Feedback feed) {
		return mapper.insertfeedback(feed);
	}
}
