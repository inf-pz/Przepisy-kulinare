package com.przepisy.web.service;

import java.util.List;

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
	
	public User findUser(String login){
		return usersDao.find(login);
	}


	public boolean exist(String login) {
		return usersDao.exists(login);
	}
	
	public void updateUser(User user){
		usersDao.update(user);
	}
	
	public List<User> getUsers(){
		return usersDao.getUsers();
	}
	public void deleteUser(User user){
		usersDao.delete(user);
	}
}
