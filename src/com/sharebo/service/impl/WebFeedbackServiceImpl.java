package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sharebo.entity.Dto.FeedbackDto;
import com.sharebo.mapper.WebFeedbackMapper;
import com.sharebo.service.WebFeedbackService;

@Service
public class WebFeedbackServiceImpl implements WebFeedbackService {
	@Autowired
	private WebFeedbackMapper mapper;
	/**
	 * 查询分页
	 */
	public List<FeedbackDto> getFeedbackPager(Map<String, Object> map) {
		return mapper.getFeedbackPager(map);
	}
	/**
	 * 查询总数
	 */
	public int getFeedbackcount() {
		return mapper.getFeedbackcount();
	}
}
