package com.przepisy.web.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name="user_login")
	private User autor;
	@Column(columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP")
	private Date datatime;
	private String text;
	@ManyToOne
	@JoinColumn(name="przepis_id")
	private Przepis przepis;
	
	
	public Comment(){
		this.przepis = new Przepis();
		this.autor = new User();
	}
	

	
	public Comment(int id, User autor, Date datatime, String text, Przepis przepis) {
		this.id = id;
		this.autor = autor;
		this.datatime = datatime;
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
	public Date getDatatime() {
		return datatime;
	}
	public void setDatatime(Date datatime) {
		this.datatime = datatime;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
		
}
