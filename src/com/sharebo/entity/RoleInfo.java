package com.sharebo.entity;
import java.io.Serializable;
/**
 * 角色表
 * @author niewei
 *
 */
public class RoleInfo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;//角色ID
	private String roleName;//角色名字
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public RoleInfo() {
	}
	public RoleInfo(String roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
}
