package com.sharebo.service;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Dayrules;
import com.sharebo.entity.TestInfo;
import com.sharebo.entity.Weekrules;
import com.sharebo.util.Pager;

public interface testService {
	public List<Weekrules> timeTest();
	//根据时间查询是否禁用
	public boolean isdisable(String today); 
	//添加一个禁用日期
	public int addDayrules(Map<String,String> map);
	//查询一天的可用时间
	public Dayrules getDayrules(Map<String,String> map);
	/**
	 * 查询
	 * @return
	 */
	public List<TestInfo> getTestInfo();
	/**
	 * 修改
	 * @return
	 */
	public int updateTestInfo(TestInfo test);
	/**
	 * 插入
	 * @return
	 */
	public int insertTestInfo(TestInfo test);
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	public Pager<TestInfo> pagerTestbyList(int pageIndex,int pageSize);
}
