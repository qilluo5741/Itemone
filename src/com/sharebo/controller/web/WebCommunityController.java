package com.sharebo.controller.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Community;
import com.sharebo.service.CommunityService;
import com.sharebo.util.Pager;

@Controller
@RequestMapping("web/community")
public class WebCommunityController {
	@Autowired
	private CommunityService service;
	
	/**
	 * 分页查询
	 * @param mp
	 * @param
	 * @return
	 */
	@RequestMapping("community")
	public  String getCommunity(ModelMap mp,Integer pageIndex){
		Pager<Community> pager=new Pager<Community>();
		pager.setPageIndex((pageIndex==null?1:pageIndex));
		pager.setPageSize(10);
		Map<String, Object> map=new HashMap<String, Object>();
		//设置开始
		int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pager.getPageSize());
		//查询集合
		pager.setList(service.getCommunityByPager(map));
		//查询总数
		pager.setTotalRecords(service.getCommunity());
		pager.setTotalPages();//设置总页数
		mp.addAttribute("pager", pager);
		return "permission/CommunityManager";
	}
	/**
	 * 审核小区
	 */
	@RequestMapping("updatecommunityId")
	public @ResponseBody String getCommunity(ModelMap mp,String communityId){
		try {
			Community community=new Community();
			community.setCommunityId(communityId);
			community.setIsaudit(1);
			if(service.updateCommunity(community)>0){
				return "0";
			}else{
				return "1";
			}
		} catch (Exception e) {
			return "2";
		}
	}
	@RequestMapping("updatecommunityIds")
	public @ResponseBody String getCommunitys(ModelMap mp,String communityId){
		try {
			Community community=new Community();
			community.setCommunityId(communityId);
			community.setIsaudit(2);
			if(service.updateCommunity(community)>0){
				return "0";
			}else{
				return "1";
			}
		} catch (Exception e) {
			return "2";
		}
	}
	/**
	 * 模糊查询小区分页
	 */
	@RequestMapping("communityvague")
	public  String getCommunityvague(ModelMap mp,Integer pageIndex,String key){
		if(key!=null){
			Pager<Community> pager=new Pager<Community>();
			pager.setPageIndex((pageIndex==null?1:pageIndex));
			pager.setPageSize(9);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pager.getPageSize());
			map.put("communityname",key);
			//查询集合
			pager.setList(service.getCommunityByvaguePager(map));
			//查询总数
			pager.setTotalRecords(service.getCommunityvaguecount(key));
			pager.setTotalPages();//设置总页数
			mp.addAttribute("pager", pager);
			mp.addAttribute("key", key);
			return "permission/CommunityvagueManager";
		}else{
			Pager<Community> pager=new Pager<Community>();
			pager.setPageIndex((pageIndex==null?1:pageIndex));
			pager.setPageSize(9);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pager.getPageSize());
			//查询集合
			pager.setList(service.getCommunityByvaguePagers(map));
			//查询总数
			pager.setTotalRecords(service.getCommunityvaguecounts());
			pager.setTotalPages();//设置总页数
			mp.addAttribute("pager", pager);
			return "permission/CommunityvagueManager";
		}
	}
	/**
	 * 添加小区
	 */
	@RequestMapping("addCommunity")
	public @ResponseBody String addCommunity(ModelMap map,String community_name,String community_address,String administrative){
		try {
			int i=service.valCommunityExists(community_address);
			if(i>0){
				return "0";
			}
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = dateFormat.format(now);//得到String类型的当前时间*/	
			Community community=new Community();
			community.setCommunity_name(community_name);
			community.setCommunity_address(community_address);
			community.setAdministrative(administrative);
			community.setAddtime(addtime);
			community.setIsaudit(0);
			int j=service.addCommunity(community);
			if(j>0){
				return "1";
			}
		} catch (Exception e) {
			System.out.println("Oh, I was wrong!");
		}
		return "2";
	}
}

