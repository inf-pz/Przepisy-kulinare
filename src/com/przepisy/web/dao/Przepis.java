package com.przepisy.web.dao;

public class Przepis {

	private int id;
	private String name;
	private String text;
	private int status;
	private String username;
	
	public Przepis(){
		
	}
	public Przepis(String name, String text, int status, String username) {
		this.name = name;
		this.text = text;
		this.status = status;
		this.username = username;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Przepis [id=" + id + ", name=" + name + ", text=" + text + ", status=" + status + ", username="
				+ username + "]";
	}
}

