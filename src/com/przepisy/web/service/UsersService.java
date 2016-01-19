package com.przepisy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.przepisy.web.dao.User;
import com.przepisy.web.dao.UsersDao;

@Service("usersService")
public class UsersService {
	
	private UsersDao usersDao;
	
	@Autowired
	public void setUsersDao(UsersDao usersDao){
		this.usersDao = usersDao;
	}


	public void createUser(User user) {
		usersDao.create(user);
		
	}


	public boolean exist(String login) {
		return usersDao.exists(login);
	}
}
