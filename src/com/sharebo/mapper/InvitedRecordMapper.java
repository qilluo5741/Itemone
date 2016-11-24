package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.InvitedRecord;
/**
 *邀请记录 
 * @author Administrator
 *
 */
public interface InvitedRecordMapper {
	/**
	 * 插入
	 * @return
	 */
	public int insertInvited(InvitedRecord Invited);
	/**
	 * 验证手机号码是否存在
	 */
	public int InvitedRecordPurse(String mobile);
	/**
	 * 验证是否被邀请
	 */
	public int InvitedRecord(String mobile);
	/**
	 * 分页查询邀请记录
	 * @param pager
	 * @return
	 */
	public List< InvitedRecord> pagerInvitedbyList(Map<String,Object> map);
	public int pagerInvitedbyCount();//总数
}
