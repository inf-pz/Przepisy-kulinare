package com.przepisy.web.dao;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="przepisy")
public class Przepis {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String text;
	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	private int status;
	
	@Lob
	private Blob photo;
	
	@ManyToOne
	@JoinColumn(name="login")
	private User user;

	public Przepis(){
		this.user = new User();
	}
	
	public Przepis(String name, String text, int status, User user) {
		this.name = name;
		this.text = text;
		this.status = status;
		this.user = user;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

