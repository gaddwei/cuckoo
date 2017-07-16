package cn.cuckoo.note.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class NoteTranspond implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2219964908343932981L;
	private String id;
	private String pid;
	private String noteid;
	private String comment;
	private Timestamp creatime;
	public NoteTranspond() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getNoteid() {
		return noteid;
	}
	public void setNoteid(String noteid) {
		this.noteid = noteid;
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
