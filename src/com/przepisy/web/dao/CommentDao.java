package com.przepisy.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("commentsDao")
public class CommentDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	

	
	public Comment getComment(int id){
		
		Criteria crit = session().createCriteria(Comment.class);
		crit.add(Restrictions.idEq(id));
		Comment comment = (Comment)crit.uniqueResult();
		return comment;
	}
	
	public void update(Comment comment){
		
		session().update(comment);
		
	}
	
	public void create(Comment comment){
		session().save(comment);
		
	}
	
	public void delete(Comment comment){
		
		session().delete(comment);
	}

	
	@SuppressWarnings("unchecked")
	public List<Comment>getComments(Przepis przepis){
		Criteria crit = session().createCriteria(Comment.class);
		crit.createAlias("przepis", "p").add(Restrictions.eq("przepis.id", przepis.getId()));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment>getComments(User user){
		Criteria crit = session().createCriteria(Comment.class);
		crit.createAlias("autor", "a").add(Restrictions.eq("autor.login", user.getLogin()));
		return crit.list();
	}
	
	
		
		
}
