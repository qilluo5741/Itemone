package com.sharebo.entity;

import java.io.Serializable;

/**
 * 菜单模型
 * @author niewei
 *
 */
public class MenuInfo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String menuId;//菜单ID
	private String menuName;//菜单名字
	private String href;//路径
	private String target;
	private MenuType mt;
	
	public MenuType getMt() {
		return mt;
	}
	public void setMt(MenuType mt) {
		this.mt = mt;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public MenuInfo() {
	}
}
