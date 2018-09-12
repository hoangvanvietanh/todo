package com.green.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.spring.dao.ToDoDAO;
import com.green.spring.entity.ToDo;

@Service
public class HomeService {
	@Autowired
	ToDoDAO toDoDAO;
	
	public List<ToDo> findAll()
	{
		return toDoDAO.findAll();
	}
	
	public ToDo createToDo(ToDo todo) {
		ToDo result = toDoDAO.create(todo);
		return result;
	}
	
	public ToDo findToDo(int id) {
		return toDoDAO.find(id);
	}
	
	public ToDo updateToDo(ToDo todo) {
		ToDo result = toDoDAO.update(todo);
		return result;
	}
	
	public ToDo deleteToDo(ToDo todo) {
		ToDo result = toDoDAO.delete(todo);
		return result;
	}
	
}
