package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sharebo.entity.Dto.OwnerorderDto;
import com.sharebo.mapper.OwnerorderMapper;
import com.sharebo.service.OwnerorderService;
@Service
public class OwnerorderServiceImpl implements OwnerorderService {
	@Resource
	private OwnerorderMapper mapper;
	public int getOwnerorder() {
		return mapper.getOwnerorder();
	}

	public List<OwnerorderDto> getOwnerorderByPager(Map<String, Object> map) {
		return mapper.getOwnerorderByPager(map);
	}

	public int getOwnerorderAll() {
		return mapper.getOwnerorderAll();
	}

	public List<OwnerorderDto> getOwnerorderByAllPager(Map<String, Object> map) {
		return mapper.getOwnerorderByAllPager(map);
	}

}
