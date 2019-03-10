package cn.bluemobi.controller.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bluemobi.controller.AppController;
import cn.bluemobi.service.AppCommunityService;

@Controller
@RequestMapping("/app/community")

public class CommunityController extends AppController {
	@Autowired
	private AppCommunityService appCommunityService;
	/**
	 * 创建圈子
	 * @param communityName
	 * @param communityType
	 * @param roler
	 * @param userId
	 * @return
	 */
	@RequestMapping("/createCommunity.htm")
	public void createCommunity(HttpServletResponse response,String communityName,int communityType,int roler,Long userId  ){
		Map<String,Object>paramMap=new HashMap<String, Object>();
		
		//1.校验数据的合理性
		paramMap.put("cName",communityName);
		paramMap.put("ct",communityType);
		paramMap.put("userId",userId);
		//2.添加调用圈子的业务层
		Map<String,Object>communityMap=appCommunityService.createCommunity(paramMap);
		//3.封装结果信息并反回
		
		outJSON(communityMap);
	}
	@RequestMapping("/test.htm")
    public @ResponseBody String test() {
        return "haha";
    }
}
