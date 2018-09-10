package com.green.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.spring.entity.ToDo;


@Repository
public class ToDoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<ToDo> findAll()
	{
		Session session = sessionFactory.openSession();
		TypedQuery<ToDo> query = session.createQuery("FROM ToDo", ToDo.class);
		List<ToDo> contact = query.getResultList();
		return contact;
	}

	public ToDo find(int id) {
		return sessionFactory.openSession().find(ToDo.class, id);
	}
	
	public ToDo create(ToDo todo) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.save(todo);
		tran.commit();
		session.close();
		return todo;
	}
	
	public ToDo update(ToDo todo) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.update(todo);
		tran.commit();
		session.close();
		return todo;
	}
	
	public ToDo delete(ToDo todo) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.delete(todo);
		tran.commit();
		session.close();
		return todo;
	}
}
