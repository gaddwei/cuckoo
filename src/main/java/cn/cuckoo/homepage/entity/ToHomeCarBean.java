package cn.cuckoo.homepage.entity;

import java.io.Serializable;

import cn.cuckoo.account.entity.User;
import cn.cuckoo.homepage.entity.HomeImg;

public class ToHomeCarBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5898258651186968652L;
	private String id;
	private User user;
	private HomeImg homeImg;
	private boolean praise_state;
	public boolean getPraise_state() {
		return praise_state;
	}
	public void setPraise_state(boolean praise_state) {
		this.praise_state = praise_state;
	}
	public ToHomeCarBean(User user, HomeImg homeImg) {
		this.user = user;
		this.homeImg = homeImg;
	}
	public ToHomeCarBean() {
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public HomeImg getHomeImg() {
		return homeImg;
	}
	public void setHomeImg(HomeImg homeImg) {
		this.homeImg = homeImg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
