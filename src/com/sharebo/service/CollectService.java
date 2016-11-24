package com.sharebo.service;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Collect;
import com.sharebo.entity.Dto.InformationDto;

public interface CollectService {
	/**
	 * 标记车位
	 * @param collect
	 * @return
	 */
	public int addCollect(Collect collect);
	/**
	 * 收藏表中查询
	 * @param collect
	 * @return
	 */
	public int Collectcount(Collect collect);
	/**
	 * 取消车位
	 * @param collect
	 * @return
	 */
	public int delectCollect(Collect collect);
	/**
	 * 标记车位-我的标记查询
	 * @param userid
	 * @return
	 */
	public List<InformationDto> getCollect(Map<String,Object> map);
	public int pagerCountInformation();//总数
	/**
	 * 查询
	 * @param userid
	 * @return
	 */
	public String SelectCollectParkid(String userid);
}
