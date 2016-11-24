package com.sharebo.entity;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单类型
 */
public class MenuType  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String menuTypeId;
	private String menuTypeName;
	private String menuTypeicon;
	private String remark;
	private List<MenuInfo> menus;
	public List<MenuInfo> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuInfo> menus) {
		this.menus = menus;
	}
	public String getMenuTypeId() {
		return menuTypeId;
	}
	public void setMenuTypeId(String menuTypeId) {
		this.menuTypeId = menuTypeId;
	}
	public String getMenuTypeName() {
		return menuTypeName;
	}
	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}
	
	public String getMenuTypeicon() {
		return menuTypeicon;
	}
	public void setMenuTypeicon(String menuTypeicon) {
		this.menuTypeicon = menuTypeicon;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
