package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Collect;
import com.sharebo.entity.Dto.InformationDto;
import com.sharebo.mapper.CollectMapper;
import com.sharebo.service.CollectService;
@Service
public class CollectServiceImpl implements CollectService {
	@Autowired
	private CollectMapper mapper;
	/**
	 * 查询是否收藏
	 */
	public int Collectcount(Collect collect) {
		return mapper.Collectcount(collect);
	}
	/**
	 * 添加收藏
	 */
	public int addCollect(Collect collect) {
		return mapper.addCollect(collect);
	}
	/**
	 * 取消收藏
	 */
	public int delectCollect(Collect collect) {
		return mapper.delectCollect(collect);
	}
	/**
	 * 标记车位-我的标记查询
	 * @param userid
	 * @return
	 */
	public List<InformationDto> getCollect(Map<String, Object> map) {
		return mapper.getCollect(map);
	}
	public int pagerCountInformation() {
		return mapper.pagerCountInformation();
	}
	public String SelectCollectParkid(String userid) {
		return mapper.SelectCollectParkid(userid);
	}
}
