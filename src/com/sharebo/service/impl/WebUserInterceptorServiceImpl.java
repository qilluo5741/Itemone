package com.sharebo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.mapper.WebUserInterceptorMapper;
import com.sharebo.service.WebUserInterceptorService;
@Service
public class WebUserInterceptorServiceImpl implements WebUserInterceptorService {
	@Autowired
	private WebUserInterceptorMapper mapper;
	public boolean valMenuPermissions(Map<String,String> map) {
		return mapper.valMenuPermissions(map)>0?true:false;
	}
	public int valHrefIsExist(String href) {
		// TODO Auto-generated method stub
		return mapper.valHrefIsExist(href);
	}
	
}
