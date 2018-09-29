package com.green.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.spring.entity.Messages;


@Repository
public class MessagesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Messages> findAll()
	{
		Session session = sessionFactory.openSession();
		TypedQuery<Messages> query = session.createQuery("FROM Messages", Messages.class);
		List<Messages> messages = query.getResultList();
		return messages;
	}
	public List<Messages> findByIdUser(int id1, int id2) {
		Session session = sessionFactory.openSession();
			String sql = "select c from Messages c where (c.user1.id = :id1 and c.user2.id =:id2) or (c.user1.id = :id2 and c.user2.id =:id1)";
			Query query = session.createQuery(sql);
			query.setParameter("id1", id1);
			query.setParameter("id2",id2);
			List<Messages> mes = query.list();
			return mes;
	}
	public Messages create(Messages mes) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.save(mes);
		tran.commit();
		session.close();
		return mes;
	}
	
	public Messages update(Messages mes) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.update(mes);
		tran.commit();
		session.close();
		return mes;
	}
	
	public Messages delete(Messages mes) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.delete(mes);
		tran.commit();
		session.close();
		return mes;
	}
}
