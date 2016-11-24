package com.sharebo.entity;
import java.io.Serializable;
public class TestInfo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tid;
	private String tname;
	public String getId() {
		return tid;
	}
	public void setId(String id) {
		this.tid = id;
	}
	public String getName() {
		return tname;
	}
	public void setName(String name) {
		this.tname = name;
	}
	public TestInfo(String tid, String tname) {
		this.tid = tid;
		this.tname = tname;
	}
	public TestInfo() {
	}
}
