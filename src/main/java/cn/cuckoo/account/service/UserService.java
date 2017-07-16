package cn.cuckoo.account.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cuckoo.account.entity.User;
import cn.cuckoo.note.entity.CircleNoteBean;

public interface UserService {
	/**
	 * 注册业务逻辑层
	 * @param re_nick
	 * @param re_sex
	 * @param re_age
	 * @param re_email
	 * @param re_password
	 * @param re_re_password
	 * @param path
	 * @return
	 */
	User register(String nickname, String sex, String age, String email, String password,
			String re_password, String path,String root,HttpServletRequest req,HttpServletResponse res) throws UserException;
	
	/**
	 * 用户登录
	 * @param email
	 * @param password
	 * @return
	 * @throws UserException
	 */
	User login(String email,String password ,HttpServletRequest req, HttpServletResponse res) throws UserException;
	
	
	public boolean checkLogin(String userId,String token);

	boolean getAttentionState(String attention_id, String userId);

	String operateAttention(String uid, String attention, String operateState);

	List<CircleNoteBean> getCircle(String uid,HttpServletRequest req);

	Map<String, Integer> getInfo(String uid);

}
