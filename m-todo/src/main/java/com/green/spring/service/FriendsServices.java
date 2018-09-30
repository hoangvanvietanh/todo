package com.green.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.spring.dao.FriendsDAO;
import com.green.spring.entity.Friends;

@Service
public class FriendsServices {

	@Autowired
	FriendsDAO friendsDAO;
	
	public List<Friends> findByIdUser(int id)
	{
		return friendsDAO.findByIdUser(id);
	}
	
	public Friends addFriends(Friends friends)
	{
		return friendsDAO.create(friends);
	}
}
