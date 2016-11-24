package com.sharebo.service;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.MenuType;
import com.sharebo.entity.WebUserInfo;
import com.sharebo.entity.Dto.appUserDto;
public interface WebUserService {
	/**
	 * 用户登录
	 * @param user用户验证信息
	 * @return 返回用户信息
	 */
	public WebUserInfo login(WebUserInfo user);
	/**
	 * 查询用户相应角色能访问的菜单
	 * @param userid 用户ID
	 * @return 菜单类型集合
	 */
	public List<MenuType> getMenus(String userid);
	/***
	 * 验证用户账号是否存在
	 * @param userAccount 
	 * @return
	 */
	public int valUserAccountIsExixts(String userAccount);
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public int regist(WebUserInfo user);
	//查询用户注册报表
	public List<com.sharebo.entity.Dto.UserRegistCountDto> userRegistCount(Map<String, String> map);
	/**
	 * 模糊查询分页
	 */
	public List<appUserDto> getUserInfoByisnotPager(Map<String,Object> map);
	/**
	 * 查询用户总数
	 * @return
	 */
	public int getUserInfonotcount(String mobile);
	/**
	 * 查询全部分页
	 */
	public List<appUserDto> getUserInfoByisPager(Map<String,Object> map);
	/**
	 * 查询全部用户总数
	 * @return
	 */
	public int getUserInfocount();
}
