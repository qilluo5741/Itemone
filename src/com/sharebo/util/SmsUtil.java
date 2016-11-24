package com.sharebo.util;

import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sharebo.config.SmsConfig;
/***
 * 短信工具包
 * @author niewei
 *
 */
public  class SmsUtil {
	//消息配置
	private SmsConfig sms;
	//日志记录对象
	private Logger log=Log.getInstance();
	
	//发送验证码短信通知
	
	/**
	 * 验证码发送
	 * mobile 发送的手机号码
	 * authCode验证码
	 */
	@SuppressWarnings("static-access")
	public  void sendCode(String mobile, String authCode) {
		  try 
		  { 
			String result = IOUtils.toString(
	        new URL("http://sh2.ipyy.com/sms.aspx?action=send&sendTime=&userid=&account=" + 
	        this.sms.ACCOUNT + "&password=" + this.sms.PASSWORD + "&mobile=" + 
	        mobile + "&content=" + URLEncoder.encode(this.sms.TP1.replace("@", authCode), "utf-8"))
	        .openConnection().getInputStream(), "utf-8");
	       log.info("SMS Send Result [{"+result+"}] end");
	    } catch (Exception e)
	    {
	    //短信发送失败！异常
	      throw new RuntimeException("Sms Send Error Caused.", e);
	    }
	  }
	/***
	 * 邀请短信发送
	 * @param name 邀请人对被邀请人的称呼
	 * @param mobile 手机号码
	 * @param invitationCode 邀请码
	 */
	@SuppressWarnings("static-access")
	public void sendInvitation(String name,String mobile,String invitationCode) {
	try{ 
			String result = IOUtils.toString(
	        new URL("http://sh2.ipyy.com/sms.aspx?action=send&sendTime=&userid=&account=" + 
	        this.sms.ACCOUNT + "&password=" + this.sms.PASSWORD + "&mobile=" + 
	        mobile + "&content=" + URLEncoder.encode(this.sms.TP2.replace("@1", name).replace("@2",invitationCode), "utf-8"))
	        .openConnection().getInputStream(), "utf-8");
	       log.info("SMS Send Result [{"+result+"}] end");
	    } catch (Exception e){
	     //短信发送失败！异常
	      throw new RuntimeException("Sms Send Error Caused.", e);
	    }
	}
}
