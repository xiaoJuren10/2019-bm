package cn.bluemobi.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.mapper.app.AppCommunityMapper;
import cn.bluemobi.service.AppCommunityService;
@Service
public class AppCommunityServiceImpl implements  AppCommunityService {
	@Autowired
	private AppCommunityMapper appCommunityMapper;
	public Map<String, Object> createCommunity(Map<String, Object> paramMap){
		appCommunityMapper.addCommunity(paramMap);
		return paramMap;
	}
	
}
