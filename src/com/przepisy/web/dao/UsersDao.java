package com.przepisy.web.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("usersDao")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbc;
	
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@Transactional
	public boolean create(User user){
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		jdbc.update("insert into members (login, email, password_h, active) values (:login, :email, :password_h, :active)", params);
		
		return jdbc.update("insert into authorities (login, authority) values (:login, :authority)", params) == 1;
		
	}

	public boolean exists(String login) {
		return jdbc.queryForObject("select count(*) from members where login=:login", new MapSqlParameterSource("login", login), Integer.class) > 0;
	}
	
}
