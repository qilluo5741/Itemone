package com.sharebo.interceptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 拦截一系列请求。
 * @author niewei
 *
 */
public class AuthInterceptor implements HandlerInterceptor{
	@SuppressWarnings("unused")
	@Value("#{configProperties['developers_token']}")
	private String token;//token获取签名使用_开发者使用
	/**
	 * 处理完成3
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse res, Object obj, Exception e)
			throws Exception {
	}
	/**
	 * 处理完成后
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse res,
			Object obj, ModelAndView model) throws Exception {
	}
	/**
	 * 请求拦截处理
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 //判断方法，是否执行阻止访问
		////////////////////////////1.  获取请求的方式///////////////////////////////////
		if(!request.getMethod().equals("GET")){
			//得到输出对象
			PrintWriter out= response.getWriter();
			out.print(JSONObject.fromObject(new ResultCode(00001,"请求方式只能为POST")));
			out.flush();
			out.close();
			return false;
		}
		
		
		////////////////////////////2.获取请求的url中的时间戳//////////////////////////
		String url=request.getRequestURI();
		String [] urls=url.split("/");
		String timestamp=urls[2];
		int ctime=getIntervalDays(timestamp);
		if(ctime>0||ctime<=-1){
			//得到输出对象z
			PrintWriter out= response.getWriter();
			out.print(JSONObject.fromObject(new ResultCode(00002,"时间不符合！")));
			out.flush();
			out.close();
			return false;
		}
		
		////////////////////////得到签名///////////////////////////////////
		
		 * 加密
		 *  
		 
		//获取加密后的token
		String token=request.getParameter("token");
		//解密
		BASE64Decoder decoder_2 = new BASE64Decoder();
		BASE64Decoder decoder_1 = new BASE64Decoder();
		String decoder=new String(decoder_1.decodeBuffer(new String(decoder_2.decodeBuffer(token.toString()))));
		if(!this.token.equals(decoder)){
			//得到输出对象
			PrintWriter out= response.getWriter();
			out.print(JSONObject.fromObject(new ResultCode(00003,"签名有误！")));
			out.flush();
			out.close();
			return false;
		}*/
		
		return true;
	}
	/**
	 * 计算一个时间 与当前时间两个时间差： 分钟
	 * @param date
	 * @return
	 */
	public static int getIntervalDays(String date) {
		//获取当前时间
		Date thisDate=new Date();
		//string 转换成date
		Date othertime;
		try {
			othertime = new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
		} catch (ParseException e) {
			return -1;
		}
		long between=( thisDate.getTime() - othertime.getTime())/1000;//除以1000是为了转换成秒
		long minute1=between%3600/60;//分钟
	    return (int) minute1;
	}
}
