package com.sharebo.service;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.MenuInfo;
import com.sharebo.entity.MenuType;
import com.sharebo.entity.RoleInfo;
import com.sharebo.entity.Dto.MenusDto;

/***
 * 
 * @author niewei
 *
 */
public interface WebPermissionsService {
	//分页查询用户
	public  List<com.sharebo.entity.Dto.UserInfoAllDto> users(Map<String,Object> map);
	//查询用户总数
	public int userAllCount();
	//修改用户角色 
	public int updateUserInfoRole(Map<String,Object> map);
	//修改用户状态 
	public int updateUserStatus(Map<String,Object> map);
	//删除角色菜单管理数据 
	public int deleteRoleMenus(Map<String,Object> map);
	//根据角色id 和菜单id 验证此角色是否拥有了菜单
	public int valRoleMenuByRoleIdOnMeunId(Map<String,Object> map);
	//添加角色菜单数据
	public int addRoleMenus(Map<String,Object> map);
	//根据角色id查询已经选择的菜单
	public List<MenusDto> getMenusByRoleId(String roleId);
	// 根据角色id查询未选择的菜单
	public List<MenusDto> getNotuMenusByRoleId(String roleId);
	//分页查询菜单
	public List<MenuInfo> getMenusByPager(Map<String,Object> map);
	//查询菜单总数
	public int getMenusCount();
	//查询全部菜单类型
	public List<MenuType> queryAllMenusType();
	//修改菜单类型
	public int updateMenusType(MenuType mt);
	//查询该类型是否存在子集
	public int valMenusIsExists(String menuTypeId);
	//删除菜单类型
	public int deletemenuTypeById(String menuTypeId);
	// 验证菜单类型名字是否存在
	public int valMenuTypeIsExists(String menuTypeName);
	//添加菜单类型
	public int addMenuType(com.sharebo.entity.MenuType mt);
	/**
	 * 查询该角色是否存在
	 */
	public int valRoleExists(String roleName);
	/**
	 * 查询该类型是否存在子集
	 */
	public int valRoleInfoExists(String roleId);
	/**
	 * 查询角色
	 * @return
	 */
	public List<RoleInfo> pagertbyList();
	/**
	 * 添加角色
	 */
	public int addRoleInfo(RoleInfo RoleInfo);
	/**
	 * 修改角色
	 */
	public int updateRoleInfo(RoleInfo RoleInfo);
	/**
	 * 删除角色
	 */
	public int deleteRoleInfo(String roleId);
	//根据菜单id查询菜单
	public MenuInfo getMenuById(String menuId);
	//修改菜单
	public int updateMenuById(MenuInfo m);
	//查询角色菜单表中是否存在菜单
	public int valRoleMenuIsExistsMenuId(String menuId);
	//删除菜单
	public int deleteMenus(String menuId);
	//添加菜单
	public int addMeuns(MenuInfo m);
	//验证菜单是否存在
	public int valMenusIsistsByMenuName(String menuName);
}
