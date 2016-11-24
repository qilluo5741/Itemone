package com.sharebo.controller; 
import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.Purse;
import com.sharebo.entity.ResultDto;
import com.sharebo.entity.UserInfo;
import com.sharebo.entity.Dto.UserInfoDto;
import com.sharebo.entity.Dto.userDto;
import com.sharebo.service.FileService;
import com.sharebo.service.UserService;
import com.sharebo.util.JPushClientExample;
import com.sharebo.util.Log;
import com.sharebo.util.MD5Util;
import com.sharebo.util.SmsUtil;
import com.sharebo.util.security.Base64;

@RestController
@RequestMapping("/{sign}/user")
public class UserController {
	@Resource
	private UserService service;
	@Autowired
	private FileService fileService;
	
	@RequestMapping("updateRegId")
	public ResultDto updateRegId(UserInfo user){
		//验证参数是否合法
		if(user.getUserid()==null||user.getRegid()==null){
			return new ResultDto(1004,"参数不能为空");
		}
		//查询
		if(service.updateRegIdByuserid(user)){
			return new ResultDto(200,"更改成功！");
		}
		else{
			return new ResultDto(1008,"更改失败！");
		}
	}
	//用户登录
	@RequestMapping("login")
	public ResultDto login(UserInfo user){
		//验证参数是否合法
		if(user.getMobile()==null||user.getPassword()==null||user.getRegid()==null){
			return new ResultDto(1004,"参数不合法！");
		}
		//验证是否代理参数是否合法
		else if(user.getIs_guard()!=0&&user.getIs_guard()!=1){
			return new ResultDto(1004,"参数不合法！");
		}
		else{
			//密码加密
			user.setPassword(MD5Util.encode(user.getPassword()));
			//userid
			String userid=service.userLogin(user);
			if(userid!=null){
				//查询regid 判断是否为空 不为空\
				String uRegid=service.getRegIdByUserid(userid);
				if(!(uRegid!=null&&uRegid.equals(user.getRegid()))){
					//regid不相等
					//发送通知  之前的手机发送通知。。你被迫下线
					System.out.println("旧regid："+uRegid);
					JPushClientExample.sendandroidRegIdMessages(uRegid);
				}
				user.setUserid(userid);
				String token=""+new Date().getTime()+new Random().nextInt(1000);
				user.setToken(token);
				//修改token regid
				if(service.updateUserTokenAndRegIdByUserId(user)){
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("userid",userid);
					map.put("token",token);
					map.put("is_guard",user.getIs_guard());
					return new ResultDto(200,"登录成功！",map);
				}
			}
			return new ResultDto(1008,"登录失败！请检查用户名密码是否正确！");
		}
	}
	
	//重新设置密码
	@RequestMapping("retrieve_password")
	public ResultDto retrievePassword(String mobile,String password,String smscode){
		//验证参数是否合法
		if(mobile==null||password==null||smscode==null){
			return new ResultDto(1004,"参数不合法！");
		}
		UserInfo user=new UserInfo();
		user.setMobile(mobile);
		user.setSmscode(smscode);
		//验证手机 验证码是否正确
		if(service.validationCode(user)){
			user.setPassword(MD5Util.encode(password));
			if(service.updatePassword(user)){
				return new ResultDto(200,"修改成功！");
			}else{
				return new ResultDto(1000,"修改失败！");
			}
		}else{
			return new ResultDto(1001,"验证码错误！");
		}
	}
	//找回密码-发送验证码
	@RequestMapping("retrieve_passwordSendCode")//376442
	public ResultDto retrievePasswordSendCode(String mobile){
		 //验证数据库中是否存在 true: 存在false：不存在
		   UserInfo user=service.isExistsByMobile(mobile);
		   if(user!=null){
			  if(!DateUtils.addMinutes(user.getDate_updated(),1).before(new Date())){
			   		//不可以发送 一分钟后再试
			   		return new ResultDto(1002,"验证码发送频繁，请一分钟后再试！");
			  } 
			  //生成验证码
			   String authCode = RandomStringUtils.randomNumeric(6);
			   user.setSmscode(authCode);
			   //修改用户验证码
			   service.updateCodeByMobile(user);
			   sendCode(mobile, authCode);
			   return new ResultDto(200,"验证码发送成功！");
		   }
		   return new ResultDto(1003,"用户不存在！");
	}
	//617946
	//注册代理用户 http://localhost:8080/sharebo/user/regagent.do?mobile=18581343206&userName=adfsaf&password=123456&smscode=123456&bank_open_no=11111111&bank_type=zsyh&communityName=tccname&community_address=addredd
	@RequestMapping("regagent")
	public ResultDto regAgent(UserInfo user){
		//验证参数是否为空  手机号，密码，姓名，银行卡号码 ，开户行，小区名字，小区地址
		if(user.getMobile()==null||user.getSmscode()==null||user.getPassword()==null||user.getSmscode()==null||user.getUserName()==null||user.getBank_open_no()==null||user.getBank_type()==null||user.getCommunityName()==null||user.getCommunity_address()==null){
			return new ResultDto(1004,"参数不合法！");
		}
		//比较验证码是否一致
		if(service.validationCode(user)){
			//设置银行开户姓名
			user.setBank_open_name(user.getUserName());
			user.setPassword(MD5Util.encode(user.getPassword()));
			if(service.regUserByAgent(user)){
				//验证是否有钱包账户
				if(!service.validationPurse(user.getMobile())){
					//添加一个钱包数据
					Purse p=new Purse();
					p.setUser(user);
					service.addPurse(p);
				}
				/****************************************************************************************/
				/************************************备注：注册成功后，修改邀请记录表中状态  给邀请的用户奖励*********************/
				/****************************************************************************************/
				return new ResultDto(200,"注册成功");
			}else{
				return new ResultDto(1005,"注册失败！");
			}
		}
		else{
			return new ResultDto(1001,"验证码错误！");
		}
	}
	//注册普通用户
	@RequestMapping("reguser")
	public ResultDto registered(String mobile,String password,String smscode){
		//验证参数是否合法
		if(mobile==null||password==null||smscode==null){
			return new ResultDto(1004,"参数不合法！");
		}
		UserInfo user=new UserInfo();
		user.setMobile(mobile);
		user.setPassword(MD5Util.encode(password));
		user.setSmscode(smscode);
		//验证手机号，验证码是否存在
		if(!service.validationCode(user)){
			return new ResultDto(1001,"验证码错误！");
		}
		else{
			//修改密码  用户注册时间 用户状态
			if(service.regUser(user)){
				//验证是否有钱包账户
				if(!service.validationPurse(user.getMobile())){
					//添加一个钱包数据
					Purse p=new Purse();
					p.setUser(user);
					service.addPurse(p);
				}
				/****************************************************************************************/
				/************************************备注：注册成功后，修改邀请记录表中状态  给邀请的用户奖励*********************/
				/****************************************************************************************/
				return new ResultDto(200,"注册成功！");
			}else{
				return new ResultDto(1006,"普通用户注册失败！");
			}
		}
	}
	
	//注册发送验证码
	@RequestMapping("regSendSmsCode")
	public ResultDto regSendSmsCode(String mobile,String isagent){
		//验证手机号是否为空
		if(null==mobile||isagent==null){
			return new ResultDto(1004,"参数缺省！");
		}
		//通过正则验证手机号是否合法
		Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");     
	    Matcher m = p.matcher(mobile);
	    if(!m.matches()){//手机号验证不成功
	    	return new ResultDto(1007,"手机号码输入不合法！");
	    }
	    
	   //验证数据库中是否存在 true: 存在false：不存在
	   UserInfo user=service.isExistsByMobile(mobile);
	   //生成验证码
	   String authCode = RandomStringUtils.randomNumeric(6);
	   if(user!=null){
		    //存在 
		   if(service.isPassWordIsNUllByMobile(mobile)){
				//查询最近时间
			   	Date ud=user.getDate_updated();
			   	if(!DateUtils.addMinutes(ud, 1).before(new Date())){
			   		//不可以发送 一分钟后再试
			   		return new ResultDto(1002,"验证码发送频繁，请一分钟后再试！");
			   	}
			   	else{
			   	   user.setMobile(mobile);//设置手机号
				   user.setSmscode(authCode);//设置验证码
				   //修改验证码和修改时间
				   if(!service.updateCodeByMobile(user)){
					   Log.getInstance().error("修改验证码失败！"+mobile+" 用户");
				   }
				   //发送验证码
				   sendCode(mobile, authCode);
				   return new ResultDto(200,"验证码发送成功！");
			   	}
		   }else{
			   //如果是代理请求，需要重新查询
			   if(isagent.equals("1")){
				   //通过手机号查询是否是代理 0 ：普通用户 1：代理用户  如果是1 就是代理用户 代表已经存在 如果是0 代表普通用户 重新修改验证码发送
				   if("1".equals(service.isGuardByMobile(mobile))){
					   return new ResultDto(1003,"代理用户已注册！");
				   }else{
					  Date ud=user.getDate_updated();
				   	  if(!DateUtils.addMinutes(ud, 1).before(new Date())){
				   		//不可以发送 一分钟后再试
				   		return new ResultDto(1002,"验证码发送频繁，请一分钟后再试！");
				       }
					   user.setMobile(mobile);//设置手机号
					   user.setSmscode(authCode);//设置验证码
					   if(!service.updateCodeByMobile(user)){
						   Log.getInstance().error("修改验证码失败！"+mobile+" 用户");
					   }
					   //发送验证码
					   sendCode(mobile, authCode);
					   return new ResultDto(200,"验证码发送成功！");
				   }
			   }else{
				   //是普通用户
				   return new ResultDto(1003,"用户已经存在！");
			   }
		   }
	   }
	   else{
		   user=new UserInfo();
		   //不存在，添加user数据 发送验证码
		   //user.setUserid(UUID.randomUUID().toString());
		   user.setMobile(mobile);//设置手机号
		   user.setSmscode(authCode);//设置验证码
		   user.setIs_guard(0);//设置不是代理
		   //添加用户
		   if(service.addUser(user)){
			   //发送验证码
			   sendCode(mobile, authCode);
			  return new ResultDto(200,"验证码发送成功！");
		   }else{
			   Log.getInstance().error("添加用户失败！"+mobile+"用户");
		   }
	   }
	return null;
	}
	//发送短信验证码方法
	public static void sendCode(String mobile,String code){
		System.out.println("手机："+mobile+"   验证码："+code);
		new SmsUtil().sendCode(mobile, code);
	}
	
	
	//根据userid修改用户姓名
	@RequestMapping("updateUserNameByUserid")
	public ResultDto updateUserName(String userid,String userName){
		if(userid==null){
			//验证userid是否为空
			return new ResultDto(3002,"用户ID不能为空");
		}
		else if(userName==null){
			//验证用户姓名是否为空
			return new ResultDto(3003,"用户姓名不能为空");
		}
		UserInfo u=new UserInfo();
		u.setUserid(userid);
		u.setUserName(userName);//修改用户姓名
		int i=service.updateUserNameByUserid(u);
		if(i>0){
			return new ResultDto(200,"修改成功");
		}
		return new ResultDto(3004,"修改失败");
	}
	//根据userid修改用户年龄user/updateAgeByUsuerid.do
	@RequestMapping("updateAgeByUsuerid")
	public ResultDto updateAgeByUsuerid(int age,String userid){
		if(userid==null){
			//验证userid是否为空
			return new ResultDto(3005,"用户ID不能为空");
		}
		UserInfo u=new UserInfo();
		u.setAge(age);
		u.setUserid(userid);
		int i=service.updateAgeByUsuerid(u);
		if(i>0) {
			return new ResultDto(200,"修改成功");
		}
		return new ResultDto(3007,"修改失败");
	}
	//根据userid修改用户性别
	@RequestMapping("updateSexByUserid")
	public ResultDto updateSexByUserid(Integer sex,String userid){
		if(userid==null){
			return new ResultDto(3008,"用户ID不能为空");
		}
		UserInfo u=new UserInfo();
		u.setSex(sex);
		u.setUserid(userid);
		int i=service.updateSexByUserid(u);
		if(i>0) {
			return new ResultDto(200,"修改成功");
		}
		return new ResultDto(3010,"修改失败");
	}
	//根据userid修改支付宝姓名
	@RequestMapping("updatePaynameByUserid")
	public ResultDto updatePaynameByUserid(String payname,String userid){
		if(userid==null){
			return new ResultDto(3011,"用户ID不能为空");
		}
		else if(payname==null){
			return new ResultDto(3012,"支付宝姓名不能为空");
		}
		UserInfo u=new UserInfo();
		u.setPayname(payname);
		u.setUserid(userid);
		int i=service.updatePaynameByUserid(u);
		if(i>0) {
			return new ResultDto(200,"修改成功");
		}
		return new ResultDto(3013,"修改失败");
	}
	//根据userid修改支付宝账号
	@RequestMapping("updatePaynoByUserid")
	public ResultDto updatePaynoByUserid(String payno,String userid){
		if(userid==null){
			return new ResultDto(3014,"用户ID不能为空");
		}
		else if(payno==null){
			return new ResultDto(3015,"支付宝账号不能为空");
		}
		UserInfo u=new UserInfo();
		u.setPayno(payno);
		u.setUserid(userid);
		int i=service.updatePaynoByUserid(u);
		if(i>0) {
			return new ResultDto(200,"修改成功");
		}
		return new ResultDto(3016,"修改失败");
	}
	//根据userid查询手机号码
	@RequestMapping("selectMobileByUserid")
	public ResultDto selectMobileByUserid(String userid){
		if(userid==null){
			return new ResultDto(3017,"用户ID不能为空");
		}
		String i=service.selectMobileByUserid(userid);
		if(i!=null){
			return new ResultDto(200,"查询成功",i);
		}
		return new ResultDto(3018,"查询失败");
	}
	//根据userid修改手机号码
	@RequestMapping("updateMobileByUserid")
	public ResultDto updateMobileByUserid(String mobile,String userid,String smscode){
		if(mobile==null || userid==null || smscode==null){
			return new ResultDto(2004,"参数缺省！");
		}
		UserInfo user=new UserInfo();
		user.setUserid(userid);
		user.setMobile(mobile);
		user.setSmscode(smscode);
		int j=service.selectsmscode(user);
		if(j<0){
			return new ResultDto(2020,"验证码错误！");
		}else{
			int i=service.updateMobileByUserid(user);
			if(i>0) {
				return new ResultDto(200,"修改成功");
			}
			return new ResultDto(3021,"修改失败");
		}
	}
	/**
	 * 代理用户是否关联小区
	 */
	@RequestMapping("selectByUserid")
	public ResultDto selectByUserid(String userid){
		if(userid==null){
			return new ResultDto(4002,"参数不合法");
		}
		int r = service.selectByUserid(userid);
		if(r>0){			
				return new  ResultDto(4004,"当前用户目前没有关联小区",false);
		}else{
			return new ResultDto(4005,"当前用户已经关联小区",true);
		}
	}
	/**
	 *代理用户关联小区
	 */
	@RequestMapping("relateCommunity")
	public ResultDto relateCommunity(String userid,String communityName,String community_address,String entrance,String id_card){
		
		if (userid==null&&communityName==null&&entrance==null&&id_card==null&&community_address==null) {
			return new ResultDto(4001,"参数不合法");
		}
		Map<String, Object> map  =new  HashMap<String, Object>();
		map.put("userid", userid);
		map.put("communityName", communityName);
		map.put("entrance", entrance);
		map.put("id_card", id_card);
		map.put("community_address", community_address);
		int r =service.relateCommunity(map);
		if (r>0) {
			return new ResultDto(4006,"关联小区成功");
		}
		else{
			return new  ResultDto(4007,"关联小区失败");
		}
	}
	/**
	 * 代理用户解除小区
	 */
	@RequestMapping("cancleCommunity")
	public ResultDto cancleCommunity(String userid){
		if(userid==null){
			return new ResultDto(4001,"参数不合法");
		}
		int r = service.cancleCommunity(userid);
		if(r>0){
			return new  ResultDto(4008,"解除小区成功");
		}
		else{
			return new ResultDto(4009,"解除小区失败");
		}
	}
	/**
	 * 查看关联小区的信息
	 * 
	 */
	@RequestMapping("guanuser")
	public ResultDto guanuser(String userid){
		if(userid==null){
			return new ResultDto(4001,"参数不合法");
		}
		userDto u = service.selectByuserid(userid);
		if(u!=null){
			return new ResultDto(200,u);
		}
		else{
			return new ResultDto(4001,"获取值异常");
		}
	}
		//根据userid查询用户个人信息
		@RequestMapping("selectUserInfoDto")
		public ResultDto selectUserInfoDto(String userid){
			if(userid==null){
				//判断用户ID是否为空
				return new ResultDto(3022,"用户id不能为空!");
			}
			List<UserInfoDto> ud=service.selectUserInfoDtoByUserid(userid);//得到用户信息
			//验证是否得到用户信息
			if(ud!=null){
				return new ResultDto(200,ud);//返回用户信息
			}
			return new ResultDto(3023,"查询失败!");
		}
		/**
		 * 修改用户头像
		 * @param userid
		 * @return
		 */
		@RequestMapping("updateheadimage")
		public ResultDto updateImage(String headportrait,String userid){
		try {
			if(userid==null && headportrait==null){
				//判断用户ID是否为空
				return new ResultDto(3022,"用户id不能为空!");
			}
			byte[] by = Base64.decode(headportrait);
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			UserInfo user=new UserInfo();
			user.setHeadportrait(headimg);
			user.setUserid(userid);
			int i=service.getImageByUserid(user);
			if(i>0){			
				return new  ResultDto(200,"修改成功!");
			}else{
				return new  ResultDto(3021,"修改失败!");
			}
		} catch (Exception e) {
			return null;
		}
	}
}
