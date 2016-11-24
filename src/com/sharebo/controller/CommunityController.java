package com.sharebo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.Community;
import com.sharebo.entity.ResultDto;
import com.sharebo.service.CommunityService;
import com.sharebo.util.httpClient.HttpUtil;
/**
 * 添加小区
 * @author zhangke
 */
@RestController
@RequestMapping("{sign}/Community")
public class CommunityController {
	@Autowired
	private CommunityService service;
	/**
	 *判断车位是否被收藏
	 *{sign}/Community/add-communit.do?community_name=123456&community_address=111111
	 */
	@RequestMapping("/add-communit")
	public ResultDto addCommunity(String community_name,String community_address){
		try {
			if(community_name==null && community_address==null){
				return new ResultDto(2002,"请求参数为空！");
			}
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addtime = dateFormat.format(now);//得到String类型的当前时间*/	
			String communityId = UUID.randomUUID().toString().replaceAll("-","");
			Community community=new Community();
			community.setCommunityId(communityId);
			community.setCommunity_name(community_name);//停车场名字
			community.setCommunity_address(community_address);//停车场地址
			community.setAdministrative("");
			community.setAddtime(addtime);//录入时间
			community.setIsaudit(0);//是否审核（0：未审核，1,：已审核）
			String key="14607db53fff73bc7ed6e611a6246fc1";
			String tableid="5704b9ee305a2a034b09bbfc";
			String httpUrl="http://yuntuapi.amap.com/datamanage/data/create";
			String httpArg="key="+key+"&tableid="+tableid+"&loctype=2&data={\"_name\":\""+community_name+"\",\"_address\":\""+community_address+"\",\"communityId\":\""+communityId+"\"}";
			System.out.println(community_name);
			String res=HttpUtil.request_post(httpUrl, httpArg);
			System.out.println(res);
			int id=JSONObject.fromObject(res).getInt("_id");
			community.setGaodeId(id);
			int i=service.addCommunity(community);
			if(i>0){
				return new ResultDto(200,"添加小区成功！");
			}else{
				return new ResultDto(2018,"添加小区失败！");
			}
		} catch (Exception e) {
			return new ResultDto(2019,"该小区已经存在！");
		}
	}
}
