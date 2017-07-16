package cn.cuckoo.note.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class NoteComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6285730243828946823L;
	private String id;
	private String userId;
	private String noteId;
	private String pid;
	private String comment;
	private Timestamp creatime;
	public NoteComment() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	
}
