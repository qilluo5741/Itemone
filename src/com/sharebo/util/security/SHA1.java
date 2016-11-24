
package com.sharebo.util.security;
import java.security.MessageDigest;

import com.sharebo.util.Log;
/**
* <p>Title: SHA1算法</p>
*
* @author niewei
*/
public final class SHA1 {
	private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5','6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	/**
	* 需要原始字节的转化和格式正确。
	*
	* @param 字节的原始字节的转化
	* @return 格式化的字节
	*/
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
		buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
		buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			Log.getInstance().error(str+"加密失败！");
			return null;
		}
	}
}