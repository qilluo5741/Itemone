package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.Dto.CertificationDto;

public interface CertificationMapper {
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<CertificationDto> getCertification(Map<String,Object> map);
	public int pagerCertificationcount();//总数
	//车主认证
	public int updateCertification(CertificationDto certification);
}
