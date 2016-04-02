package com.przepisy.web.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name = "przepisy")
public class Przepis {

	@Id
	@GeneratedValue
	private int id;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String name;
	private String text;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String skladniki;
	private int czas;
	@Lob
	@Column(name = "photo", columnDefinition = "mediumblob")
	private byte[] photo;

	@ManyToOne
	@JoinColumn(name = "login")
	private User user;
	private int status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new Date();

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Przepis() {
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

	public String getSkladniki() {
		return skladniki;
	}

	public int getCzas() {
		return czas;
	}

	public void setCzas(int czas) {
		this.czas = czas;
	}

	public void setSkladniki(String skladniki) {
		this.skladniki = skladniki;
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

	public String getData() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		return df.format(data);
	}

	public void setData(Date data) {
		this.data = data;
	}
}
