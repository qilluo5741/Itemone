package com.sharebo.mapper;


import java.util.List;
import java.util.Map;

import com.sharebo.entity.Purse;
import com.sharebo.entity.TouchBalance;
import com.sharebo.entity.WithdrawalRecord;

/**
 * 我的钱包
 * @author gyf
 *
 */
public interface PurseMapper {
	/**
	 * 根据用户编号查询余额和冻结余额
	 * @param userid
	 * @return
	 */
	public Purse getPurseByUserid(String userid);
	/**
	 * 分页查询提现记录
	 * @param pager
	 * @return
	 */
	public List<WithdrawalRecord> pagertbyList(Map<String,Object> map);
	public int pagerbyCount(String userid);//总数
	/**
	 * 分页查询余额明细
	 * @param map
	 * @return
	 */
	public List<TouchBalance> pagerTouchBalance(Map<String,Object> map);
	public int pagerCountTouchBalance(String userid);//总数
	//更新钱包的金额
	public int updatePurse(Map<String,Object> map);
	//添加提现记录
	public int createWR(WithdrawalRecord wr);
	//添加交易记录
	public int createTB(TouchBalance tb);
	
	/**
	 * 查询订单号查询需要支付的金额用户id
	 */
	public double getOrdernumBymoney(String ordernum);
	/**
	 * 查询用户id查询余额
	 */
	public double getPurseBybalance(String userid);
	/**
	 * 查询是否该订单已经支付，防止重复扣取费用
	 * @param ordernum
	 * @return
	 */
	public int getorder_state(String ordernum);
	/**
	 * 扣取用户支付的金额
	 * @param orderid
	 * @return
	 */
	public int updateBybalance(Purse purse);
}
