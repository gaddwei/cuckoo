package cn.cuckoo.core.entity;

import java.io.Serializable;

public class District implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5330127927243248283L;
	private int id;
	private String name;
	private int level;
	private int upid;
	private int list;
	public District() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getUpid() {
		return upid;
	}
	public void setUpid(int upid) {
		this.upid = upid;
	}
	public int getList() {
		return list;
	}
	public void setList(int list) {
		this.list = list;
	}
	
	
	
}
