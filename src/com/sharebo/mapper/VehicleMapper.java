package com.sharebo.mapper;

import java.util.List;

import com.sharebo.entity.Certification;
import com.sharebo.entity.Vehicle;
import com.sharebo.entity.Dto.VehicleDto;

/**
 * 车辆表
 * @author zhangke
 *
 */
public interface VehicleMapper {
	/**
	 * 插入
	 * @return
	 */
	public int insertVehicle(Vehicle vehicle);
	/**
	 * 删除前删除
	 * @param vehicleid
	 * @return
	 */
	public int count(String vehicleid);
	/**
	 * 删除
	 */
	public int delectVehicle(String vehicleid);
	/**
	 * 查询车牌号
	 * @param userid
	 * @return
	 */
	public List<VehicleDto> getvehicleno(String userid);
	/**
	 * 插入车主认证信息
	 * @return
	 */
	public int insertCerti(Certification cercation);
	/**
	 * 查询是否被认证
	 */
	public int selectCertific(String userid);
	/**
	 * 修改车主认证
	 */
	public int getcertification_statusByUserid(Certification Certi);
}
