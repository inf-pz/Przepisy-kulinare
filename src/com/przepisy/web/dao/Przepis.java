package com.przepisy.web.dao;

import org.springframework.web.multipart.MultipartFile;

public class Przepis {

	private int id;
	private String name;
	private String text;
	private int status;
	private String username;
	private MultipartFile photo;
	
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public Przepis(){
		
	}
	public Przepis(String name, String text, int status, String username, MultipartFile photo) {
		this.name = name;
		this.text = text;
		this.status = status;
		this.username = username;
		this.photo = photo;
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

