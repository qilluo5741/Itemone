package com.sharebo.controller.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharebo.entity.Dto.ManagementDto;
import com.sharebo.service.WebManagementService;
import com.sharebo.util.Pager;

/**
 * web车位管理
 * @author zhangke
 */
@Controller
@RequestMapping("web/manage")
public class WebManagementController {
	@Autowired
	private WebManagementService service;

	@RequestMapping("getManagement")
	public  String getUserInfoByisnotPager(ModelMap model,Integer pageIndex,String key,String creabeegin,String creaend,String is_delete,String chargetype){
			try {
				Pager<ManagementDto> pager=new Pager<ManagementDto>();
				pager.setPageIndex((pageIndex==null?1:pageIndex));
				pager.setPageSize(10);
				Map<String, Object> map=new HashMap<String, Object>();
				//设置开始
				int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
				map.put("pageBegin", pageBegin);
				map.put("pageSize", pager.getPageSize());
				//设置开始
				map.put("mobile",key);
				map.put("is_delete",is_delete);
				map.put("chargetype",chargetype);
				map.put("date_beegin",creabeegin);
				map.put("date_end",creaend);
				if(key==null || key.equals("")){
					//查询集合
					pager.setList(service.getManagementPager(map));
					//查询总数
					pager.setTotalRecords(service.getManagementcount());
					System.out.println(pager.getTotalRecords());
					pager.setTotalPages();//设置总页数
					model.addAttribute("pager",pager);
					return "permission/ManagementManager";
				}else{
					//查询集合
					pager.setList(service.getManagementisnotPager(map));
					//查询总数
					pager.setTotalRecords(service.getManagementnotcount(key));
					pager.setTotalPages();//设置总页数
					model.addAttribute("pager", pager);
					return "permission/ManagementManager";
				}
			} catch (Exception e) {
				return "permission/ManagementManager";
			}
	}
}
