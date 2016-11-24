package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.Feedback;
import com.sharebo.entity.ResultDto;
import com.sharebo.entity.UserInfo;
import com.sharebo.service.FeedbackService;
import com.sharebo.service.FileService;
import com.sharebo.util.security.Base64;
/**
 * 用户反馈
 * @author zhangke
 */
@RestController
@RequestMapping("{sign}/feedback")
public class FeedbackController{
	@Autowired
	private FileService fileService;
	@Autowired
	private FeedbackService service;
	/**
	 * 添加用户反馈
	 * @param feedback_content
	 * @param feedback_img
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/json-addfeed")
	public ResultDto insertfeedback(String  feedback_content,String feedback_img,String userid){
		if(feedback_content!=null || userid!=null){
			//String feedbackid=UUID.randomUUID().toString().replaceAll("-", "");;//创建uuid
		    if(feedback_img==null){//判断图片是否为空
		    	Feedback feed=new Feedback();//new一个实体
		    	//feed.setFeedbackid(feedbackid);//feedback主键
		    	feed.setFeedback_content(feedback_content);//用户的内容
		    	feed.setFeedback_time(new Date());
		    	feed.setUser(new UserInfo(userid));//用户的id
	    		int i=service.insertfeedback(feed);//插入数据
	    		if(i>0){//判断成功状态
	    			return new ResultDto(200,"意见反馈成功！");
	    		}else{
	    			return new ResultDto(2001,"意见反馈失败！");
	    		}
		    }else{
		    	Feedback feed=new Feedback();//new一个实体
		    	//feed.setFeedbackid(feedbackid);//feedback主键
		    	feed.setFeedback_content(feedback_content);//用户的内容
		    	feed.setFeedback_time(new Date());
		    	feed.setUser(new UserInfo(userid));//用户的id
			    byte[] by = Base64.decode(feedback_img);
			    ByteArrayInputStream bais = new ByteArrayInputStream(by);
			    String feedimg = this.fileService.uploadImage(bais);
			    feed.setFeedback_img(feedimg);
			    int i=service.insertfeedback(feed);
			    if(i>0){//判断成功状态
	    			return new ResultDto(200,"意见反馈成功！");
	    		}else{
	    			return new ResultDto(2001,"意见反馈失败！");
	    		}
		    }
		}else{
			return new ResultDto(2002,"请求参数为空！");
		}
	}
}
