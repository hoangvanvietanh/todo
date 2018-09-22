package com.green.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
	
	@SuppressWarnings("rawtypes")
	public int getMaxResults(int id)
	{
		Session session = sessionFactory.openSession();
		String cout = "select count(*) from ToDo c where c.user.id = :id";
		Query queryCout = session.createQuery(cout);
		queryCout.setParameter("id", id);
		 return ((Number) queryCout.uniqueResult()).intValue();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ToDo> findByIdUser(int id, int page) {
		int first;
		Session session = sessionFactory.openSession();
		if(page==1)
		{
			first = 0;
		}
		else
		{
			first = (10*page)-10;
		}
		String sql = "select c from ToDo c where c.user.id = :id";
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		query.setFirstResult(first);
		query.setMaxResults(10);
		List<ToDo> toDo = query.list();
		
		return toDo;
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
