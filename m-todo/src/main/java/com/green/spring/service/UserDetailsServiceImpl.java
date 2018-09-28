package com.green.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.green.spring.dao.UserDAO;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//Account account = usertDao.find(username);
		com.green.spring.entity.User users = userDao.findByEmail(email);
		if (users == null) {
			throw new UsernameNotFoundException(email + " not found!");
		}
		
		// TODO: get user permission here
		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority("ADMIN"));
//		authorities.add(new SimpleGrantedAuthority("CONTACT-MANAGER"));
		
		UserDetails user = new User(users.getEmail(), 
				users.getPassword(), authorities);
		System.out.println("email: " + users.getEmail() +"Pass: "+users.getPassword());
		return user;
	}

}
