package com.sharebo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sharebo.entity.Collect;
import com.sharebo.entity.Parkingspace;
import com.sharebo.entity.ResultDto;
import com.sharebo.entity.UserInfo;
import com.sharebo.entity.Dto.InformationDto;
import com.sharebo.service.CollectService;
import com.sharebo.util.Pager;

/**
 * 标记车位
 * 
 * @author zhangke
 */
@RestController
@RequestMapping("{sign}/Collect")
public class CollectController {
	@Autowired
	private CollectService service;

	// 判断车位是否被收藏
	@RequestMapping("/add-collect")
	public ResultDto addCollect(String parkid, String userid) {
		if (parkid != null && userid != null) {
			Collect collect = new Collect();
			collect.setPark(new Parkingspace(parkid));
			collect.setUser(new UserInfo(userid));
			int i = service.Collectcount(collect);
			if (i > 0) {
				int j = service.delectCollect(collect);
				if (j > 0) {
					return new ResultDto(200, "取消收藏成功！");
				} else {
					return new ResultDto(2016, "取消收藏失败！");
				}
			} else {
				String collectid = UUID.randomUUID().toString().replaceAll("-","");
				collect.setCollectid(collectid);
				collect.setUser(new UserInfo(userid));
				collect.setPark(new Parkingspace(parkid));
				collect.setCollecttime(new Date());
				int k = service.addCollect(collect);
				if (k > 0) {
					return new ResultDto(200, "添加收藏成功！");
				} else {
					return new ResultDto(2017, "添加收藏失败！");
				}
			}
		} else {
			return new ResultDto(2009, "参数有异常");
		}
	}

	/**
	 * 标记车位-我的标记
	 * @param userid
	 * @return
	 */
	@RequestMapping("/get-collect")
	public ResultDto getCollect(Integer pageIndex, String userid) {
		if (pageIndex == 0 && userid == null) {
			return new ResultDto(2009, "请求参数有异常!");
		}
		Pager<InformationDto> pager = new Pager<InformationDto>();
		pager.setPageIndex((pageIndex==null?1:pageIndex));
		pager.setPageSize(10);
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置开始
		int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pager.getPageSize());
		map.put("userid", userid);
		// 查询集合
		pager.setList(service.getCollect(map));
		// 查询总数
		pager.setTotalRecords(service.pagerCountInformation());
		pager.setTotalPages();// 设置总页数
		if (pager != null) {
			return new ResultDto(200, pager);
		} else {
			return new ResultDto(2009, "返回值有异常");
		}
	}
}
