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
import com.green.spring.entity.User;

import utils.Page;


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
	
	public int getMaxResults(int id)
	{
		Session session = sessionFactory.openSession();
		String cout = "select count(*) from ToDo c where c.user.id = :id";
		Query queryCout = session.createQuery(cout);
		queryCout.setParameter("id", id);
		 return ((Number) queryCout.uniqueResult()).intValue();
		
	}
	/*public Page<ToDo> search(int id, int page)
	{
		int totalRows = 10;
		int MAX_ROWS = getMaxResults(id);
		
		int start=(page -1) * MAX_ROWS;
		int totalPage = totalRows/MAX_ROWS;
		if(totalRows%MAX_ROWS>0) {
			totalPage+=1;
		}
		
		List<ToDo> list = findByIdUser(id);
		
		Page<ToDo> pages = new Page<>();
		pages.setList(list);
		pages.setTotalPage(totalPage);
		return pages;
	}*/
	
	public List<ToDo> findByIdUser(int id, int page) {
		System.out.println("vao tim user by id:"+id);
		int first;
		Session session = sessionFactory.openSession();
//		String cout = "select count(*) from ToDo c where c.user.id = :id";
//		Query queryCout = session.createQuery(cout);
//		queryCout.setParameter("id", id);
//		int max = ((Number) queryCout.uniqueResult()).intValue();
		if(page==1)
		{
			first = 0;
		}
		else
		{
			first = (10*page)-10;
		}
		int max = page*10;
		System.out.println("Tong la::::::::::::::::" + first);
		System.out.println("Tong la::::::::::::::::" + max);
		String sql = "select c from ToDo c where c.user.id = :id";
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		query.setFirstResult(first);
		query.setMaxResults(max);
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
