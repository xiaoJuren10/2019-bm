package cn.bluemobi.controller.app;


import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bluemobi.controller.AppController;
import cn.bluemobi.util.sms.HttpClientUtil;
@Scope(value="prototype")
@Controller(value="LoginController")
@RequestMapping("/app/login")
public class LoginController extends AppController  {
	
	/**
	 * 发送短信验证码
	 * @param cellphone
	 */
	@RequestMapping("/getSmsCode.htm")
	public void getSmsCode(@RequestParam(value="cellphone",required=true) String cellphone) {
		Map<String,Object>resultMap=new HashMap<String, Object>();
		resultMap.put("result","1"); 
		//1.随机生成一个验证码
		int smsCode=(int)(Math.random()*10000);
		//2.判断是否存在
		//1）存在
		//2）不存在
		int result=HttpClientUtil.sendSMS(cellphone,"验证码为："+smsCode+",一分钟内有效");
		if(result>0) {
			//短信发送成功，将短信验证码存储到数据库中去
		}else {
	     	resultMap.put("status","0");
	
		}
		outJSON(resultMap);
		
	}
}
