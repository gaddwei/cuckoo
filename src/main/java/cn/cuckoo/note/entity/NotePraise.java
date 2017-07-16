package cn.cuckoo.note.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class NotePraise implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7453044743620228233L;
	private String id;
	private String noteId;
	private String pid;
	private Timestamp creatime;
	public NotePraise() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	
	
}
