package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Purse;
import com.sharebo.entity.UserInfo;
import com.sharebo.entity.Dto.UserInfoDto;
import com.sharebo.entity.Dto.userDto;

public interface UserMapper {
	//查询所有用户
	public UserInfo isExistsByMobile(String mobile);
	//添加一个用户
	public int addUser(UserInfo user);
	//修改验证码
	public int updateCodeByMobile(UserInfo user);
	//根据手机号查询密码是否为空 密码不为空说明用户不存在
	public int isPassWordIsNUllByMobile(String mobile);
	//根据手机号查询是代理用户
	public String isGuardByMobile(String mobile);
	//通过手机号码，验证数据库是否存在
	public int validationCode(UserInfo user);
	//修改密码，用户级别，创建时间  普通用户
	public int regUser(UserInfo user);
	//修改代理用户信息
	public int regUserByAgent(UserInfo user);
	//修改密码
	public int updatePassword(UserInfo user);
	//验证钱包是否存在
	public int validationPurse(String mobile);
	//给用户分配钱包
	public int addPurse(Purse purse);
	//用户登录
	public String userLogin(UserInfo user);
	//通过userid查询regid
	public String getRegIdByUserid(String userid);
	//通过userid修改token.regid
	public int updateUserTokenAndRegIdByUserId(UserInfo user);
	//修改用户的regid
	public int updateRegIdByuserid(UserInfo user);
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
	//根据userid查询一个对象
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
