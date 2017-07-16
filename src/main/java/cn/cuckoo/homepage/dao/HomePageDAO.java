package cn.cuckoo.homepage.dao;


import java.util.List;
import java.util.Map;

import cn.cuckoo.homepage.entity.HomeImg;
import cn.cuckoo.homepage.entity.ToHomeCarBean;


public interface HomePageDAO {
	/**
	 * 保存用户提交的首页图片
	 * @param homeImg
	 * @return
	 */
	int saveHomePage(HomeImg homeImg);
	
	/**
	 * 查询是首页图片
	 * @param id
	 * @return
	 */
	HomeImg findHomePageById(String id);
	
	/**
	 * 获取首页图片信息
	 * @return
	 */
	List<HomeImg> getHomeImgList();
	
	/**
	 * 我要上首页加载更多
	 * @return
	 */
	List<ToHomeCarBean> getLoadmore(Map limtSE);
	
	/**
	 * 根据用户ID 与  imgid获取是否存在记录
	 * @param map
	 * @return
	 */
	int getCountPraiseByUseridAndImgid(Map map);
	
	/**
	 * 用户点赞
	 * @param paramMap
	 */
	void savePraise(Map paramMap);

	/**
	 * 取消赞
	 * @param paramMap
	 */
	void deletePraise(Map paramMap);
	
}
