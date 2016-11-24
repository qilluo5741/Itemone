package com.sharebo.entity;

import java.io.Serializable;

/**
 * 菜单角色
 * @author niewei
 *
 */
public class MenuRoleInfo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rmid;//角色菜单表ID
	private RoleInfo role;//角色
	private MenuInfo menu;//菜单
	public String getRmid() {
		return rmid;
	}
	public void setRmid(String rmid) {
		this.rmid = rmid;
	}
	public RoleInfo getRole() {
		return role;
	}
	public void setRole(RoleInfo role) {
		this.role = role;
	}
	public MenuInfo getMenu() {
		return menu;
	}
	public void setMenu(MenuInfo menu) {
		this.menu = menu;
	}
	public MenuRoleInfo(String rmid, RoleInfo role, MenuInfo menu) {
		super();
		this.rmid = rmid;
		this.role = role;
		this.menu = menu;
	}
	public MenuRoleInfo() {
		super();
	}
	
}