package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.InvitedRecord;
import com.sharebo.mapper.InvitedRecordMapper;
import com.sharebo.service.InvitedRecordService;
/**
 *记录好友
 * @author Administrator
 *
 */
@Service
public class InvitedRecordServiceImpl implements InvitedRecordService {
	@Autowired
	private InvitedRecordMapper mapper;
	//查询邀请用户手机号是否存在
	public int InvitedRecordPurse(String mobile) {
		return mapper.InvitedRecordPurse(mobile);
	}
	//添加邀请记录
	public int insertInvited(InvitedRecord Invited) {
		return mapper.insertInvited(Invited);
	}
	/**
	 * 验证是否被邀请
	 */
	public int InvitedRecord(String mobile) {
		return mapper.InvitedRecord(mobile);
	}
	public int pagerInvitedbyCount() {
		return mapper.pagerInvitedbyCount();
	}
	public List<InvitedRecord> pagerInvitedbyList(Map<String, Object> map) {
		return mapper.pagerInvitedbyList(map);
	}
}
