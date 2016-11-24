package com.sharebo.service;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Dto.FeedbackDto;

public interface WebFeedbackService {
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<FeedbackDto> getFeedbackPager(Map<String,Object> map);
	/**
	 * 查询总数
	 * @return
	 */
	public int getFeedbackcount();
}
