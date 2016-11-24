package com.sharebo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.Purse;
import com.sharebo.entity.ResultDto;
import com.sharebo.entity.TouchBalance;
import com.sharebo.entity.UserInfo;
import com.sharebo.entity.WithdrawalRecord;
import com.sharebo.service.PurseService;
import com.sharebo.util.Pager;

@RestController
@RequestMapping("{sign}/purse")
public class PurseController {
	@Resource
	private PurseService service;

	/**
	 * 根据用户的id查看可用余额和冻结余额
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping("getPurseByUserid")
	public ResultDto getPurseByUserid(String userid) {
		if (userid == null) {
			return new ResultDto(4002,"参数不能为空");
		}
		Purse p = service.getPurseByUserid(userid);
		if (p != null) {
			return new ResultDto(200, p);
		} else {
			return new ResultDto(4001, "获取值异常");
		}
	}

	/**
	 * 分页显示提现记录
	 */
	@RequestMapping("pagelist")
	public ResultDto pagelist(int pageIndex, int pageSize, String userid) {
		if (pageIndex < 1 || pageSize == 0 || userid == null) {
			return new ResultDto(4002, "参数不合法！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int pageBegin = (pageIndex - 1) * pageSize;
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pageSize);
		map.put("userid", userid);
		List<WithdrawalRecord> list = service.pagertbyList(map);
		Pager<WithdrawalRecord> p = new Pager<WithdrawalRecord>();
		p.setPageSize(pageSize);
		p.setPageIndex(pageIndex);
		p.setTotalRecords(service.pagerbyCount(userid));
		p.setTotalPages();// 设置总页数
		// 设置数据
		p.setList(list);
		return new ResultDto(200, p);
	}

	/**
	 * 分页显示交易记录
	 */
	@RequestMapping("pagelistTB")
	public ResultDto pagelistTB(int pageIndex, int pageSize, String userid) {
		if (pageIndex < 1 || pageSize == 0 || userid == null) {
			return new ResultDto(4002, "参数不合法！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Purse p = service.getPurseByUserid(userid);
		String purseid = p.getPurseid();
		int pageBegin = (pageIndex - 1) * pageSize;// 起始行
		map.put("pageBegin", pageBegin);
		map.put("pageSize", pageSize);// 条数
		map.put("purseid", purseid);
		List<TouchBalance> list = service.pagerTouchBalance(map);
		Pager<TouchBalance> t = new Pager<TouchBalance>();
		t.setPageSize(pageSize);
		t.setPageIndex(pageIndex);
		t.setTotalRecords(service.pagerCountTouchBalance(purseid));
		t.setTotalPages();// 设置总页数
		// 设置数据
		t.setList(list);
		return new ResultDto(200, t);
	}

	/**
	 * 钱包提现
	 */
	@RequestMapping("tixian")
	public ResultDto tixian(String userid, double money) {
		if (userid == null && money <= 0 ) {
			return new ResultDto(4002, "参数不合法！");
		}
		Purse p = service.getPurseByUserid(userid);
		if (p != null) {
			double m = p.getBalance();
			if (m < money) {
				return new ResultDto(4003, "余额不足");
			}
			// 创建uuid
			String Id = UUID.randomUUID().toString().replaceAll("-", "");
			// 更新钱包
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("balance", money);
			map.put("userid", userid);
			int r = service.updatePurse(map);
			// 添加提现记录
			WithdrawalRecord wr = new WithdrawalRecord();
			wr.setWrid(Id);
			wr.setUser(new UserInfo(userid));
			wr.setWithdrawal_money(money);
			int r1 = service.createWR(wr);
			if (r > 0 && r1 > 0) {
				return new ResultDto(200, "提现申请成功！");
			} else {
				return new ResultDto(4001, "获取值异常");
			}
		}else {
			return new ResultDto(4001, "获取值异常");
		}
	}
}
