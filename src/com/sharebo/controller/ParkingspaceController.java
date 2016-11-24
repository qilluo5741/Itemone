package com.sharebo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.ResultDto;
import com.sharebo.entity.Dto.ParkingDto;
import com.sharebo.entity.Dto.ParkingdetailDto;
import com.sharebo.service.ParkingspaceService;
import com.sharebo.util.Pager;

@RestController
@RequestMapping("{sign}/parkingspace")
public class ParkingspaceController {
	@Autowired
	private ParkingspaceService service;
	/**
	 * 通过小区id分页查询车位 
	 */
	@RequestMapping("pagerSpark")
	public ResultDto pagerSpark(String communityId,int pageIndex, int pageSize) {
		if (pageIndex < 1 || pageSize == 0 || communityId == null) {
			return new ResultDto(4002, "参数不合法！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int pageBegin = (pageIndex - 1) * pageSize;
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pageSize);
		map.put("communityId", communityId);
		List<ParkingDto> list = service.pagertbyList(map);
		//设置车位状态  根据车位id去查询
		for (ParkingDto parkingDto : list) {
			//替换手机号
			parkingDto.setMobile(parkingDto.getMobile().substring(0,3)+"****"+parkingDto.getMobile().substring(7,11));
			/*if(parkingDto.getParkstate()==0){//0为空闲  1为繁忙
				//查询星期规则
				
			}*/
		}
		Pager<ParkingDto> p = new Pager<ParkingDto>();
		p.setPageSize(pageSize);
		p.setPageIndex(pageIndex);
		p.setTotalRecords(service.pagerbyCount());
		p.setTotalPages();// 设置总页数
		// 设置数据
		p.setList(list);
		return new ResultDto(200, p);
	}
	/**
	 * 通过车位id查询车位详情（车位状态、收费类型、费用、说明、类型、入口地址、是否收藏）
	 */
	@RequestMapping("selectBypid")
	public ResultDto selectBypid(String parkid){
		if (parkid==null || parkid .equals("")) {
			return new ResultDto(4002, "参数不合法！");
		}
		ParkingdetailDto p = service.selectById(parkid);
		Map<String, Object> map  =new HashMap<String, Object>();
		map.put("parkid", parkid);
		int c = service.iscollect(map);
		p.setIs_collect(c);
		if(p!=null){
			return new ResultDto(200,p);
		}
		else{
			return new ResultDto(4001, "获取值异常");
		}
	}
}
