package com.green.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.spring.dao.ToDoDAO;
import com.green.spring.entity.ToDo;


@Service
public class ToDoServices {
	@Autowired
	ToDoDAO toDoDAO;
	
	public List<ToDo> findAll()
	{
		return toDoDAO.findAll();
	}
	
	public List<ToDo> findByuser(int id, int page, String name)
	{
		return toDoDAO.findByIdUser(id, page, name);
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
	
	public double getNumberPage(int id) {
		double test = toDoDAO.getMaxResults(id)%10;
		if(test==0)
		{
			return toDoDAO.getMaxResults(id)/10;
		}
		
		return Math.round(toDoDAO.getMaxResults(id)/10+0.5);
	}
}
