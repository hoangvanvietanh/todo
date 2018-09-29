package com.green.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="friend")
public class Friends {
	@Id
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="iduser1")
	private User user1;

	@ManyToOne
	@JoinColumn(name="iduser2")
	private User user2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	
}
