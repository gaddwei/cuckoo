package cn.cuckoo.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cuckoo.core.dao.CoreDAO;
import cn.cuckoo.core.entity.District;
import cn.cuckoo.util.Util;

@Service
public class ProCityService {
	
	@Resource
	private CoreDAO dao;
	
	public List<District> getProCityData(String level,String upid) throws GeneralException{
		if(!Util.isNotNull(level)){
			throw new GeneralException("数据加载错误请重新刷新网页。");
		}
		level = level.trim();
		if(!level.matches("^\\d$")){
			throw new GeneralException("数据加载错误请重新刷新网页。");
		}
		if(!Util.isNotNull(upid)){
			throw new GeneralException("数据加载错误请重新刷新网页。");
		}
		upid = upid.trim();
		if(!upid.matches("^\\d{0,8}$")){
			throw new GeneralException("数据加载错误请重新刷新网页。");
		}
		District district = new District();
		district.setLevel(Integer.valueOf(level));
		district.setUpid(Integer.valueOf(upid));
		List<District> districts =  dao.getDistrictByLevel(district);
		return districts;
	}
}
