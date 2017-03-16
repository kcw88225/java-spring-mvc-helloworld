package com.in28minutes.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class GeneralRepository {
	public Session getSession() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		return session;
	}

	public void commitSession(Session session) {
		session.getTransaction().commit();
		this.closeSession(session);
	}

	public void closeSession(Session session) {
		session.close();
	}
}
