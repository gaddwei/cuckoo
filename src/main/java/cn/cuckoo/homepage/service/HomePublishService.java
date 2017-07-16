package cn.cuckoo.homepage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.cuckoo.account.dao.UserDAO;
import cn.cuckoo.homepage.dao.HomePageDAO;
import cn.cuckoo.homepage.entity.HomeImg;
import cn.cuckoo.homepage.entity.ToHomeCarBean;
import cn.cuckoo.util.Util;

@Service
public class HomePublishService {
	@Resource
	private HomePageDAO dao;
	@Resource
	private  UserDAO userDao;
	
	
	public boolean saveHomePage(String imgPath,String cr,String userId,String root) throws HomePageException{
		if(!Util.isNotNull(userId)){
			throw new HomePageException("账户为空，请先登录。");
		}
		if(!Util.isNotNull(imgPath)){
			throw new HomePageException("存储路径错误，请重新上传图片。");
		}
		if(!Util.isNotNull(cr)){
			throw new HomePageException("请添加描述内容");
		}
		
		String id = UUID.randomUUID().toString();
		HomeImg img = new HomeImg();
		img.setId(id);
		img.setUserId(userId);
		try {
			 String imgLoc = Util.pasteImg(imgPath, Util.userFilePath(userId), root);
			 img.setImgLoc(imgLoc);
		} catch (Exception e) {
			throw new HomePageException("存储路径错误，请重新上传图片。");
		}
		img.setDescription(cr);
		dao.saveHomePage(img);
		img = null;
		img = dao.findHomePageById(id);
		if(img != null){
			return true;
		}
		return false;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ToHomeCarBean> loadmore(int pages,int pageSize, String userId) {
		long start = (pages-1)*pageSize;
		long end = start+pageSize;
		Map paramMap = new HashMap();
		paramMap.put("start", start);
		paramMap.put("end", end);
		List<ToHomeCarBean> toHomeCars = dao.getLoadmore(paramMap);
		if(Util.isNotNull(userId)){
			for(int i= 0;i< toHomeCars.size();i++){
				ToHomeCarBean hs = toHomeCars.get(i);
				paramMap = new HashMap();
				paramMap.put("userId", userId);
				paramMap.put("imgId", hs.getHomeImg().getId());
				int num =dao.getCountPraiseByUseridAndImgid(paramMap);
				if(num > 0){
					toHomeCars.get(i).setPraise_state(true);
				}
			}
		}
		
		return toHomeCars;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean savePraise(HttpServletRequest req,  String imgId) throws HomePageException{
		String userId = Util.getCookie(req, "userId");
		if(!Util.isNotNull(imgId)){
			throw new HomePageException("请重新刷新页面，数据有误。");
		}
		String id = UUID.randomUUID().toString();
		Map paramMap = new HashMap();
		paramMap.put("userId", userId);
		paramMap.put("imgId", imgId);
		paramMap.put("id", id);
		dao.savePraise(paramMap);
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean cancelPraise(HttpServletRequest req, String imgId)throws HomePageException  {
		String userId = Util.getCookie(req, "userId");
		if(!Util.isNotNull(imgId)){
			throw new HomePageException("请重新刷新页面，数据有误。");
		}
		Map paramMap = new HashMap();
		paramMap.put("userId", userId);
		paramMap.put("imgId", imgId);
		dao.deletePraise(paramMap);
		return true;
	}
}
