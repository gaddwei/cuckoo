package cn.cuckoo.homepage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cuckoo.account.service.UserService;
import cn.cuckoo.homepage.entity.ToHomeCarBean;
import cn.cuckoo.homepage.service.HomePublishService;
import cn.cuckoo.util.AbstractController;
import cn.cuckoo.util.JsonResult;

@Controller
@RequestMapping("/home")
public class HomePageController extends AbstractController{
	@Resource
	private HomePublishService service;
	
	@Resource
	private  UserService userService;
	
	@RequestMapping("/publisHome.do")
	@ResponseBody
	public boolean publisHome(HttpServletRequest req){
		
		String root = req.getRealPath("/");
		String userId = req.getParameter("userId");
		String imgPath = req.getParameter("imgPath");
		String cr = req.getParameter("cr");
		return service.saveHomePage(imgPath, cr, userId, root);
	}
	
		
	@RequestMapping("/loadmore.do")
	@ResponseBody
	public List<ToHomeCarBean>  loadmore(HttpServletRequest req){
		String pages = req.getParameter("pages");
		String pageSize = req.getParameter("pageSize");
		String userId = req.getParameter("userId");
		List<ToHomeCarBean> thc = service.loadmore(Integer.valueOf(pages), Integer.valueOf(pageSize), userId);
		return thc;
	}
	
	@RequestMapping("/logging/addPraise.do")
	@ResponseBody
	public JsonResult<Boolean>  addPraise(HttpServletRequest req){
		String imgId = req.getParameter("imgId");
		return new JsonResult<Boolean>(service.savePraise(req, imgId));
	}
	
	
	@RequestMapping("/logging/cancelPraise.do")
	@ResponseBody
	public JsonResult<Boolean>  cancelPraise(HttpServletRequest req){
		String imgId = req.getParameter("imgId");
		return new JsonResult<Boolean>(service.cancelPraise(req,imgId));
	}
	
}
