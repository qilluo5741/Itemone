package com.sharebo.controller.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sharebo.entity.Dto.OwnerorderDto;
import com.sharebo.service.OwnerorderService;
import com.sharebo.util.Pager;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("web/Ownerorder")
public class WebOwnerorderController {
	@Autowired
	private OwnerorderService service;
	
	/**
	 * 分页查询
	 * @param mp
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("Ownerorder")
	public String getOwnerorderByPager(ModelMap mp,Integer pageIndex,String key,String order_state,String payType){
		if(key==null || key.equals("")){
			Pager<OwnerorderDto> pager=new Pager<OwnerorderDto>();
			pager.setPageIndex((pageIndex==null?1:pageIndex));
			pager.setPageSize(10);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pager.getPageSize());
			map.put("order_state", order_state);
			map.put("payType", payType);
			//查询集合
			pager.setList(service.getOwnerorderByAllPager(map));
			//查询总数
			pager.setTotalRecords(service.getOwnerorderAll());
			pager.setTotalPages();//设置总页数
			mp.addAttribute("pager", pager);
			return "permission/OwnerorderManager";
		}else{
			Pager<OwnerorderDto> pager=new Pager<OwnerorderDto>();
			pager.setPageIndex((pageIndex==null?1:pageIndex));
			pager.setPageSize(10);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pager.getPageSize());
			map.put("userName",key);
			map.put("mobile",key);
			//查询集合
			pager.setList(service.getOwnerorderByPager(map));
			//查询总数
			pager.setTotalRecords(service.getOwnerorder());
			pager.setTotalPages();//设置总页数
			mp.addAttribute("pager", pager);
			return "permission/OwnerorderManager";
		}
	}
}

