package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.MenuInfo;
import com.sharebo.entity.MenuType;
import com.sharebo.entity.RoleInfo;
import com.sharebo.entity.Dto.MenusDto;
import com.sharebo.entity.Dto.UserInfoAllDto;
import com.sharebo.mapper.WebPermissionsMapper;
import com.sharebo.service.WebPermissionsService;
@Service
public class WebPermissionsImpl implements WebPermissionsService {
	@Autowired
	private WebPermissionsMapper mapper;
	//查询全部菜单类型
	public List<MenuType> queryAllMenusType() {
		return mapper.queryAllMenusType();
	}
	//修改菜单类型
	public int updateMenusType(MenuType mt){
		return mapper.updateMenusType(mt);
	}
	//查询该类型是否存在子集
	public int valMenusIsExists(String menuTypeId) {
		return mapper.valMenusIsExists(menuTypeId);
	}
	public int deletemenuTypeById(String menuTypeId) {
		return mapper.deletemenuTypeById(menuTypeId);
	}
	// 验证菜单类型名字是否存在
	public int valMenuTypeIsExists(String menuTypeName) {
		return mapper.valMenuTypeIsExists(menuTypeName);
	}
	//添加菜单类型
	public int addMenuType(MenuType mt) {
		return mapper.addMenuType(mt);
	}
	//分页查询菜单
	
	public List<MenuInfo> getMenusByPager(Map<String, Object> map) {
		return mapper.getMenusByPager(map);
	}
	//查询菜单总数
	public int getMenusCount() {
		return mapper.getMenusCount();
	}
	public List<RoleInfo> pagertbyList() {
		return mapper.pagertbyList();
	}
	/**
	 * 添加角色
	 */
	public int addRoleInfo(RoleInfo RoleInfo) {
		return mapper.addRoleInfo(RoleInfo);
	}
	/**
	 * 删除角色
	 */
	public int deleteRoleInfo(String roleId) {
		return mapper.deleteRoleInfo(roleId);
	}
	/**
	 * 修改角色
	 */
	public int updateRoleInfo(RoleInfo RoleInfo) {
		return mapper.updateRoleInfo(RoleInfo);
	}
	/**
	 * 查询该类型是否存在子集
	 */
	public int valRoleInfoExists(String roleId) {
		return mapper.valRoleInfoExists(roleId);
	}
	//根据菜单id查询菜单
	public MenuInfo getMenuById(String menuId) {
		return mapper.getMenuById(menuId);
	}
	//修改菜单
	public int updateMenuById(MenuInfo m) {
		return mapper.updateMenuById(m);
	}
	//查询角色菜单表中是否存在菜单
	public int deleteMenus(String menuId) {
		return mapper.deleteMenus(menuId);
	}
	//删除菜单
	public int valRoleMenuIsExistsMenuId(String menuId) {
		return mapper.valRoleMenuIsExistsMenuId(menuId);
	}
	public int addMeuns(MenuInfo m) {
		return mapper.addMeuns(m);
	}
	//验证菜单是否存在
	public int valMenusIsistsByMenuName(String menuName) {
		return mapper.valMenusIsistsByMenuName(menuName);
	}
	public int valRoleExists(String roleName) {
		return mapper.valRoleExists(roleName);
	}
	public List<MenusDto> getMenusByRoleId(String roleId) {
		return mapper.getMenusByRoleId(roleId);
	}
	public List<MenusDto> getNotuMenusByRoleId(String roleId) {
		return mapper.getNotuMenusByRoleId(roleId);
	}
	public int addRoleMenus(Map<String, Object> map) {
		return mapper.addRoleMenus(map);
	}
	public int valRoleMenuByRoleIdOnMeunId(Map<String, Object> map) {
		return mapper.valRoleMenuByRoleIdOnMeunId(map);
	}
	//删除角色菜单管理数据 
	public int deleteRoleMenus(Map<String, Object> map) {
		return mapper.deleteRoleMenus(map);
	}
	//修改用户角色 
	public int updateUserInfoRole(Map<String, Object> map) {
		return mapper.updateUserInfoRole(map);
	}
	//修改用户状态 
	public int updateUserStatus(Map<String, Object> map) {
		return mapper.updateUserStatus(map);
	}
	//分页查询用户
	public int userAllCount() {
		return mapper.userAllCount();
	}
	//查询用户总数
	public List<UserInfoAllDto> users(Map<String, Object> map) {
		return mapper.users(map);
	}
}
