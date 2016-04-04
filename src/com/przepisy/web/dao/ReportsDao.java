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
@Component("reportsDao")
public class ReportsDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public Report getReport(int id) {

		Criteria crit = session().createCriteria(Report.class);
		crit.add(Restrictions.idEq(id));
		Report report = (Report) crit.uniqueResult();
		return report;
	}

	public void create(Report report) {
		session().save(report);
	}

	public void delete(Report report) {
		session().delete(report);
	}

	@SuppressWarnings("unchecked")
	public List<Report> getReports() {
		Criteria crit = session().createCriteria(Report.class);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Report> getReports(Przepis przepis) {
		Criteria crit = session().createCriteria(Report.class);
		crit.createAlias("przepis", "p").add(Restrictions.eq("przepis.id", przepis.getId()));
		return crit.list();
	}
	@SuppressWarnings("unchecked")
	public List<Report> getReports(User user) {
		Criteria crit = session().createCriteria(Report.class);
		crit.createAlias("user", "u").add(Restrictions.eq("user.login", user.getLogin()));
		return crit.list();
	}
	
}
