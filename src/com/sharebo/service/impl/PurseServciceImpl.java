package com.sharebo.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sharebo.entity.Purse;
import com.sharebo.entity.TouchBalance;
import com.sharebo.entity.WithdrawalRecord;
import com.sharebo.mapper.PurseMapper;
import com.sharebo.service.PurseService;

@Service
public class PurseServciceImpl implements PurseService{
	@Resource
	private PurseMapper mapper;
	/**
	 * 根据用户编号查询余额和冻结余额
	 * @param userid
	 * @return
	 */
	public Purse getPurseByUserid(String userid) {
		return mapper.getPurseByUserid(userid);
	}
	public int pagerbyCount(String userid) {
		return mapper.pagerbyCount(userid);
	}
	public List<WithdrawalRecord> pagertbyList(Map<String, Object> map) {
		return mapper.pagertbyList(map);
	}
	public int pagerCountTouchBalance(String userid) {
		return mapper.pagerCountTouchBalance(userid);
	}
	public List<TouchBalance> pagerTouchBalance(Map<String, Object> map) {
		return mapper.pagerTouchBalance(map);
	}
	public int createTB(TouchBalance tb) {
		return mapper.createTB(tb);
	}
	public int createWR(WithdrawalRecord wr) {
		return mapper.createWR(wr);
	}
	public int updatePurse(Map<String,Object> map) {
		return mapper.updatePurse(map);
	}
	/**
	 * 根据订单号查询需要支付的金额
	 */
	public double getOrdernumBymoney(String ordernum) {
		return mapper.getOrdernumBymoney(ordernum);
	}
	/**
	 * 根据userid查询当前用户的余额
	 */
	public double getPurseBybalance(String userid) {
		return mapper.getPurseBybalance(userid);
	}
	/**
	 * 查询是否该订单已经支付，防止重复扣取费用
	 * @param ordernum
	 * @return
	 */
	public int getorder_state(String ordernum) {
		return mapper.getorder_state(ordernum);
	}
	/**
	 * 扣取用户支付的金额
	 * @param orderid
	 * @return
	 */
	public int updateBybalance(Purse purse) {
		return mapper.updateBybalance(purse);
	}
}
