package com.przepisy.web.dao;

public class Przepis {

	private int id;
	private String name;
	private String text;
	private int status;
	private int member_id;
	
	public Przepis(){
		
	}
	public Przepis(String name, String text, int status, int member_id) {
		this.name = name;
		this.text = text;
		this.status = status;
		this.member_id = member_id;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	@Override
	public String toString() {
		return "Przepis [id=" + id + ", name=" + name + ", text=" + text + ", status=" + status + ", member_id="
				+ member_id + "]";
	}
}

