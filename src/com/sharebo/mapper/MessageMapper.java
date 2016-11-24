package com.sharebo.mapper;

import java.util.List;
import java.util.Map;
import com.sharebo.entity.Message;

public interface MessageMapper {
	/**
	 * 添加消息
	 * @param message
	 * @return
	 */
	public int AddMessage(Message message);
	/**
	 * 分页查询供方消息记录
	 * @param pager
	 * @return supplier
	 */
	public List< Message> pagersupplierMessagebyList(Map<String,Object> map);
	public int pagersupplierMessagebyCount();//总数
	/**
	 * 分页查询需方消息记录
	 */
	public List< Message> pagerbuyerMessagebyList(Map<String,Object> map);
	public int pagerbuyerMessagebyCount();//总数
}
