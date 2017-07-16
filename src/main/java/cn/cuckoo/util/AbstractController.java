package cn.cuckoo.util;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cuckoo.account.service.LoginErrorException;
import cn.cuckoo.account.service.UserException;
import cn.cuckoo.homepage.service.HomePageException;


public abstract class AbstractController {
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(UserException.class)
	@ResponseBody
	public JsonResult expUser(UserException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(LoginErrorException.class)
	@ResponseBody
	public JsonResult explogin(LoginErrorException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(HomePageException.class)
	@ResponseBody
	public JsonResult explogin(HomePageException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult explogin(Exception e){
		e.printStackTrace();
		return new JsonResult(e);
	}
}
