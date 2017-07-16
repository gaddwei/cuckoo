package cn.cuckoo.account.dao;

import java.util.Map;

import cn.cuckoo.account.entity.User;

public interface UserDAO {
	/**
	 * 保存用户信息
	 * @param user
	 */
	void saveUser(User user);
	
	/**
	 * 根据邮箱查询用户所用信息
	 * @param email
	 * @return
	 */
	User findUserByEaiml(String email);
	
	User findUserViewById(String id);
	
	void updateUserToken(User user);
	
	int checkUserTokenById(Map<String,String> map);

	int findAttentionByUserId(Map<String, String> map);

	void addAttention(Map<String, String> map);

	void cancelAttention(Map<String, String> map);

	int getAttentionCount(String uid);

	int getUserFans(String uid);
}
