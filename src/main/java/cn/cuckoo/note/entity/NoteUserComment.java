package cn.cuckoo.note.entity;

import java.io.Serializable;

import cn.cuckoo.account.entity.User;

public class NoteUserComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6822817772194991233L;
	private String id;
	private User user;
	private NoteComment comment;
	public NoteUserComment() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public NoteComment getComment() {
		return comment;
	}
	public void setComment(NoteComment comment) {
		this.comment = comment;
	}
	
}
