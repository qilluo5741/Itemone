package com.sharebo.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 分页查询 工具
 * @author niewei
 *
 * @param <T>
 */
public class Pager<T> {
	private int pageSize;
	private int pageIndex;
	private int totalRecords;
	private int totalPages;
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	private List<T> list = new ArrayList<T>();
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		//获取第一个index
		if(pageIndex<=0){
			return 1;
		}
		/*else if(pageIndex>totalPages){
			return getTotalPages();
		}*/else
		{
		return pageIndex;
		}
	}
	public void setPageIndex(int pageIndex) {
		//设置当当前页数
		if(pageIndex<=0){
			this.pageIndex =1;
		}
		this.pageIndex = pageIndex;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
		//设置总页数
		this.setTotalPages();
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages() {
		this.totalPages = this.totalRecords % this.pageSize == 0 ? this.totalRecords / this.pageSize : this.totalRecords / this.pageSize + 1;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public boolean getIsLast(){
		if(this.pageIndex >= this.totalPages){
			return true;
		}else{
			return false;
		}
	}
	public boolean hasNext(){
		if(this.pageIndex < this.totalPages){
			return true;
		}else{
			return false;
		}
	}

}
