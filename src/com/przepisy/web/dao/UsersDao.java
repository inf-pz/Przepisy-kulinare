package com.przepisy.web.dao;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("usersDao")
public class UsersDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	
	public void create(User user){
		
		session().save(user);
		
	}
	
	public void update(User user){
		
		session().update(user);
	}

	public User find(String login){
		
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("login", login));
		User user = (User)crit.uniqueResult();
		return user;
	}
	
	public boolean exists(String login) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("login", login));
		User user = (User)crit.uniqueResult();
		return user != null;
	}
}
