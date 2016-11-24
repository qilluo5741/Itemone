package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Dto.ManagementDto;
import com.sharebo.mapper.WebManagementMapper;
import com.sharebo.service.WebManagementService;
@Service
public class WebManagementServiceImpl implements WebManagementService {
	@Autowired
	private WebManagementMapper mapper;

	public List<ManagementDto> getManagementisnotPager(Map<String, Object> map) {
		return mapper.getManagementisnotPager(map);
	}

	public int getManagementnotcount(String mobile) {
		return mapper.getManagementnotcount(mobile);
	}

	public List<ManagementDto> getManagementPager(Map<String, Object> map) {
		return mapper.getManagementPager(map);
	}

	public int getManagementcount() {
		return mapper.getManagementcount();
	}
}
