package cn.cuckoo.note.entity;

import java.io.Serializable;
import java.util.List;

import cn.cuckoo.account.entity.User;

public class NoteMap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7805870028779708380L;
	private String id;
	private User user;
	private Note note;
	private List<NoteTranspond> transponds;
	private List<NoteComment> comments;
	private List<NotePraise> praises;
	public NoteMap() {
	}
	public String getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public List<NoteTranspond> getTransponds() {
		return transponds;
	}
	public void setTransponds(List<NoteTranspond> transponds) {
		this.transponds = transponds;
	}
	public List<NoteComment> getComments() {
		return comments;
	}
	public void setComments(List<NoteComment> comments) {
		this.comments = comments;
	}
	public List<NotePraise> getPraises() {
		return praises;
	}
	public void setPraises(List<NotePraise> praises) {
		this.praises = praises;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
