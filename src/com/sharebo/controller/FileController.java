package com.sharebo.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import com.sharebo.config.ResultConfig;
import com.sharebo.service.FileService;
import com.sharebo.util.FileUtil;

/**
 * 关于上传文件的操作
 * @author niewei
 *
 */
@RestController
@RequestMapping("/{timestamp}/upload")
public class FileController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	 private FileService fileService;
	/**
	 * 测试   ----待完善
	 * @param mobile 手机号码
	 * @param file 图片 通过base64加密byte数组  调用需要进行URLEncoder.encode处理
	 * @return 返回状态
	 */
	@RequestMapping(value="updateHeadPortrait",method=RequestMethod.POST)
	public Map<String,Object> updateHeadPortrait(String mobile,String file) throws IOException{
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//判断文件参数是否传递
		if(file==null){
			resultMap.put(ResultConfig.STATE,-1);//文件不能为空
		}
		else{
			String imgName=FileUtil.uploadImg(file, request,"headImage");
			System.out.println(imgName+"文件名字");
			resultMap.put(ResultConfig.STATE,200);//文件上传成功
		}
		return resultMap;
	}
	/**
	 * 文件操作 测试
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="updateTest",method=RequestMethod.POST)
	public Map<String,Object> updateImage(String file) throws IOException{
		Map<String,Object> resultMap=new HashMap<String,Object>();
		//判断文件参数是否传递
		if(file==null){
			resultMap.put(ResultConfig.STATE,-1);//文件不能为空
		}
		else{
			byte[] buffer = new BASE64Decoder().decodeBuffer(file);
			ByteArrayInputStream is = new ByteArrayInputStream(buffer);
			if (file.isEmpty()) {
			    System.out.println("不是文件！");
			}
			System.out.println("文件的访问路径是：：：：：："+this.fileService.uploadImage(is));
			//System.out.println("文件的路径是："+this.fileService.getUrl("image","7e47d6c9-9c5e-4f92-8d06-c9d0bbcbc47c"));
			resultMap.put(ResultConfig.STATE,200);//文件上传成功
		}
		return resultMap;
	}
}
