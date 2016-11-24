package com.sharebo.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sharebo.entity.WebUserInfo;
import com.sharebo.service.WebUserInterceptorService;

public class WebInterceptor implements HandlerInterceptor{
	@Autowired
	private WebUserInterceptorService service;
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		HttpSession session=request.getSession();
		WebUserInfo user=(WebUserInfo) session.getAttribute("user");
		//验证是否登录
		if(user==null){
			//未登录，跳转登录页面
			response.sendRedirect("/sharebo/login.jsp");
			return false;
		}
		Map<String,String> map=new HashMap<String,String>();
		map.put("userId",user.getUserId());
		map.put("href",request.getRequestURI());
		//验证当前用户是否有权限访问该网页
		if(!service.valMenuPermissions(map)){
			//没有权限访问
			//判断当前路径是否在数据库中存在   存在：false 不存在 true 可以执行
			if(service.valHrefIsExist(request.getRequestURI())>0){
				return false;
			}
		}
		return true;
	}
}
