package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.MenuType;
import com.sharebo.entity.WebUserInfo;
import com.sharebo.entity.Dto.UserRegistCountDto;
import com.sharebo.entity.Dto.appUserDto;
import com.sharebo.mapper.WebUserMapper;
import com.sharebo.service.WebUserService;
@Service
public class WebUserServiceImpl implements WebUserService {
	@Autowired
	private WebUserMapper mapper;
	/**
	 * 用户登录
	 * @param user用户验证信息
	 * @return 返回用户信息
	 */
	public WebUserInfo login(WebUserInfo user) {
		return mapper.login(user);
	}
	/**
	 * 查询用户相应角色能访问的菜单
	 * @param userid 用户ID
	 * @return 菜单类型集合
	 */
	public List<MenuType> getMenus(String userid) {
		return mapper.getMenus(userid);
	}
	public int valUserAccountIsExixts(String userAccount) {
		return mapper.valUserAccountIsExixts(userAccount);
	}
	public int regist(WebUserInfo user) {
		return mapper.regist(user);
	}
	//查询用户注册报表
	public List<UserRegistCountDto> userRegistCount(Map<String, String> map) {
		return mapper.userRegistCount(map);
	}
	/**
	 * 分页查询
	 */
	public List<appUserDto> getUserInfoByisnotPager(Map<String, Object> map) {
		return mapper.getUserInfoByisnotPager(map);
	}
	/**
	 * 查询总数
	 */
	public int getUserInfonotcount(String mobile) {
		return mapper.getUserInfonotcount(mobile);
	}
	/**
	 * 查询全部分页
	 */
	public List<appUserDto> getUserInfoByisPager(Map<String, Object> map) {
		return mapper.getUserInfoByisPager(map);
	}
	/**
	 * 查询全部用户总数
	 * @return
	 */
	public int getUserInfocount() {
		return mapper.getUserInfocount();
	}
}
