package com.green.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.spring.dao.MessagesDAO;
import com.green.spring.entity.Messages;


@Service
public class MessagesServices {

	@Autowired
	MessagesDAO messagesDAO;
	
	public List<Messages> findByUser(int id1,int id2)
	{
		return messagesDAO.findByIdUser(id1, id2);
	}
	
	public Messages createMess(Messages mess)
	{
		return messagesDAO.create(mess);
	}
}
