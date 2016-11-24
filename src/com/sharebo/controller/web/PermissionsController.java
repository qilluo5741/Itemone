package com.sharebo.controller.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.MenuInfo;
import com.sharebo.entity.MenuType;
import com.sharebo.entity.RoleInfo;
import com.sharebo.service.WebPermissionsService;
import com.sharebo.util.Pager;
/****
 * 权限管理
 * @author niewei
 */
@Controller
@RequestMapping("web/permiss")
public class PermissionsController {
	@SuppressWarnings("unused")
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private WebPermissionsService service;
	//查询全部菜单类型
	@RequestMapping("menusAll")
	public String menusAll(ModelMap map){
		map.addAttribute("menusType",service.queryAllMenusType());
		return "permission/menusTypeManager";
	}
	//修改菜单
	@RequestMapping("updateMeunsTypeById")
	@ResponseBody
	public String updateMeunsTypeById(MenuType mt){
		//String menuTypeId,String menuTypeName
		if(service.updateMenusType(mt)>0){
			return "true";
		}
		return "false";
	}
	//删除菜单
	@RequestMapping("deleteMeunsTypeById")
	public @ResponseBody String deleteMeunsTypeById(String menuTypeId){
		// 0 存在  1成功！ 2 失败！
		if(service.valMenusIsExists(menuTypeId)>0){
			return "0";
		}
		if(service.deletemenuTypeById(menuTypeId)>0){
			return "1";
		}
		return "2";
	}
	//添加菜单类型
	@RequestMapping("addMenuType")
	public @ResponseBody String addMenuType(MenuType mt){
		//验证菜单名字是够存在
		if(service.valMenuTypeIsExists(mt.getMenuTypeName())>0){
			return "0";
		}
		//执行添加
		if(service.addMenuType(mt)>0){
			return "1";
		}
		return "2";
	}
	//分页查询菜单
	@RequestMapping("menus")
	public String getMenus(ModelMap mp,Integer pageIndex){
		Pager<MenuInfo> pager=new Pager<MenuInfo>();
		pager.setPageIndex((pageIndex==null?1:pageIndex));
		pager.setPageSize(8);
		Map<String, Object> map=new HashMap<String, Object>();
		//设置开始
		int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pager.getPageSize());
		//查询集合
		pager.setList(service.getMenusByPager(map));
		//查询总数
		pager.setTotalRecords(service.getMenusCount());
		pager.setTotalPages();//设置总页数
		mp.addAttribute("pager", pager);
		mp.addAttribute("menutype", service.queryAllMenusType());
		return "permission/menusManager";
	}
	/**
	 * 查询全部角色
	 * @param map
	 *  @author zhangke
	 * @return
	 */
	@RequestMapping("roleInfo")
	public  String RoleInfoAll(ModelMap map){
		map.addAttribute("RoleInfo",service.pagertbyList());
		return "permission/RoleInfoManager";
	}
	/**
	 * 添加角色
	 */
	@RequestMapping("addRole")
	public @ResponseBody String addRoleInfo(ModelMap map,String roleName){
		try {
			int i=service.valRoleExists(roleName);
			if(i>0){
				return "0";
			}
			RoleInfo RoleInfo=new RoleInfo();
			RoleInfo.setRoleName(roleName);
			int j=service.addRoleInfo(RoleInfo);
			if(j>0){
				return "1";
			}
		} catch (Exception e) {
			System.out.println("Oh, I was wrong!");
		}
		return "2";
	}
	 /**
	  *  修改角色
	  */
	@RequestMapping("updateRole")
	public @ResponseBody String updateRoleInfo(ModelMap map,String roleName,String roleId){
		try {
			int i=service.valRoleExists(roleName);
			if(i>0){
				return "0";
			}
			RoleInfo RoleInfo=new RoleInfo();
			RoleInfo.setRoleId(roleId);
			RoleInfo.setRoleName(roleName);
			int j=service.updateRoleInfo(RoleInfo);
			if(j>0){
				return "1";
			}
		} catch (Exception e) {
			System.out.println("Oh, I was wrong!");
		}
		return "2";
	}
	 /**
	  *  删除角色
	  */
	@RequestMapping("deleteRole")
	public @ResponseBody String deleteRole(String roleId){
		try {
			if(service.valRoleInfoExists(roleId)>0){
				return "0";
			}
			if(service.deleteRoleInfo(roleId)>0){
				return "1";
			}
		} catch (Exception e) {
			System.out.println("Oh, I was wrong!");
		}
		return "2";
	}
	//根据id查询一条菜单记录
	@RequestMapping("getMenuById")
	public @ResponseBody MenuInfo getMenuById(String menuId){
		return service.getMenuById(menuId);
	}
	//修改菜单
	@RequestMapping("updateMenuById")
	public @ResponseBody String updateMenuById(MenuInfo m){
		if(service.updateMenuById(m)>0)
			return "0";
		return "1";
	}
	//删除菜单
	@RequestMapping("deleteMenusById")
	public @ResponseBody String deleteMenusById(String menuId){
		//查询该菜单是否在角色中存在
		if(service.valRoleMenuIsExistsMenuId(menuId)>0){
			return "0";
		}
		if(service.deleteMenus(menuId)>0)
			return "1";
		//删除菜单
		return "2";//0 ：角色中存在 1 ： 删除成功  2 删除失败！
	}
	//添加菜单
	@RequestMapping("addMenus")
	public @ResponseBody String addMenus(MenuInfo m){
		//验证菜单名字是否存在
		if(service.valMenusIsistsByMenuName(m.getMenuName())>0){
			return "0";
		}
		if(service.addMeuns(m)>0){
			return "1";
		}
		return "2";
	}
	//根据角色id查询拥有 和为拥有的菜单
	@RequestMapping("role_menuManager")
	public String role_menuManager(ModelMap mp,String roleId){
		mp.addAttribute("ok_menus", service.getMenusByRoleId(roleId));
		mp.addAttribute("no_menus", service.getNotuMenusByRoleId(roleId));
		mp.addAttribute("role", roleId);
		return "permission/RoleMenuManger";
	}
	//给角色添加菜单
	@RequestMapping("addRoleMenus")
	public @ResponseBody String addRoleMenus(String roleId,String menuId){ //return 0 已经存在 1 成功 2 失败！
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("menuId", menuId);
		//验证角色菜单表中是否存在该数据
		if(service.valRoleMenuByRoleIdOnMeunId(map)>0){
			return "0";
		}
		if(service.addRoleMenus(map)>0){
			return "1";
		}
		return "2";
	}
	//删除角色菜单数据
	@RequestMapping("removeRoleMenus")
	public @ResponseBody String removeRoleMenus(String roleId,String menuId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("menuId", menuId);
		if(service.deleteRoleMenus(map)>0)
			return "0";
		return "1";
	}
	//分页查询用户
	@RequestMapping("users")
	public  String users(ModelMap mp,Integer pageIndex){
		Pager<com.sharebo.entity.Dto.UserInfoAllDto> pager=new Pager<com.sharebo.entity.Dto.UserInfoAllDto>();
		pager.setPageIndex((pageIndex==null?1:pageIndex));
		pager.setPageSize(8);
		Map<String, Object> map=new HashMap<String, Object>();
		//设置开始
		int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pager.getPageSize());
		//查询集合
		pager.setList(service.users(map));
		//查询总数
		pager.setTotalRecords(service.userAllCount());
		mp.addAttribute("pager", pager);
		//查询全部角色
		mp.addAttribute("role", service.pagertbyList());
		return "permission/userManager";
	}
	//修改用户的角色
	@RequestMapping("updateUserRole")
	public @ResponseBody String  updateUserRole(String userId,String roleId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("userId",userId);
		if(service.updateUserInfoRole(map)>0){
			return "0";
		}
		return "1";
	}
	//修改用户的状态
	@RequestMapping("updateUserStatus")
	public @ResponseBody String  updateUserStatus(String userId,Integer userStatus){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userStatus", userStatus);
		map.put("userId",userId);
		if(service.updateUserStatus(map)>0){
			return "0";
		}
		return "1";
	}
}
