package com.sharebo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.Message;
import com.sharebo.entity.ResultDto;
import com.sharebo.service.MessageService;
import com.sharebo.util.Pager;

/**
 * @author zhangke
 */
@RestController
@RequestMapping("{sign}/Message")
public class MessageController {
	@Autowired
	public MessageService service;
	/**
	 * 分页查询供方消息记录
	 * @param pageIndex
	 * @param pageSize
	 * @param userid
	 * @return
	 */
	@RequestMapping("/pagesupplierMessage")
	public ResultDto pagesupplierMessage(int pageIndex,int pageSize,String userid){
		if(pageIndex==0 && pageSize==0 && userid==null){
			return new ResultDto(2008,"参数不合法！");
		}else{
			Pager<Message> pager=new Pager<Message>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			//查询集合
			pager.setList(service.pagersupplierMessagebyList(map));
			//查询总数
			pager.setTotalRecords(service.pagersupplierMessagebyCount());
			pager.setTotalPages();//设置总页数
			if(pager!=null){
				return new ResultDto(200,pager);
			}
			else{
				return new  ResultDto(2009,"返回值有异常");
			}
		}
	}
	/**
	 * 分页查询需方消息记录
	 * @param pageIndex
	 * @param pageSize
	 * @param userid
	 * @return
	 */
	@RequestMapping("/pagebuyerMessage")
	public ResultDto pagebuyerMessage(int pageIndex,int pageSize,String userid){
		if(pageIndex==0 && pageSize==0 && userid==null){
			return new ResultDto(2008,"参数不合法！");
		}else{
			Pager<Message> pager=new Pager<Message>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			//查询集合
			pager.setList(service.pagerbuyerMessagebyList(map));
			//查询总数
			pager.setTotalRecords(service.pagerbuyerMessagebyCount());
			pager.setTotalPages();//设置总页数
			if(pager!=null){
				return new ResultDto(200,pager);
			}
			else{
				return new  ResultDto(2009,"返回值有异常");
			}
		}
	}
}
