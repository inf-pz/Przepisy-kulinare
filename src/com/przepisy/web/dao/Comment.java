package com.przepisy.web.dao;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="comments")
public class Comment {

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name="user_login")
	private User autor;
	private String text;
	@ManyToOne
	@JoinColumn(name="przepis_id")
	private Przepis przepis;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new Date();
	
	
	public Comment(){
		this.przepis = new Przepis();
		this.autor = new User();
	}
	

	
	public Comment(int id, User autor, Date data, String text, Przepis przepis) {
		this.id = id;
		this.autor = autor;
		this.data = data;
		this.text = text;
		this.przepis = przepis;
	}
	

	public Przepis getPrzepis(){
		return przepis;
	}
	
	public void setPrzepis(Przepis przepis){
		this.przepis = przepis;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getAutor() {
		return autor;
	}
	public void setAutor(User autor) {
		this.autor = autor;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getData() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		  
		return df.format(data);
	}
	
	public void setData(Date data) {
		this.data = data;
	}
		
}
