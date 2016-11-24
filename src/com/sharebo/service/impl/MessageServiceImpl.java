package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Message;
import com.sharebo.mapper.MessageMapper;
import com.sharebo.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageMapper mapper;
	/**
	 * 添加message消息
	 */
	public int AddMessage(Message message) {
		return mapper.AddMessage(message);
	}
	/**
	 * 分页查询供方消息记录
	 */
	public int pagersupplierMessagebyCount() {
		return mapper.pagersupplierMessagebyCount();
	}
	public List<Message> pagersupplierMessagebyList(Map<String, Object> map) {
		return mapper.pagersupplierMessagebyList(map);
	}
	public int pagerbuyerMessagebyCount() {
		return mapper.pagerbuyerMessagebyCount();
	}
	public List<Message> pagerbuyerMessagebyList(Map<String, Object> map) {
		return mapper.pagerbuyerMessagebyList(map);
	}
}
