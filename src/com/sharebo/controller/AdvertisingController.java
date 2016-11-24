package com.sharebo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sharebo.entity.Advertising;
import com.sharebo.entity.ResultDto;
import com.sharebo.service.AdvertisingService;

@RestController
@RequestMapping("{sign}/advertising")
public class AdvertisingController {
	@Autowired
	public AdvertisingService service;

	@RequestMapping("/json-advertion")
	public ResultDto selectAdvertising() {
		List<Advertising> advertis = service.selectAdvertising();
		if (advertis == null) {
			return new ResultDto(3001, "查询失败");
		}
		return new ResultDto(200,advertis);
	}

}