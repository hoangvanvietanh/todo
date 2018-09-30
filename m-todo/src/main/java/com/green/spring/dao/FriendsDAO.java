package com.green.spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.spring.entity.Friends;

@Repository
public class FriendsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public List<Friends> findAll() {
		Session session = sessionFactory.openSession();
		TypedQuery<Friends> query = session.createQuery("FROM Friends", Friends.class);
		List<Friends> Friends = query.getResultList();
		return Friends;
	}

	public List<Friends> findByIdUser(int id) {
		Session session = sessionFactory.openSession();
		String sql = "select c from Friends c where c.user1.id = :id";
		Query query = session.createQuery(sql);
		query.setParameter("id", id);
		List<Friends> friends = query.list();
		return friends;
	}

	public Friends create(Friends friends) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.save(friends);
		tran.commit();
		session.close();
		return friends;
	}

	public Friends update(Friends friends) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.update(friends);
		tran.commit();
		session.close();
		return friends;
	}

	public Friends delete(Friends friends) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();
		session.delete(friends);
		tran.commit();
		session.close();
		return friends;
	}
}
