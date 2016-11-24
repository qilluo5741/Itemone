package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Dto.ParkingDto;
import com.sharebo.entity.Dto.ParkingdetailDto;
import com.sharebo.mapper.parkingspaceMapper;
import com.sharebo.service.ParkingspaceService;

@Service
public class ParkingspaceServiceImpl implements ParkingspaceService {
	@Autowired
	private parkingspaceMapper mapper;

	public int pagerbyCount() {
		return mapper.pagerbyCount();
	}

	public List<ParkingDto> pagertbyList(Map<String, Object> map) {
		return mapper.pagertbyList(map);
	}

	public ParkingdetailDto selectById(String pid) {
		return mapper.selectById(pid);
	}

	public int iscollect(Map<String, Object> map) {
		return mapper.iscollect(map);
	}

}
