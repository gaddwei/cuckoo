package cn.cuckoo.account.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
@Alias("user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nickname;
	private int sex;
	private int age;
	private String pwd;
	private String email;
	private String token;
	private String motto;
	private String addr_head_ico;
	private String addr_homepage_ico;
	private Timestamp register_time;
	private Timestamp last_edit_time;
	public User() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getAddr_head_ico() {
		return addr_head_ico;
	}
	public void setAddr_head_ico(String addr_head_ico) {
		this.addr_head_ico = addr_head_ico;
	}
	public String getAddr_homepage_ico() {
		return addr_homepage_ico;
	}
	public void setAddr_homepage_ico(String addr_homepage_ico) {
		this.addr_homepage_ico = addr_homepage_ico;
	}
	public Timestamp getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Timestamp register_time) {
		this.register_time = register_time;
	}
	public Timestamp getLast_edit_time() {
		return last_edit_time;
	}
	public void setLast_edit_time(Timestamp last_edit_time) {
		this.last_edit_time = last_edit_time;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", sex=" + sex + ", age=" + age + ", pwd=" + pwd
				+ ", email=" + email + ", token=" + token + ", motto=" + motto + ", addr_head_ico=" + addr_head_ico
				+ ", addr_homepage_ico=" + addr_homepage_ico + ", register_time=" + register_time + ", last_edit_time="
				+ last_edit_time + "]";
	}
	
}
