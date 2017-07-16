package cn.cuckoo.core.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cuckoo.core.entity.District;
import cn.cuckoo.core.service.ProCityService;
import cn.cuckoo.util.JsonResult;

@Controller
@RequestMapping("/core")
public class CoreController {
	
	@Resource
	private ProCityService proCityService;
	 @RequestMapping("/initProCityData.do")
	    @ResponseBody
	    public JsonResult<List<District>> getinitProvinceData(HttpServletRequest req){
	    	String level = req.getParameter("level");
	    	String upid = req.getParameter("upid");
	    	List<District> districts = proCityService.getProCityData(level,upid);
			return new JsonResult<List<District>>(districts);
	    	
	    }
}
