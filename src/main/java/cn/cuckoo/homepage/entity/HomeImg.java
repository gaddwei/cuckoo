package cn.cuckoo.homepage.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class HomeImg implements Serializable{

	private static final long serialVersionUID = 1815842520216107028L;
	
	private String id;
	private String userId;
	private String imgLoc;
	private String description;
	private int praise;
	private Timestamp creatime;
	public HomeImg() {
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
	public String getImgLoc() {
		return imgLoc;
	}
	public void setImgLoc(String imgLoc) {
		this.imgLoc = imgLoc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPraise() {
		return praise;
	}
	public void setPraise(int praise) {
		this.praise = praise;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	
}
