package cn.cuckoo.note.entity;

import java.io.Serializable;

import cn.cuckoo.account.entity.User;

public class NoteUserTranspond implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 891045863344495111L;
	private String id;
	private User user;
	private NoteTranspond transpond;
	public NoteUserTranspond() {
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
	public NoteTranspond getTranspond() {
		return transpond;
	}
	public void setTranspond(NoteTranspond transpond) {
		this.transpond = transpond;
	}
	
	
}
