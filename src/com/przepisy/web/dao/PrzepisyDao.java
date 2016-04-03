package com.przepisy.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
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

	
	public void indexPrzepisy(){
			FullTextSession fullTextSession = Search.getFullTextSession(session());
			try {
				fullTextSession.createIndexer().startAndWait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public List<Przepis> searchForPrzepis(String searchText) {
	      FullTextSession fullTextSession = Search.getFullTextSession(session());

	      QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Przepis.class).get();
	      org.apache.lucene.search.Query query = qb
	        .keyword().onFields("name", "skladniki")
	        .matching(searchText)
	        .createQuery();

	      org.hibernate.Query hibQuery =
	         fullTextSession.createFullTextQuery(query, Przepis.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

	      List<Przepis> results = hibQuery.list();
	      return results;
	  
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

	public void delete(Przepis przepis) {
		session().delete(przepis);
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
