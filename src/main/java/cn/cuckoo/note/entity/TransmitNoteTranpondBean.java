package cn.cuckoo.note.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class TransmitNoteTranpondBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1992993312010095139L;
	private String ioc;
	private String nickname;
	private String comment;
	private Timestamp creatime;
	public String getIoc() {
		return ioc;
	}
	public void setIoc(String ioc) {
		this.ioc = ioc;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
