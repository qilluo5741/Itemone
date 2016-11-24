package com.sharebo.service;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Dto.CertificationDto;

public interface CertificationService {
	/**
	 * 查询车主
	 * @param map
	 * @return
	 */
	public List<CertificationDto> getCertification(Map<String,Object> map);
	public int pagerCertificationcount();//总数
	//车主认证
	public int updateCertification(CertificationDto certification);
}
