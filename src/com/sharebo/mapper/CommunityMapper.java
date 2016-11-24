package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Community;

/**
 * 小区表
 * @author Administrator
 *
 */
public interface CommunityMapper {
	/**
	 * 添加小区
	 * @param collect
	 * @return
	 */
	public int addCommunity(Community community);
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<Community> getCommunityByPager(Map<String,Object> map);
	/**
	 * 查询小区总数
	 * @return
	 */
	public int getCommunity();
	
	/**
	 * 审核小区
	 */
	public int updateCommunity(Community community);
	/**
	 * 模糊查询分页
	 */
	public List<Community> getCommunityByvaguePager(Map<String,Object> map);
	/**
	 * 查询小区总数
	 * @return
	 */
	public int getCommunityvaguecount(String communityname);
	/**
	 * 查询分页
	 */
	public List<Community> getCommunityByvaguePagers(Map<String,Object> map);
	/**
	 * 查询小区总数
	 * @return
	 */
	public int getCommunityvaguecounts();
	/**
	 * 验证小区名字是否已经存在
	 * @param community_name
	 * @param community_address
	 * @return
	 */
	public int  valCommunityExists(String community_address);
}
