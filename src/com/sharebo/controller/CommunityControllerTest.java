package com.sharebo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sharebo.service.CommunityService;

@RestController
@RequestMapping("{sign}/Community")
public class CommunityControllerTest {
	@Autowired
	public CommunityService service;
	/**
	 * 高德地图同步小区
	 * http://localhost:8080/sharebo/123456/Community/updateaction.do
	 * @return
	 */
	/*@RequestMapping("/updateaction")
	public ResultDto selectAdvertising() {
		try {
			List<Community> list = service.selectCommunityAll();
			for (Community community : list) {
				String community_name=community.getCommunity_name();
				String community_address=community.getCommunity_address();
				String communityId=community.getCommunityId();
				String key="14607db53fff73bc7ed6e611a6246fc1";
				String tableid="5704b9ee305a2a034b09bbfc";
				String httpUrl="http://yuntuapi.amap.com/datamanage/data/create";
				String httpArg="key="+key+"&tableid="+tableid+"&loctype=2&data={\"_name\":\""+community_name+"\",\"_address\":\""+community_address+"\",\"communityId\":\""+communityId+"\"}";
				System.out.println(community_name);
				String res=HttpUtil.request_post(httpUrl, httpArg);
				System.out.println(res);
				int id=JSONObject.fromObject(res).getInt("_id");
				System.out.println(id);
				Community comm=new Community();
				comm.setCommunityId(communityId);
				comm.setGaodeId(id);
				service.updateCommunityGaoid(comm);
			}
			return new ResultDto(200,true);
		} catch (Exception e) {
			return new ResultDto(200,false);
		}
	}*/
}