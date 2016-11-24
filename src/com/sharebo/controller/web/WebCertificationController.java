package com.sharebo.controller.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.Dto.CertificationDto;
import com.sharebo.service.CertificationService;
import com.sharebo.util.Pager;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("web/Certification")
public class WebCertificationController {
	@Autowired
	private CertificationService service;
	/**
	 * 分页查询
	 * @param mp
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("Certification")
	public String getCertificationDtoByPager(ModelMap mp,Integer pageIndex){
			Pager<CertificationDto> pager=new Pager<CertificationDto>();
			pager.setPageIndex((pageIndex==null?1:pageIndex));
			pager.setPageSize(10);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pager.getPageIndex()-1)*pager.getPageSize();
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pager.getPageSize());
			//查询集合
			pager.setList(service.getCertification(map));
			//查询总数
			pager.setTotalRecords(service.pagerCertificationcount());
			pager.setTotalPages();//设置总页数
			mp.addAttribute("pager", pager);
			return "permission/CertificationManager";
	}
	/**
	 * 审核车主
	 */
	@RequestMapping("updateCertificationId")
	public @ResponseBody String getCertification(String certificationId,ModelMap mp){
		try {
			Date date=new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String thistime = dateFormat.format(date);
			CertificationDto cer=new CertificationDto();
			cer.setCertification_status(1);
			cer.setThistime(thistime);
			cer.setCertificationId(certificationId);
			if(service.updateCertification(cer)>0){
				return "0";
			}else{
				return "1";
			}
		} catch (Exception e) {
			return "2";
		}
	}
	@RequestMapping("updateCertificationIds")
	public @ResponseBody String getCertifications(String certificationId,ModelMap mp){
		try {
			Date date=new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String thistime = dateFormat.format(date);
			CertificationDto cer=new CertificationDto();
			cer.setCertification_status(2);
			cer.setThistime(thistime);
			cer.setCertificationId(certificationId);
			if(service.updateCertification(cer)>0){
				return "0";
			}else{
				return "1";
			}
		} catch (Exception e) {
			return "2";
		}
	}
}

