package cn.cuckoo.note.entity;

import java.io.Serializable;

import cn.cuckoo.account.entity.User;

public class NoteCover implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2963376144566635953L;
	private String id;
	private Note note;
	private User user;
	private Integer transpond;
	private Integer comment;
	private Integer praise;
	private String site;
	public NoteCover(){
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getTranspond() {
		return transpond;
	}
	public void setTranspond(Integer transpond) {
		this.transpond = transpond;
	}
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
}
