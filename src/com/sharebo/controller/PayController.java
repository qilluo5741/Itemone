package com.sharebo.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.config.WeixinConfig;
import com.sharebo.entity.OrderInfo;
import com.sharebo.entity.Purse;
import com.sharebo.entity.ResultDto;
import com.sharebo.entity.TouchBalance;
import com.sharebo.entity.UserInfo;
import com.sharebo.entity.WeixinResult;
import com.sharebo.service.OrderService;
import com.sharebo.service.PurseService;
import com.sharebo.service.TouchBalanceService;
import com.sharebo.util.AlipayNotify;
import com.sharebo.util.Log;
import com.sharebo.util.SmsSendClientExample;
import com.sharebo.util.WeixinUtil;
import com.sharebo.util.security.MD5Util;
/**
 *  @author zhangke
 */
@Controller
@RequestMapping("{sign}/pay")
public class PayController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private OrderService orderservice;
	@Autowired
	private TouchBalanceService touchService;
	@Autowired
	private PurseService purseService;
	/**
	 * 余额支付
	 */
	@RequestMapping(value="purse")
	public @ResponseBody ResultDto purseNotice(String out_trade_no){
		if(out_trade_no==null){
			return new ResultDto(2002,"请求参数为空！");
		}
		/**
		 * 根据订单号查询用户id
		 */
		String userid=orderservice.getOrdernumByUserid(out_trade_no);
		Double money=purseService.getOrdernumBymoney(out_trade_no);//需要支付的金额
		Double balance=purseService.getPurseBybalance(userid);//用户账户余额
		if(purseService.getorder_state(out_trade_no)>0){
			if(balance>=money){
				double balances=balance-money;//计算金额
				Purse purse=new Purse();
				purse.setBalance(balances);
				purse.setUser(new UserInfo(userid));
				//扣取用户支付的金额
				int b=purseService.updateBybalance(purse);
				System.out.println(b);
				/**
				 * 根据订单号修改支付状态
				 */
				OrderInfo Order=new OrderInfo();
				Order.setPayment_time(new Date());//支付时间
				Order.setPayType(0);//支付类型（0“余额支付，1：支付宝，2：微信支付）
				Order.setOrder_state(1);//支付状态
				Order.setMoney(money);
				Order.setOrdernum(out_trade_no);
				int i=orderservice.updateOrderInfoByordernum(Order);//修改支付状态
				System.out.println(i);
				/**
				 * 根据用户id查询purseid
				 */
				String purseid =touchService.getpurseBypurseid(userid);
				//创建明细记录空对象
				TouchBalance touchbalance=new TouchBalance();
				String tbid=UUID.randomUUID().toString().replaceAll("-", "");;//创建uuid并且去掉含有-的	
				touchbalance.setTbid(tbid);
				touchbalance.setMoney(money);//回调中金额
				touchbalance.setOperation_time(new Date());//支付时间
				touchbalance.setTradtype(2);//0支付宝1微信2余额3退款
				touchbalance.setPurse(new Purse(purseid));
				int j=touchService.addTouchBalance(touchbalance);//添加余额明细记录
				System.out.println(j);
				String vehicleid=orderservice.getvehicleidByOrderNum(out_trade_no);
				System.out.println(vehicleid+"车牌号");
				String mobile =orderservice.getmobileByOrderNum(out_trade_no);
				System.out.println(mobile+"余额发送的手机号");
				SmsSendClientExample.sendMessage(mobile,vehicleid);
				return new ResultDto(200,"支付成功");
			}else{
				return new ResultDto(2019,"对不起！您的余额不足，不能支付！");
			}
		}else{
			return new ResultDto(2020,"订单已经失效！");
		}
	}
	/**
	 * 微信支付
	 * @return
	 */
	@RequestMapping(value="weixinNotice",method=RequestMethod.POST)
	public @ResponseBody String wxNotice(){
		//接受传过来的信息 并转换成键值对的集合
		Map<String, String> map;
		map = WeixinUtil.xmlToMap(request);
		String weixinSign=map.get("sign");
		String mySign = createSign(map);
		WeixinResult outRes=null;
		//验证签名
		if(weixinSign.equals(mySign)){//验证成功
			////////////////////////////////////可能需要的参数///////////////////////////////////////////
			//业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
			//错误代码	err_code	否	String(32)	SYSTEMERROR	错误返回的信息描述
			//总金额	total_fee	是	Int	100	订单总金额，单位为分
			//现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
			//微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
			//商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统的订单号，与请求一致。
			//商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
			//支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
			if(map.get("result_code").equals("SUCCESS")){
				//////////////////////////////////处理业务/////////////////////////////////////
				String out_trade_no=map.get("out_trade_no");//商户订单号
				String total_fee=map.get("total_fee");//订单金额
				  /////////////////////////////////////////////////////////////////////////////
				/**
				 * 根据订单号查询用户id
				 */
				String userid=orderservice.getOrdernumByUserid(out_trade_no);
				/**
				 * 根据用户id查询purseid
				 */
				String purseid =touchService.getpurseBypurseid(userid);
				//创建明细记录空对象
				TouchBalance touchbalance=new TouchBalance();
				String tbid=UUID.randomUUID().toString().replaceAll("-", "");;//创建uuid并且去掉含有-的	
				double cash_fee=Double.valueOf(total_fee);//转double
				touchbalance.setTbid(tbid);
				touchbalance.setMoney(cash_fee/100);//回调中金额
				touchbalance.setOperation_time(new Date());
				touchbalance.setTradtype(1);//0支付宝1微信2余额3退款
				touchbalance.setPurse(new Purse(purseid));
				/**
				 * 根据订单号修改支付状态
				 */
				OrderInfo Order=new OrderInfo();
				Order.setOrder_state(1);//支付状态
				Order.setPayment_time(new Date());//支付时间
				Order.setPayType(2);//支付类型（0“余额支付，1：支付宝，2：微信支付）
				Order.setMoney(cash_fee);
				Order.setOrdernum(out_trade_no);
				int i=orderservice.updateOrderInfoByordernum(Order);//修改支付状态
				int j=touchService.addTouchBalance(touchbalance);//添加余额明细记录
				System.out.println(i+j);
				String vehicleid=orderservice.getvehicleidByOrderNum(out_trade_no);
				System.out.println(vehicleid+"车牌号");
				String mobile =orderservice.getmobileByOrderNum(out_trade_no);
				System.out.println(mobile+"微信发送的手机号");
				SmsSendClientExample.sendMessage(mobile,vehicleid);
				/////////////////////////////////////////////////////////////////////////////
				System.out.println(out_trade_no);
				outRes=new WeixinResult("SUCCESS","签名验证成功！");
			}
			else{
				Log.getInstance().debug("微信支付通知：错误代码："+map.get("err_code")+"错误代码描述："+map.get("err_code_des"));
				outRes=new WeixinResult("FAIL","业务结果验证为失败！");
			}
		}
		else{
			//验证签名失败！
			Log.getInstance().error("签名验证失败！");
			outRes=new WeixinResult("FAIL","签名验证失败！");
		}	
		//相应微信是否接受成功
		return WeixinUtil.toXml(outRes);
	}
	/**
	 * 支付宝支付
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="alipayNotice",method=RequestMethod.POST)
	public @ResponseBody String alipayNotice(ModelMap map) throws UnsupportedEncodingException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap(); 
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]:valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//订单金额
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数
		if(AlipayNotify.verify(params)){
			//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				System.out.println(out_trade_no+"测试订单号是否得到____________________________________");
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				/**
				 * 根据订单号查询用户id
				 */
				String userid=orderservice.getOrdernumByUserid(out_trade_no);
				/**
				 * 根据用户id查询purseid
				 */
				String purseid =touchService.getpurseBypurseid(userid);
				//创建明细记录空对象
				TouchBalance touchbalance=new TouchBalance();
				String tbid=UUID.randomUUID().toString().replaceAll("-", "");;//创建uuid并且去掉含有-的
				touchbalance.setTbid(tbid);
				double cash_fee=Double.valueOf(total_fee);
				touchbalance.setMoney(cash_fee);//回调中金额--------------------------------
				touchbalance.setOperation_time(new Date());
				touchbalance.setTradtype(0);
				touchbalance.setPurse(new Purse(purseid));
				/**
				 * 根据订单号修改支付状态
				 */
				OrderInfo Order=new OrderInfo();
				Order.setOrder_state(1);//支付状态
				Order.setPayment_time(new Date());//支付时间
				Order.setPayType(1);//支付类型（0“余额支付，1：支付宝，2：微信支付）
				Order.setMoney(cash_fee);
				Order.setOrdernum(out_trade_no);
				int i=orderservice.updateOrderInfoByordernum(Order);//修改支付状态
				int j=touchService.addTouchBalance(touchbalance);//添加余额明细记录
				System.out.println(i+j);
				String vehicleid=orderservice.getvehicleidByOrderNum(out_trade_no);
				System.out.println(vehicleid+"车牌号");
				String mobile =orderservice.getmobileByOrderNum(out_trade_no);
				System.out.println(mobile+"支付宝手机号");
				SmsSendClientExample.sendMessage(mobile,vehicleid);
				//如果有做过处理，不执行商户的业务程序
				//注意：
				System.out.println(out_trade_no+"订单系统中查到该笔订单的详细____________________________________");
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}
			return "success";//请不要修改或删除
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			return "fail";
		}
	}
	@SuppressWarnings("unchecked")
	public static String createSign(Map<String,String> parameters){ 	
		StringBuffer sb = new StringBuffer(); 		
		Set es = parameters.entrySet();
		//所有参与传参的参数按照accsii排序（升序） 		
		Iterator it = es.iterator(); 	
		while(it.hasNext()) { 		
			Map.Entry entry = (Map.Entry)it.next(); 	
			String k = (String)entry.getKey(); 		
			Object v = entry.getValue(); 		
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) { 
				sb.append(k + "=" + v + "&");
				} 	
		}
		sb.append("key=" + WeixinConfig.APP_KEY); 	
		String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase(); 
		return sign; 	
	}
}