package com.przepisy.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("przepisyDao")
public class PrzepisyDao {

	@Autowired
	public SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Przepis> getPrzepisy() {
		return session().createQuery("from Przepis").list();
	}

	public Przepis getPrzepis(int id) {

		Criteria crit = session().createCriteria(Przepis.class);
		crit.add(Restrictions.idEq(id));
		Przepis przepis = (Przepis) crit.uniqueResult();
		return przepis;
	}

	public void update(Przepis przepis) {

		session().update(przepis);

	}

	@Transactional
	public void create(Przepis przepis) {
		session().save(przepis);

	}

	public void delete(int id) {

		Query query = session().createQuery("delete from Przepis where id=:id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Przepis> getPrzepisy(String login) {

		Criteria crit = session().createCriteria(Przepis.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.login", login));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Comment> getComments(Przepis przepis) {
		Criteria crit = session().createCriteria(Comment.class);
		crit.add(Restrictions.idEq(przepis.getId()));
		return crit.list();
	}

}
