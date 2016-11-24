package com.sharebo.controller.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.MenuType;
import com.sharebo.entity.WebUserInfo;
import com.sharebo.entity.Dto.UserRegistCountDto;
import com.sharebo.entity.Dto.appUserDto;
import com.sharebo.service.WebUserService;
import com.sharebo.util.Pager;
import com.sharebo.util.push.StringUtils;

/**
 * web后台 用户操作
 * 
 * @author niewei
 */
@Controller
@RequestMapping("web/user")
public class WebUserController {
	@Autowired
	private WebUserService service;
	@Autowired
	private HttpServletRequest request;

	// 退出
	@RequestMapping("exit")
	public String exit() {
		// 得到session
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "login";
	}

	// 验证用户名字是否存在
	@RequestMapping("valUserAccountIsExixts")
	public @ResponseBody
	String valUserAccountIsExixts(String userAccount) {
		if (service.valUserAccountIsExixts(userAccount) > 0) {
			return "true";
		}
		return "false";
	}

	// 注册
	@RequestMapping("regist")
	public @ResponseBody
	String regist(WebUserInfo user) {
		user.setUserId(UUID.randomUUID().toString());
		user.setUserPwd(StringUtils.toMD5(user.getUserPwd()));
		if (service.regist(user) > 0) {
			return "true";
		}
		return "false";
	}

	// 用户登录
	@RequestMapping("login")
	public @ResponseBody
	String login(WebUserInfo user) {
		user.setUserPwd(StringUtils.toMD5(user.getUserPwd()));
		// 登录查询
		WebUserInfo w_user = service.login(user);
		// 判断结果是否为null null为登录失败！
		if (w_user == null) {
			return "0";
		} else {
			if (w_user.getUserStatus() == 1) {
				// 得到session
				HttpSession session = request.getSession();
				// 存入用户
				session.setAttribute("user", w_user);
				return "1";
			} else if (w_user.getUserStatus() == 0) {
				return "3";// 审核中
			} else {
				return "2";
			}
		}

	}

	// 获取菜单
	@RequestMapping("menus")
	public String getMenus(ModelMap map) {
		// 得到session
		HttpSession session = request.getSession();
		WebUserInfo w_user = (WebUserInfo) session.getAttribute("user");
		if (w_user != null) {
			// 获取userId查询菜单
			List<MenuType> list = service.getMenus(w_user.getUserId());// 查询菜单
			map.addAttribute("menuTypes", list);
			map.addAttribute("menuValidation", true);// 提供前段验证是否执行查询
			return "index";// 跳转首页
		}
		// 返回登录
		return "login";
	}

	// 用户注册量报表查询
	@RequestMapping("getvur")
	public String getViewUserRegist(ModelMap mv, Integer year, Integer month) {
		// 默认当前时间
		if (year == null || month == null) {
			Calendar calendarCountDays = new GregorianCalendar();
			calendarCountDays.setTime(new Date());
			year = calendarCountDays.get(Calendar.YEAR);
			month = calendarCountDays.get(Calendar.MONTH) + 1;
		}
		// 查询
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", year + "-" + (month < 10 ? "0" + month : month));
		map.put("end", year + "-" + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)));

		// 得到当前月的天数
		Calendar cd = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM");
		try {
			cd.setTime(s.parse(map.get("begin")));
		} catch (ParseException e) {
			System.out.println("时间转换失败！");
		}
		int daysCount = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
		List<String> daysList = new ArrayList<String>();// ---------一月的日期
		for (int i = 1; i <= daysCount; i++) {
			map.put(map.get("begin") + "-" + (i < 10 ? "0" + i : i), 0 + "");
			daysList.add("'" + map.get("begin") + "-" + i + "'");
		}
		List<UserRegistCountDto> list = service.userRegistCount(map);
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getDateupdate(), list.get(i).getCount() + "");
		}
		List<String> daysListDate = new ArrayList<String>();// ---------一月的日期
		for (int i = 1; i <= daysCount; i++) {
			daysListDate.add(map.get(map.get("begin") + "-"
					+ (i < 10 ? "0" + i : i)));
		}
		// 生成日期List
		// 生成对应数据
		mv.addAttribute("daysList", daysList);// 添加日期
		mv.addAttribute("daysListDate", daysListDate);// 添加数据
		mv.addAttribute("year", year);// 添加数据
		mv.addAttribute("month", month);// 添加数据
		return "userRegistCount";
	}
	/**
	 * 用户管理
	 * @param mp
	 * @param pageIndex
	 * @param key
	 * @return
	 */
	@RequestMapping("getUserInfonot")
	public  String getUserInfoByisnotPager(ModelMap mp,Integer pageIndex,String key,String creabeegin,String creaend){
			Pager<appUserDto> pager=new Pager<appUserDto>();
			pager.setPageIndex((pageIndex==null?1:pageIndex));
			pager.setPageSize(9);
			Map<String, Object> map=new HashMap<String, Object>();
			System.out.println("开始时间："+creabeegin);
			System.out.println("结束时间:"+creaend);
			System.out.println("查询号码："+key);
			/*String date_creabeegin="2016-04-19";
			String date_creaend="2016-12-31";*/
			//设置开始
			int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pager.getPageSize());
			map.put("mobile",key);
			map.put("date_creabeegin",creabeegin);
			map.put("date_creaend",creaend);
			if(key==null || key.equals("")){
				//查询集合
				pager.setList(service.getUserInfoByisPager(map));
				//查询总数
				pager.setTotalRecords(service.getUserInfocount());
				pager.setTotalPages();//设置总页数
				mp.addAttribute("pager", pager);
				return "permission/UserByisnotManager";
			}else{
				//查询集合
				pager.setList(service.getUserInfoByisnotPager(map));
				//查询总数
				pager.setTotalRecords(service.getUserInfonotcount(key));
				pager.setTotalPages();//设置总页数
				mp.addAttribute("pager", pager);
				return "permission/UserByisnotManager";
			}
	}
}
