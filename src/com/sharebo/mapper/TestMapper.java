package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Dayrules;
import com.sharebo.entity.OrderTime;
import com.sharebo.entity.TestInfo;
import com.sharebo.entity.Weekrules;

public interface TestMapper {
	
	public List<Weekrules> timeTest();
	//根据时间查询是否没有禁用
	public int isDayNotDisable(String today); 
	//根据某天查询是否禁用
	public int isDayDisable(String today);
	//根据星期查询是否为禁用
	public int isdisableWeek(Map<String, Object> map);
	//添加一个禁用的日期
	public int addDayrules(Map<String,String> map);
	//验证一个日期是否存在
	public int isdayrulesExists(String today);
	//修改一个存在的时间
	public int updateDisable(String today);
	//查询一天的可用时间
	public Dayrules getDayrules(Map<String,String> map);
	//查询星期规则
	public Weekrules weekTime();
	//查询订单时间表 通过日期 和车位Id
	public OrderTime getOrdertime(Map<String,String> map);
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
	public List<TestInfo> pagerTestbyList(Map<String,Object> map);
	public int pagerTestbyCount();//总数
}
