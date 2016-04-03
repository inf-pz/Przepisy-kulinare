package com.przepisy.web.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.przepisy.web.dao.User;
import com.przepisy.web.dao.UsersDao;

@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	private UsersDao usersDao;

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

		UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		User user = usersDao.find(username);
		user.setLast_active(new Date());
		usersDao.update(user);
	}

}