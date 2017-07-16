package cn.cuckoo.util;

import java.util.List;

import javax.annotation.Resource;

import cn.cuckoo.homepage.dao.HomePageDAO;
import cn.cuckoo.homepage.entity.HomeImg;

/**
 *  这是一个定时从数据中获取首页图片的数据
 * @author Administrator
 *
 */
public class DataConversionTask {
	
	@Resource
	private  HomePageDAO dao;
	
	public void execute(){
		System.out.println("execute..............");
		Util.sethomeImgList(null);
		List<HomeImg> homeImgList = dao.getHomeImgList(); 
		Util.sethomeImgList(homeImgList);
	}
	
	public void init(){
		Util.sethomeImgList(null);
		List<HomeImg> homeImgList = dao.getHomeImgList(); 
		Util.sethomeImgList(homeImgList);
	}
	
}
