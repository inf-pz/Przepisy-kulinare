package com.przepisy.web.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reports")
public class Report {

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_login")
	private User user;
	@Column(columnDefinition = "TEXT")
	private String text;
	@ManyToOne
	@JoinColumn(name = "przepis_id")
	private Przepis przepis;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new Date();

	public Report() {
		this.przepis = new Przepis();
		this.user = new User();
	}

	public Report(int id, User autor, Date data, String text, Przepis przepis) {
		this.id = id;
		this.user = autor;
		this.data = data;
		this.text = text;
		this.przepis = przepis;
	}

	public int getId(){
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		return df.format(data);
	}

	public void setData(Date data) {
		this.data = data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Przepis getPrzepis() {
		return przepis;
	}

	public void setPrzepis(Przepis przepis) {
		this.przepis = przepis;
	}

}
