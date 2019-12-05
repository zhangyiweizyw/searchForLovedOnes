package com.example.sunyidong.qinfeng;

public class Comment {
	
	private int id;
	private String name;
	private String tel;
	private String email;
	private String content;
	private String qq;
	private String time;
	
	public Comment(String name, String tel, String email, String content, String qq, String time) {
		super();
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.content = content;
		this.qq = qq;
		this.time = time;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
}
