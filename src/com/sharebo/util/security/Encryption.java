package com.sharebo.util.security;
import java.io.IOException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 双重加密；解密
 * @author niewei
 *
 */
public class Encryption {
	/**
	 * 双重加密
	 * @param str 加密字符串
	 * @return 已经加密的字符串
	 */
	public static String encoder(String str){
		//加密第一次
		BASE64Encoder encoder_1 = new BASE64Encoder();
		//加密第二次
		BASE64Encoder encoder_2 = new BASE64Encoder();
		//返回加密结果
		return encoder_2.encode(encoder_1.encode(str.getBytes()).getBytes());
	}
	/**
	 * 双重解密
	 * @param str已经加密的字符串
	 * @return 加密之前的字符串
	 */
	public static String decoder(String str){
		BASE64Decoder decoder_2 = new BASE64Decoder();
		BASE64Decoder decoder_1 = new BASE64Decoder();
		String decoder=null;
		try {
			decoder=new String(decoder_1.decodeBuffer(new String(decoder_2.decodeBuffer(str.toString()))));
		} catch (IOException e) {
			com.sharebo.util.Log.getInstance().warn("解密异常！");
		}
		return decoder;
	}
	/**
	 * 字符串 与加密的字符串比较  
	 * @param str未加密的字符串
	 * @param encode 已加密的字符串
	 * @return true匹配成功  false匹配失败
	 */
	public static boolean stringTocompareEncoderStr(String str,String encode){
		//加密字符串 //匹配
		if(encoder(str).equals(encode)){
			return true;
		}
		return false;
	}
	/**
	 * 两个字符串比较；两个加密密码比较
	 * @param str1 字 符串1
	 * @param str2 字符串2
	 * @return 是否比配
	 */
	public static boolean strTocompareStr(String str1,String str2){
		return str1.equals(str2);
	}
}
