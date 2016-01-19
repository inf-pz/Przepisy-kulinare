package com.przepisy.web.dao;


public class User {

	private String login;
	private String password_h;
	private String email;
	private boolean active;
	private String authority;
	
	public User(){
		
	}
	
	public User(String login, String password_h, String email, boolean active, String authority) {
		this.login = login;
		this.password_h = password_h;
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
	public String getPassword_h() {
		return password_h;
	}
	public void setPassword_h(String password_h) {
		this.password_h = password_h;
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
	
	
}
