package com.sharebo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sharebo.service.TouchBalanceService;
/**
 * @author zhangke
 */
@RestController
@RequestMapping("{sign}/Message")
public class TouchBalanceController {
	@Autowired
	public TouchBalanceService service;
}
