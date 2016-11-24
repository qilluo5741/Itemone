package com.sharebo.service;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Purse;
import com.sharebo.entity.UserInfo;
import com.sharebo.entity.Dto.UserInfoDto;
import com.sharebo.entity.Dto.userDto;

public interface UserService {
	//判断所有用户是否存在
	public UserInfo isExistsByMobile(String mobile);
	//添加一个用户
	public boolean addUser(UserInfo user);
	//修改验证码
	public boolean updateCodeByMobile(UserInfo user);
	//通过手机号验证用户密码是否为空
	public boolean isPassWordIsNUllByMobile(String mobile);
	//根据手机号查询是代理用户
	public String isGuardByMobile(String mobile);
	//通过手机号码，验证数据库是否存在
	public boolean validationCode(UserInfo user);
	//修改密码，用户级别，创建时间
	public boolean regUser(UserInfo user);
	//修改代理用户信息
	public boolean regUserByAgent(UserInfo user);
	//修改密码
	public boolean updatePassword(UserInfo user);
	//验证钱包是否存在
	public boolean validationPurse(String mobile);
	//给用户分配钱包
	public boolean addPurse(Purse purse);
	//用户登录  返回userid
	public String userLogin(UserInfo user);
	//通过userid查询regid
	public String getRegIdByUserid(String userid);
	//通过userid修改token.regid
	public boolean updateUserTokenAndRegIdByUserId(UserInfo user);
	//修改用户的regid
	public boolean  updateRegIdByuserid(UserInfo user);
	//根据userid修改姓名
	public int updateUserNameByUserid(UserInfo user);
	//根据userid修改年龄
	public int updateAgeByUsuerid(UserInfo user);
	//根据userid修改性别
	public int updateSexByUserid(UserInfo user);
	//根据userid修改支付宝姓名
	public int updatePaynameByUserid(UserInfo user);
	//根据userid修改支付宝账号
	public int updatePaynoByUserid(UserInfo user);
	//根据userid查询手机号
	public String selectMobileByUserid(String userid);
	//根据userid修改用户手机号码
	public int updateMobileByUserid(UserInfo user);
	//根据手机号查询一个对象
	public int selectByUserid(String userid);
	//代理用户关联小区
	public int relateCommunity(Map<String, Object> map);
	//代理用户解除小区
	public int  cancleCommunity(String userid);
	//根据useid 查看关联小区的信息
	public userDto selectByuserid(String userid);
	//根据userid查询用户个人信息
	public List<UserInfoDto> selectUserInfoDtoByUserid(String userid);
	/**
	 * 修改头像
	 */
	public int getImageByUserid(UserInfo user);
	/**
	 * 查询验证码
	 */
	public int selectsmscode(UserInfo user);
}
