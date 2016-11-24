package com.sharebo.service;

import java.util.List;
import java.util.Map;

import com.sharebo.entity.OrderInfo;
import com.sharebo.entity.Purse;
import com.sharebo.entity.Dto.GongOrder;
import com.sharebo.entity.Dto.OrderDto;
import com.sharebo.entity.Dto.OrderTimeDto;
import com.sharebo.entity.Dto.Times;
import com.sharebo.entity.Dto.TwoOrder;
import com.sharebo.entity.Dto.XuOrder;

public interface OrderService {
	/**
	 * 验证该订单时间是否可以预约,通过验证车位时间
	 * @param list
	 * @param parkid
	 * @return
	 */
	public List<String> valOrderTime(List<OrderTimeDto> list,String parkid);
	/**
	 * 通过车位id和一组预约时间验证是否与已经预约的时间有交集
	 * @param list
	 * @param parkid
	 * @return
	 */
	public List<String> valOtherTime(List<OrderTimeDto> list,String parkid);
	/**
	 * 添加订单
	 * @param list
	 * @param order
	 * @param orderId
	 * @return
	 */
	public boolean addOrder(List<OrderTimeDto> list,OrderInfo order,String orderId);
	/**
	 * 验证车位是否存在
	 * @param parkid
	 * @return
	 */
	public int valParkIsExistsByparkid(String parkid);
	/**
	 * 验证时间是否合法
	 * @param list
	 * @return
	 */
	public boolean valOrdetimeisoverdue(List<OrderTimeDto> list);
	/**
	 * 修改订单状态为已取消
	 * @param orderid
	 * @return
	 */
	public int updateOrderState(String orderid);
	/**
	 * 查询全部未支付的订单
	 * @return
	 */
	public List<com.sharebo.entity.Dto.OrderCancelDto> getOrderInfoByDisNotPay();
	/**
	 * 根据订单id 查询订单详情
	 * @param orderid
	 * @return
	 */
	public  com.sharebo.entity.Dto.OrderInfoDto getOrderInfo(String orderid);
	/**
	 * 根据订单id 查询订单时间表
	 * @param orderid
	 * @return
	 */
	public List<com.sharebo.entity.Dto.OrderTimeGetOrderDto> getOrderTimeInfo(String orderid);
	/**
	 * 需方：根据userid分页查询我的订单（每条订单的信息：小区名字、时间段（多条），订单状态，费用等）
	 * @param map
	 * @return
	 */
	public List<XuOrder> qureyXuOrder(Map<String,Object> map);
	/**
	 * 供方：根据userid分页查询我的车位id,然后通过车位id查询订单表中关于我的订单 去除未付款的订单。
	 * @param map
	 * @return
	 */
	public List<GongOrder> queryGongOrder(String orderid);
	/**
	 * 根据userid查询最近的两个订单信息
	 * @param userid
	 * @return
	 */
	public List<TwoOrder> queryTwoOrder(String userid);
	/**
	 * 根据用户的id 查看订单时间段
	 * @param orderid
	 * @return
	 */
	public List<Times> queryTimes(String orderid);
	/**
	 * xu总数
	 * @param parkid
	 * @return
	 */
	public int pagerbyXuCount(String orderid);
	/**
	 * gong总数
	 */
	public int pagerbyGongCount(String parkid);
	/**
	 * 通过userid查看停车场id
	 */
	public String queryByUserid(String userid);
	/**
	 * 根据userid查看订单表的parkid
	 * @param userid
	 * @return
	 */
	public List<String> queryParkid(String userid);
	/**
	 * 查询订单号用户id
	 */
	public String getOrdernumByUserid(String ordernum);
	/**
	 * 修改订单状态
	 * @param Order
	 * @return
	 */
	public int updateOrderInfoByordernum(OrderInfo Order);
	/***
	 * 通过订单号码查询订单id
	 * @param ordernum
	 * @return
	 */
	public String getOrderIdByOrderNum(String ordernum);
	/**
	 * 修改接受拒绝状态
	 * @param Order
	 * @return
	 */
	public int updatestateByordernum(OrderInfo Order);
	/**
	 *  查询订单id查询需要退款的的金额和查询用户id
	 */
	public OrderDto getorderidByorder(String orderid);
	/**
	 * 退还用户支付的金额
	 * @param orderid
	 * @return
	 */
	public int updateBybalances(Purse purse);
	/**
	 * 查询车牌
	 * @param ordernum
	 * @return
	 */
	public String getvehicleidByOrderNum(String ordernum);
	/**
	 * 查询手机号码
	 * @param ordernum
	 * @return
	 */
	public String getmobileByOrderNum(String ordernum);
	/**
	 * 根据订单主键id查询userid
	 * @param orderid
	 * @return
	 */
	public String getmobileByOrderid(String orderid);
}


