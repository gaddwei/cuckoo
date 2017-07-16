package cn.cuckoo.account.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import cn.cuckoo.account.dao.UserDAO;
import cn.cuckoo.account.entity.User;
import cn.cuckoo.core.service.GeneralException;
import cn.cuckoo.note.dao.NoteDAO;
import cn.cuckoo.note.entity.CircleNoteBean;
import cn.cuckoo.util.Util;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDAO dao;
	
	@Resource
	private NoteDAO notedao;
	
	public User register(String nickname, String sex, String age, String email, String password,
			String re_password, String path,String root,HttpServletRequest req,HttpServletResponse res) throws UserException{
		if(nickname == null || "".equals(nickname)){
			throw new UserException("用户昵称不能为空！");
		}
		if(nickname.length() > 15){
			throw new UserException("用户昵称过长，只能是15个字符！");
		}
		if(sex != null || !"".equals(sex)){
			if(!sex.matches("[0-1]")){
				throw new UserException("性别选择非法！");
			}
		}
		if(age != null && !"".equals(age) ){
			if(!age.matches("\\d{0,3}")){
				throw new UserException("年龄不合法！");
			}
		}
		if(password == null || "".equals(password)){
			throw new UserException("请输入密码！");
		}
		if(re_password == null || "".equals(re_password)){
			throw new UserException("请输入密码！");
		}
		String reg = "[a-zA-Z0-9]{6,12}";
		if(!password.matches(reg) || !re_password.matches(reg)){
			throw new UserException("密码非法，必须由6-12个数字字母组成！");
		}
		if(!password.equals(re_password)){
			throw new UserException("两次输入密码不一致！");
		}
		if(email == null || "".equals(email)){
			throw new UserException("email邮箱地址不能为空！");
		}
		reg = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$" ;
		if(!email.matches(reg)){
			throw new UserException("email邮箱地址不符合规则！");
		}
		User user = dao.findUserByEaiml(email);
		if(user != null){
			throw new UserException("email邮箱已占用！");
		}
		String userId = UUID.randomUUID().toString();
		String split = File.separator;
		//为新用户创建文件夹用于存放个人文件
		String newUser = "upload"+split+"account"+split+"user"+split+userId;
		String newPath = null;
		if(path != null && !"".equals(path)){
			try {
				newPath = Util.pasteImg(path,newUser,root);
			} catch (IOException e) {
				e.printStackTrace();
				throw new UserException("请重试！！");
			}
		}
		user = new User();
		user.setId(userId);
		user.setNickname(nickname);
		user.setSex(Integer.valueOf(sex));
		user.setAge(Integer.valueOf(age));
		//加盐
		user.setPwd(Util.crypt(password));
		user.setEmail(email);
		user.setAddr_head_ico(newPath);
		user.setToken(userId);
		dao.saveUser(user);
		//设置cookie token userId
		user.setAddr_head_ico(Util.getRealPath(req, newPath));
		Cookie cookie = new Cookie("token", userId);
		cookie.setMaxAge(60*60*24*15);
		cookie.setPath("/");
		res.addCookie(cookie);
		Cookie cookie1 = new Cookie("userId", userId);
		cookie1.setMaxAge(60*60*24*15);
		cookie1.setPath("/");
		res.addCookie(cookie1);
		return user;
	}


	
	public User login(String email, String password ,HttpServletRequest req, HttpServletResponse res) throws UserException {
		if(email == null || "".equals(email)){
			throw new UserException("email邮箱地址不能为空！");
		}
		String reg = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$" ;
		if(!email.matches(reg)){
			throw new UserException("email邮箱地址不符合规则！");
		}
		if(password == null || "".equals(password)){
			throw new UserException("请输入密码！");
		}
		reg = "[a-zA-Z0-9]{6,12}";
		if(!password.matches(reg) || !password.matches(reg)){
			throw new UserException("密码非法，必须由6-12个数字字母组成！");
		}
		User user = dao.findUserByEaiml(email);
		password = Util.crypt(password);
		if(user == null || !user.getPwd().equals(password)){
			throw new LoginErrorException("密码或账户错误");
		}
		String token = UUID.randomUUID().toString();
		user.setToken(token);
		dao.updateUserToken(user);
		user.setAddr_head_ico(Util.getRealPath(req, user.getAddr_head_ico()));
		//设置cookie token
		
		Cookie cookie = new Cookie("token", token);
		cookie.setMaxAge(60*60*24*15);
		cookie.setPath("/");
		res.addCookie(cookie);
		
		Cookie cookie1 = new Cookie("userId", user.getId());
		cookie1.setMaxAge(60*60*24*15);
		cookie1.setPath("/");
		res.addCookie(cookie1);
		System.out.println("UserServiceImpl.login()");
		return user;
	}
	
	
	public boolean checkLogin(String userId, String token){
		Map<String , String > map = new HashMap<String, String>();
		map.put("id", userId);
		map.put("token", token);
		if(dao.checkUserTokenById(map) <= 0){
			return false;
		};
		return true;
	}



	public boolean getAttentionState(String attention_id, String userId) throws GeneralException{ 
		if(!Util.isNotNull(attention_id)){
			throw new  GeneralException("错误");
		}
		
		Map<String ,String > map = new HashMap<String, String>();
		map.put("attention_id", attention_id);
		map.put("uid", userId);
		int num = dao.findAttentionByUserId(map);
		if(num > 0){
			return true;
		}
		return false;
	}



	public String operateAttention(String uid, String attention, String operateState) {
		if(!Util.isNotNull(attention)){
			throw new  GeneralException("错误");
		}
		if(!Util.isNotNull(operateState)){
			throw new  GeneralException("错误");
		}
		Map<String ,String > map = new HashMap<String, String>();
		map.put("attentionId", attention);
		map.put("uid", uid);
		Boolean isExist = this.getAttentionState(attention, uid);
		if("addAttention".equals(operateState)){
			if(!isExist){
				String id = UUID.randomUUID().toString();
				map.put("id", id);
				dao.addAttention(map);
			}
			return "addAttention";
		}
		if("cancelAttention".equals(operateState)){
			if(isExist){
				dao.cancelAttention(map);
			}
			return "cancelAttention";
		}
		return null;
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CircleNoteBean> getCircle(String uid,HttpServletRequest req) {
		int start = 0;
		int end = 5;
		String str1 = req.getParameter("start");
		String str2 = req.getParameter("end");
		
		if(str1 != null && str2 != null){
			start = Integer.parseInt(str1);
			end = Integer.parseInt(str2);
		}
		Map map = new HashMap();
		map.put("userid", uid);
		map.put("start", start);
		map.put("end", end);
		List<CircleNoteBean>   circleNotes = notedao.getCircleNote(map);
		List<CircleNoteBean>   newCircleNotes = new ArrayList<CircleNoteBean>(); 
		for(int i=0 ; i < circleNotes.size();i++ ){
			CircleNoteBean circleNote = circleNotes.get(i);
			List<String> imgs = match(circleNote.getContent(),"img","src");
			String content = StripHT(circleNote.getContent());
			circleNote.setImgs(imgs);
			circleNote.setTxtContent(content);
			newCircleNotes.add(circleNote);
		}
		
		return newCircleNotes;
	}
	
	 public static List<String> match(String source, String element, String attr) {  
	        List<String> result = new ArrayList<String>();  
	        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";  
	        Matcher m = Pattern.compile(reg).matcher(source);  
	        while (m.find()) {  
	            String r = m.group(1);  
	            result.add(r);  
	        }  
	        return result;  
	    }  
	 public static String StripHT(String strHtml) {  
	     String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签    
	        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符    
	        return txtcontent;  
	   }



	public Map<String, Integer> getInfo(String uid) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("attentions",dao.getAttentionCount(uid));
		map.put("fans",dao.getUserFans(uid) );
		return map;
	} 
}
