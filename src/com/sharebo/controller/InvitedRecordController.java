package com.sharebo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sharebo.entity.InvitedRecord;
import com.sharebo.entity.ResultDto;
import com.sharebo.entity.UserInfo;
import com.sharebo.service.InvitedRecordService;
import com.sharebo.util.Pager;
/**
 * 记录好友
 * @author zhangke
 */
@RestController
@RequestMapping("{sign}/Invited")
public class InvitedRecordController {
	@Autowired
	private InvitedRecordService service;
	
	//添加邀请记录同事验证邀请该用户是否存在
	@RequestMapping("/json-Invited")
	public ResultDto InvitedRecord(String  vehicleno,String mobile,String userid){
		try {
			if(vehicleno!=null && mobile!=null && userid!=null){
				Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");//正则表达式验证手机号码是否合法
			    Matcher m = p.matcher(mobile);
			    if(!m.matches()){//手机号验证不成功
			    	return new ResultDto(2003,"手机号码输入不合法！");
			    }else{
			    	int i=service.InvitedRecordPurse(mobile);//查询好友是否被注册
			    	if(i>0){
			    		return new ResultDto(2004,"该好友已经被注册！");
			    	}else{
			    		int j=service.InvitedRecord(mobile);//查询好友是否被邀请
			    		if(j>0){
			    			return new ResultDto(2005,"该好友已经被邀请！");
			    		}else{
			    			Date now = new Date();//创建时间
			    			InvitedRecord In=new InvitedRecord();
			    			In.setTo_phone(mobile);//邀请人电话
			    			In.setInviteTime(now);//邀请时间
			    			In.setInviteState(0);//邀请状态（0：已发送，1：已注册）
			    			In.setUser(new UserInfo(userid));//用户ID（外键）
			    			int k=service.insertInvited(In);//插入数据
			    			if(k>0){
			    				return new ResultDto(200,"邀请好友成功！");
			    			}else{
			    				return new ResultDto(2006,"邀请好友失败！");
			    			}
			    		}
			    	}
			    }
			}else{
				return new ResultDto(2002,"请求参数为空！");
			}
		} catch (Exception e) {
			return new ResultDto(2007,"邀请人请求参数不对！");
		}
	}
	
	//分页查询邀请记录
	@RequestMapping("/json-pageInvited")
	public ResultDto pageInvited(int pageIndex,int pageSize,String userid){
		if(pageIndex==0 && pageSize==0 && userid==null){
			return new ResultDto(2008,"参数不合法！");
		}
		Pager<InvitedRecord> pager=new Pager<InvitedRecord>();
		pager.setPageIndex(pageIndex);
		pager.setPageSize(pageSize);
		Map<String, Object> map=new HashMap<String, Object>();
		//设置开始
		int pageBegin=(pageIndex-1)*pageSize;
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pageSize);
		map.put("userid", userid);
		//查询集合
		pager.setList(service.pagerInvitedbyList(map));
		//查询总数
		pager.setTotalRecords(service.pagerInvitedbyCount());
		pager.setTotalPages();//设置总页数
		if(pager!=null){
			return new ResultDto(200,pager);
		}else{
			return new  ResultDto(2009,"返回值有异常");
		}
	}
}
