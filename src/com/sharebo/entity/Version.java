package com.sharebo.entity;
/***
 * 版本信息
 * @author Administrator
 */
public class Version implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String versionid;//主键ID
	private int version;//版本号
	private String  description;//'描述',
	private String  url;//'路劲',
	private String  os_version;//'操作系统版本号',
	public String getVersionid() {
		return versionid;
	}
	public void setVersionid(String versionid) {
		this.versionid = versionid;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String osVersion) {
		os_version = osVersion;
	}
}
