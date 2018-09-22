package com.green.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.spring.entity.User;

@Repository
public class UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<User> findAll()
	{
		Session session = sessionFactory.openSession();
		TypedQuery<User> query = session.createQuery("FROM User", User.class);
		List<User> user = query.getResultList();
		return user;
	}

	@SuppressWarnings("rawtypes")
	public User findByEmail(String email) {
		Session session = sessionFactory.openSession();
		String sql = "select c from User c where c.email = :email";
		Query query = session.createQuery(sql);
		query.setParameter("email", email);
		User user = (User) query.uniqueResult();
		return user;
	}
	public User find(int id) {
		return sessionFactory.openSession().find(User.class, id);
	}
	
	public User create(User user) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.save(user);
		tran.commit();
		session.close();
		return user;
	}
	
	public User update(User user) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.update(user);
		tran.commit();
		session.close();
		return user;
	}
	
	public User delete(User user) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.delete(user);
		tran.commit();
		session.close();
		return user;
	}
}
