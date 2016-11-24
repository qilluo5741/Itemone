package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Dto.CertificationDto;
import com.sharebo.mapper.CertificationMapper;
import com.sharebo.service.CertificationService;
@Service
public class CertificationServiceImpl implements CertificationService {
	@Autowired
	private CertificationMapper mapper;
	/**
	 * 分页查询
	 */
	public List<CertificationDto> getCertification(Map<String, Object> map) {
		return mapper.getCertification(map);
	}
	/**
	 * 总条数
	 */
	public int pagerCertificationcount() {
		return mapper.pagerCertificationcount();
	}
	//车主认证
	public int updateCertification(CertificationDto certification){
		return mapper.updateCertification(certification);
	}
}
