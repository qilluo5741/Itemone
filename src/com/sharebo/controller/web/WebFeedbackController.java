package com.sharebo.controller.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sharebo.entity.Dto.FeedbackDto;
import com.sharebo.service.WebFeedbackService;
import com.sharebo.util.Pager;
/****
 * 用户信息
 */
@Controller
@RequestMapping("web/feed")
public class WebFeedbackController {
	@Autowired
	private WebFeedbackService service;
	
	/**
	 * 查询用户反馈内容
	 * @param mp
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("getFeedback")
	public  String users(ModelMap mp,Integer pageIndex){
		Pager<FeedbackDto> pager=new Pager<FeedbackDto>();
		pager.setPageIndex((pageIndex==null?1:pageIndex));
		pager.setPageSize(8);
		Map<String, Object> map=new HashMap<String, Object>();
		//设置开始
		int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pager.getPageSize());
		//查询集合
		pager.setList(service.getFeedbackPager(map));
		//查询总数
		pager.setTotalRecords(service.getFeedbackcount());
		mp.addAttribute("pager", pager);
		return "permission/FeedbackManager";
	}
}
