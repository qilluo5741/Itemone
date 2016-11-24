package com.sharebo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Certification;
import com.sharebo.entity.Vehicle;
import com.sharebo.entity.Dto.VehicleDto;
import com.sharebo.mapper.VehicleMapper;
import com.sharebo.service.VehicleService;
/**
 * 车辆表
 * @author zhangke
 */
@Service
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleMapper mapper;//车辆接口
	/**
	 * 根据vehicleid删除数据
	 */
	public int delectVehicle(String vehicleid) {
		return mapper.delectVehicle(vehicleid);
	}
	/**
	 * 根据userid添加车牌
	 */
	public int insertVehicle(Vehicle vehicle) {
		return mapper.insertVehicle(vehicle);
	}
	/**
	 * 删除前删除
	 */
	public int count(String vehicleid) {
		return mapper.count(vehicleid);
	}
	/**
	 * 查询车牌号
	 * @param userid
	 * @return
	 */
	public List<VehicleDto> getvehicleno(String userid) {
		return mapper.getvehicleno(userid);
	}
	/**
	 * 插入车主认证信息
	 * @return
	 */
	public int insertCerti(Certification cercation) {
		return mapper.insertCerti(cercation);
	}
	/**
	 * 查询是否被认证
	 */
	public int selectCertific(String userid) {
		return mapper.selectCertific(userid);
	}
	/**
	 * 修改认证
	 */
	public int getcertification_statusByUserid(Certification Certi) {
		return mapper.getcertification_statusByUserid(Certi);
	}
}
