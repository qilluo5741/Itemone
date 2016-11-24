package com.sharebo.util;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Decoder;
/**
 * 文件写入
 *
 */
public class FileUtil {
	/***
	 * 保存文件 写入本地
	 * @param fileCode 经过base64加密的字符码
	 * @param req HttpServletRequest对象
	 * @param filefolder 保存图片的文件夹
	 * @return 图片名字  通过uuid命名
	 */
	public static String uploadImg(String fileCode,HttpServletRequest req,String filefolder){
		//项目根目录
		String path=req.getSession().getServletContext().getRealPath("/")+filefolder+"\\";
		//验证文件夹是否存在，不在就创建
		File f=new File(path);
		if(!f.exists()){
			f.mkdir();//创建
		}
		//写入文件
		String imgName=UUID.randomUUID().toString()+".jpg";
		 try {
			byte[] buffer = new BASE64Decoder().decodeBuffer(fileCode);
			FileOutputStream out = new FileOutputStream(path+"\\"+imgName);
		    out.write(buffer);
		    out.close();
		} catch (Exception e) {
			return  null;
		}
		return imgName;
	}

}
