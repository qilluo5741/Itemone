package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sharebo.entity.Certification;
import com.sharebo.entity.ResultDto;
import com.sharebo.entity.UserInfo;
import com.sharebo.entity.Vehicle;
import com.sharebo.entity.Dto.VehicleDto;
import com.sharebo.service.FileService;
import com.sharebo.service.VehicleService;
import com.sharebo.util.security.Base64;


/**
 * 车辆信息
 * @author zhangke
 */
@RestController
@RequestMapping("{sign}/Vehicle")
public class VehicleController {
	@Autowired
	private VehicleService service;//导入车辆service
	@Autowired
	private FileService fileService;//导入上传service
	//添加车牌号
	@RequestMapping("/add-vehicle")
	public ResultDto insertVehicle(String vehicleno,String userid){
		 try {
			if(vehicleno!=null){//判断车牌号是否为空
				 Vehicle vehicle=new Vehicle();
				 vehicle.setVehicleno(vehicleno);//传入车牌号值
				 vehicle.setUser(new UserInfo(userid));//获取user的id
				 int i=service.insertVehicle(vehicle);//插入数据
				 if(i>0){
					return new ResultDto(200,"添加车牌成功！");
				 }else{
					return new ResultDto(2010,"添加车牌失败！");
				 }
			 }else{
				 return new  ResultDto(2009,"返回值有异常");
			 }
		} catch (Exception e) {
			return new  ResultDto(2009,"返回值有异常");
		}
	}
	//根据vehicleid删除数据,删除验证车牌号是否是认证车牌号，是就不能删除
	@RequestMapping("/delete-vehicle")
	public ResultDto delectVehicle(String vehicleid){
		 if(vehicleid!=null){//判断车牌号是否为空
			 int i=service.count(vehicleid);//根据vehicleid去查询certification是否已经认证，未认证执行以下删除操作
			 if(i>0){
				 return new ResultDto(2011,"该车牌已经认证，不能删除！");
			 }else{
				 int j=service.delectVehicle(vehicleid);//删除未认证的车牌号
				 if(j>0){
					 return new ResultDto(200,"删除车牌成功！");
				 }else{
					 return new ResultDto(2012,"删除车牌失败！");
				 }
			 }
		 }else{
			 return new  ResultDto(2009,"返回值有异常");
		 }
	}
	//根据userid查询所有车牌号
	@RequestMapping("/select-vehicle")
	public ResultDto getvehicleno(String userid){
		 if(userid!=null){//判断用户id是否为空
			 List<VehicleDto> list=service.getvehicleno(userid);//根据userid查询车牌号
			 if(list!=null){
				 return new ResultDto(200,"查询成功！",list);
			 }else{
				 return new ResultDto(2013,"你还未添加车牌");
			 }
		 }else{
			 return new  ResultDto(2009,"请求参数为空!");
		 }
	}
	
	//车主认证-提交资料
	@RequestMapping("/add-certcation")
	public ResultDto insertCertification(String driver_license_positive,String driver_license_reverse,String vehicle_license_positive,String vehicle_license_reverse,String email,String userid,String vehicleid){
		 if(driver_license_positive != null && driver_license_reverse !=null && vehicle_license_positive!=null && vehicle_license_reverse!=null && userid!=null && vehicleid!=null && email!=null){//判断userid 驾驶证，行驶证，邮箱，车辆id是否为空
			 if(email!=null){
				 Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");//正则表达式验证邮箱是否正确
			     Matcher m = p.matcher(email);
			     if(!m.matches()){//邮箱格式不正确
			    	return new ResultDto(2015,"邮箱格式不正确！");
			     }
			 }
			 int j=service.selectCertific(userid);
			 if(j>0){
				 return new ResultDto(2019,"该车主已经认证了！");
			 }
			 //视图解密
			 byte[] by = Base64.decode(driver_license_positive);//得到驾驶证（正面）数据流
		     ByteArrayInputStream bais = new ByteArrayInputStream(by);
		     String dpositive = this.fileService.uploadImage(bais);
		     
		     byte[] by1 = Base64.decode(driver_license_positive);//得到驾驶证（反面）数据流
		     ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
		     String dreverse = this.fileService.uploadImage(bais1);
		     
		     byte[] by2 = Base64.decode(driver_license_positive);//得到行驶证（正面）数据流
		     ByteArrayInputStream bais2 = new ByteArrayInputStream(by2);
		     String vpositive = this.fileService.uploadImage(bais2);
		     
		     byte[] by3 = Base64.decode(driver_license_positive);//得到行驶证（反面）数据流
		     ByteArrayInputStream bais3 = new ByteArrayInputStream(by3);
		     String vreverse = this.fileService.uploadImage(bais3);
		     
		     Certification cert=new Certification();//创建对象
		     cert.setUser(new UserInfo(userid));
		     cert.setVehicle(new Vehicle(vehicleid));
		     cert.setDriver_license_positive(dpositive);//放入参数驾驶证（正面）
		     cert.setDriver_license_reverse(dreverse);//放入参数驾驶证（反面）
		     cert.setVehicle_license_positive(vpositive);//放入参数行驶证驶证（正面）
		     cert.setVehicle_license_reverse(vreverse);//放入参数行驶证驶证（反面）
		     cert.setEmail(email);////放入参数邮箱
		     cert.setCertification_status(0);//默认给用户添加一个未认证状态
		     cert.setAudittime(new Date());
		     int i=service.insertCerti(cert);
		     if(i>0){
		    	/* Certification certr=new Certification();//创建对象
		    	 certr.setCertification_status(1);
		    	 certr.setUser(new UserInfo(userid));
		    	 service.getcertification_statusByUserid(certr);*/
		    	 return new ResultDto(200,"提交资料成功");
		     }else{
		    	 return new ResultDto(2014,"提交资料失败");
		     }
		 }else{
			 return new ResultDto(2002,"请求参数为空！");
		 }
	}
}
