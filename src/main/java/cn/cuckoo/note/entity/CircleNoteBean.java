package cn.cuckoo.note.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class CircleNoteBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4232317026244908255L;
	private String noteid;
	private String userid;
	private String nickname;
	private String ico;
	private String comment;
	private String content;
	private String txtContent;
	private String notenickname;
	private Timestamp creatime;
	private List<String> imgs;
	public String getNoteid() {
		return noteid;
	}
	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTxtContent() {
		return txtContent;
	}
	public void setTxtContent(String txtContent) {
		this.txtContent = txtContent;
	}
	public String getNotenickname() {
		return notenickname;
	}
	public void setNotenickname(String notenickname) {
		this.notenickname = notenickname;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	public List<String> getImgs() {
		return imgs;
	}
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	
	
}
