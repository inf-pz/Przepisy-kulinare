package com.przepisy.web.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {

	@NotBlank(message="Login nie może być pusty")
	@Size(min=4, max=32, message="Login musi mieć od 4 do 32 znaków")
	@Pattern(regexp="^\\w+$", message="Login może zawierać tylko znaki alfanumeryczne")
	@Id
	private String login;
	
	@NotBlank(message="Hasło nie może być puste")
	@Size(min=4, max=32, message="Hasło musi mieć od 4 do 32 znaków")
	@Pattern(regexp="^\\S+$", message="Hasło nie może zawierać spacji")
	private String password;
	
	@Email(message="Adres e-mail nie jest poprawny")
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_rejestracji = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_active = new Date();
	
	private boolean active;
	private String authority;
	
	public User(){
		
	}
	@Override
	public String toString(){
		return login;
	}

	
	public User(String login, String password, String email, boolean active, String authority) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.active = active;
		this.authority = authority;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getData_rejestracji() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		  
		return df.format(data_rejestracji);
	}
	public void setData_rejestracji(Date data_rejestracji) {
		this.data_rejestracji = data_rejestracji;
	}	
	public void setLast_active(Date last_active) {
		this.last_active = last_active;
	}	
	public String getLast_active() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		  
		return df.format(last_active);
	}

}
