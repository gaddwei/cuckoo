package cn.cuckoo.account.web;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;

import cn.cuckoo.account.entity.User;
import cn.cuckoo.account.service.UserService;
import cn.cuckoo.note.entity.CircleNoteBean;
import cn.cuckoo.util.AbstractController;
import cn.cuckoo.util.JsonResult;
import cn.cuckoo.util.Util;

@Controller
@RequestMapping("/account")
public class AccountDealController extends AbstractController{
	
	Logger log = Logger.getLogger(AccountDealController.class);
	
	@Resource(name="userService")
	private UserService us;
	
	@RequestMapping("/registerico.do")
	@ResponseBody
	public String registerico(HttpServletRequest request){
		String patha = request.getRealPath("/");
		System.out.println(patha);
		try {
	        //����ǰ�����ĳ�ʼ����  CommonsMutipartResolver ���ಿ�ֽ�������
	       CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	               request.getSession().getServletContext());
	       String path = "account/temp/ico/"+UUID.randomUUID().toString();
	       //���form���Ƿ���enctype="multipart/form-data"
	       if(multipartResolver.isMultipart(request))
	       {
	           //��request��ɶಿ��request
	           MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	          //��ȡmultiRequest �����е��ļ���
	           Iterator iter=multiRequest.getFileNames();
	           while(iter.hasNext())
	           {
	               //һ�α��������ļ�
	               MultipartFile file=multiRequest.getFile(iter.next().toString());
	               if(file!=null)
	               {	
	            	   String fileName = file.getOriginalFilename();
	                   path += fileName.substring(fileName.lastIndexOf("."));
	                   //�ϴ�
	                   file.transferTo(new File(patha+path));
	               }
	                
	           }
	          
	       }
	       System.out.println(path);
	       return path; 
		} catch (Exception e) {
			
		}
		return "error";
	}


	//注册处理ע
	@RequestMapping("/register.do")
	@ResponseBody
	public JsonResult<User> register(HttpServletRequest req,HttpServletResponse res){
		String root = req.getRealPath("/");
		String path = req.getParameter("re_icopath");
		String nickname = req.getParameter("re_nick");
		String sex = req.getParameter("re_sex");
		String age = req.getParameter("re_age");
		String email = req.getParameter("re_email");
		String password = req.getParameter("re_password");
		String re_password = req.getParameter("re_re_password");
		User user = us.register(nickname, sex, age, email, password, re_password, path, root,req,res);
		HttpSession session= req.getSession();
		session.setAttribute("userId", user.getId());
		session.setAttribute("token", user.getToken());
		return new JsonResult<User>(user);
		}
	
	
	//用户登录处理
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<User> login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpServletRequest req,
			HttpServletResponse res
			){
		User user = us.login(email, password, req, res);
		log.debug("用户登录---------"+email);
		return new JsonResult<User>(user);
	}
	
	@RequestMapping("/getAttentionState.do")
	@ResponseBody
	public JsonResult<Boolean> getAttentionState(HttpServletRequest req){
		String attention_id = req.getParameter("noteUserId");
		String userId = req.getParameter("userId");
		Boolean attentionState = us.getAttentionState(attention_id,userId);
		return new JsonResult<Boolean>(attentionState);
	}
	
	@RequestMapping("/logging/operateAttention.do")
	@ResponseBody
	public JsonResult<String> OperateAttention(HttpServletRequest req){
		String operateState = req.getParameter("operateState"); 
		String attention = req.getParameter("attention");
		String uid = Util.getCookie(req, "userId");
		String isOperateState = us.operateAttention(uid,attention,operateState);
		return new JsonResult<String>(isOperateState);
	}
	
	@RequestMapping("/logging/circle.do")
	public String myCircle(HttpServletRequest req){
		String uid = Util.getCookie(req, "userId");
		List<CircleNoteBean> circleNotes = us.getCircle(uid,req);
		Map<String,Integer> selfInfoMap = us.getInfo(uid);
		Gson gson = new Gson();
		String str = gson.toJson(circleNotes);
		String selfInfo = gson.toJson(selfInfoMap);
		req.setAttribute("circleNotes", str);
		req.setAttribute("selfInfo", selfInfo);
		return "circle";
	}
	@RequestMapping("/logging/moreCircle.do") 
	@ResponseBody 
	public JsonResult<List<CircleNoteBean> > moreCircle(HttpServletRequest req){
		String uid = Util.getCookie(req, "userId");
		List<CircleNoteBean> circleNotes = us.getCircle(uid,req);
		return new JsonResult<List<CircleNoteBean>>(circleNotes);
	}
	
	
}
	



