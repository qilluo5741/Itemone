package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Community;
import com.sharebo.mapper.CommunityMapper;
import com.sharebo.service.CommunityService;
@Service
public class CommunityServiceImpl implements CommunityService {
	@Autowired
	private CommunityMapper mapper;
	//添加小区
	public int addCommunity(Community community) {
		return mapper.addCommunity(community);
	}
	/**
	 * 分页查询
	 */
	public List<Community> getCommunityByPager(Map<String, Object> map) {
		return mapper.getCommunityByPager(map);
	}
	/**
	 * 查询小区总数
	 * @return
	 */
	public int getCommunity() {
		return mapper.getCommunity();
	}
	/**
	 * 审核小区
	 */
	public int updateCommunity(Community community) {
		return mapper.updateCommunity(community);
	}
	
	/**
	 * 模糊查询分页
	 */
	
	public List<Community> getCommunityByvaguePager(Map<String, Object> map) {
		return mapper.getCommunityByvaguePager(map);
	}
	/**
	 * 查询小区总数
	 * @return
	 */
	public int getCommunityvaguecount(String communityname) {
		return mapper.getCommunityvaguecount(communityname);
	}
	public List<Community> getCommunityByvaguePagers(Map<String, Object> map) {
		return mapper.getCommunityByvaguePagers(map);
	}
	public int getCommunityvaguecounts() {
		return mapper.getCommunityvaguecounts();
	}
	/**
	 * 验证小区名字是否已经存在
	 * @param community_name
	 * @param community_address
	 * @return
	 */
	public int valCommunityExists(String community_address) {
		return mapper.valCommunityExists(community_address);
	}
}
