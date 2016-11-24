package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sharebo.entity.Purse;
import com.sharebo.entity.UserInfo;
import com.sharebo.entity.Dto.UserInfoDto;
import com.sharebo.entity.Dto.userDto;
import com.sharebo.mapper.UserMapper;
import com.sharebo.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper mapper;
	/***
	 * 判断所有用户是否存在
	 */
	public UserInfo isExistsByMobile(String mobile) {
		//大于0 说明存在 相反不存在
		return mapper.isExistsByMobile(mobile);
	}
	//添加1个用户
	public boolean addUser(UserInfo user){
		return mapper.addUser(user)>0?true:false;
	}
	//修改验证码
	public boolean updateCodeByMobile(UserInfo user) {
		return mapper.updateCodeByMobile(user)>0?true:false;
	}
	public boolean isPassWordIsNUllByMobile(String mobile) {
		// true:为空 fasle:不为空（用户已经存在）
		return mapper.isPassWordIsNUllByMobile(mobile)>0?true:false;
	}
	public String isGuardByMobile(String mobile) {
		return mapper.isGuardByMobile(mobile);
	}
	//通过手机号码，验证数据库是否存在
	public boolean validationCode(UserInfo user) {
		return mapper.validationCode(user)>0?true:false;
	}
	//修改普通用户信息
	public boolean regUser(UserInfo user) {
		return mapper.regUser(user)>0?true:false;
	}
	//修改代理信息
	public boolean regUserByAgent(UserInfo user) {
		return mapper.regUserByAgent(user)>0?true:false;
	}
	//修改密码
	public boolean updatePassword(UserInfo user) {
		return mapper.updatePassword(user)>0?true:false;
	}
	//验证钱包是否存在
	public boolean validationPurse(String mobile) {
		return mapper.validationPurse(mobile)>0?true:false;
	}
	//添加钱包
	public boolean addPurse(Purse purse) {
		return mapper.addPurse(purse)>0?true:false;
	}
	//用户登录
	public String userLogin(UserInfo user) {
		return mapper.userLogin(user);
	}
	//通过userid查询regid
	public String getRegIdByUserid(String userid) {
		return mapper.getRegIdByUserid(userid);
	}
	//通过userid修改token regid
	public boolean updateUserTokenAndRegIdByUserId(UserInfo user) {
		return mapper.updateUserTokenAndRegIdByUserId(user)>0?true:false;
	}
	//修改regid
	public boolean updateRegIdByuserid(UserInfo user) {
		return mapper.updateRegIdByuserid(user)>0?true:false;
	}
	//根据userid修改用户姓名
    public int updateUserNameByUserid(UserInfo user){
    	return mapper.updateUserNameByUserid(user);
    }
	//根据userid修改年龄
	public int updateAgeByUsuerid(UserInfo user){
		return mapper.updateAgeByUsuerid(user);
	}
	//根据userid修改性别
	public int updateSexByUserid(UserInfo user){
		return mapper.updateSexByUserid(user);
	}
	//根据userid修改支付宝姓名
	public int updatePaynameByUserid(UserInfo user){
		return mapper.updatePaynameByUserid(user);
	}
	//根据userid修改支付宝账号
	public int updatePaynoByUserid(UserInfo user){
		return mapper.updatePaynoByUserid(user);
	}
	//根据userid查询手机号
	public String selectMobileByUserid(String userid){
		return mapper.selectMobileByUserid(userid);
	}
	//根据userid修改用户手机号码
	public int updateMobileByUserid(UserInfo user){
		return mapper.updateMobileByUserid(user);
	}
	public int cancleCommunity(String userid) {
		return mapper.cancleCommunity(userid);
	}
	public int relateCommunity(Map<String, Object> map) {
		return mapper.relateCommunity(map);
	}
	public int selectByUserid(String userid) {
		return mapper.selectByUserid(userid);
	}
	public userDto selectByuserid(String userid) {
		return mapper.selectByuserid(userid);
	}
	//根据userid查询用户个人信息
	public List<UserInfoDto> selectUserInfoDtoByUserid(String userid){
		return mapper.selectUserInfoDtoByUserid(userid);
	}
	/**
	 * 修改头像
	 */
	public int getImageByUserid(UserInfo user) {
		return mapper.getImageByUserid(user);
	}
	/**
	 * 查询验证码
	 */
	public int selectsmscode(UserInfo user) {
		return mapper.selectsmscode(user);
	}
}
