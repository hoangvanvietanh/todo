package com.green.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.spring.dao.UserDAO;
import com.green.spring.entity.User;
@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
	public List<User> findAll()
	{
		return userDAO.findAll();
	}
	
	public User createUser(User user) {
		User result = userDAO.create(user);
		return result;
	}
	
	public User findUser(int id) {
		return userDAO.find(id);
	}
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}
	public Boolean checkEmail(String email) {
		 List<User> user = findAll();
		 for(User u:user)
		 {
			 if(u.getEmail().equals(email))
			 {
				 return true;
			 }
		 }		
		return false;
	}
	public Boolean checkLogin(String email, String pass) {
		if(checkEmail(email)==false)
		{
			return false;
		}
		 List<User> user = findAll();
		 for(User u:user)
		 {
			 if(u.getEmail().equals(email)&&u.getPassword().equals(pass))
			 {
				 return true;
			 }
		 }		
		return false;
	}
	public User updateUser(User user) {
		User result = userDAO.update(user);
		return result;
	}
	
	public User deleteUser(User user) {
		User result = userDAO.delete(user);
		return result;
	}
}
