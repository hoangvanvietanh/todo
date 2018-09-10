package com.green.spring.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.green.spring.entity.ToDo;

import utils.Status;

public class ToDoModel {
	private int id;
	private String name;
	private String description;
	private String status;
	private String createdAt;
	private String note;
	private String startDate;
	private String startedAt;
	private String endedAt;
	
	public void formtoDo(ToDo todo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.setId(todo.getId());
		this.setName(todo.getName());
		this.setDescription(todo.getDescription());
		this.setCreatedAt(dateFormat.format(todo.getCreatedAt()));
		this.setNote(todo.getNote());
		this.setStartDate(dateFormat.format(todo.getStartDate()));
		this.setStartedAt(dateFormat.format(todo.getStartedAt()));
		this.setEndedAt(dateFormat.format(todo.getEndedAt()));
	}
	
	public ToDo toToDo() {
		ToDo todo = new ToDo();
		todo.setId(this.getId());
		todo.setName(this.getName());
		
		todo.setDescription(this.getDescription());
		todo.setNote(this.getNote());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		todo.setStartDate(this.getStartDate());
		Calendar cal = Calendar.getInstance();
		todo.setCreatedAt(cal.getTime());
		
        try {
        	Date date1 = dateFormat.parse(this.getStartDate());
			if(date1.compareTo(cal.getTime())>0)
			{
				todo.setStatus("New[1]");
			}
			else
			{
				todo.setStatus("New[2]");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		try {
			todo.setEndedAt(dateFormat.parse(this.getEndedAt()));
		} catch (ParseException e) {
			todo.setEndedAt(null);
		}
		try {
			todo.setStartedAt(dateFormat.parse(this.getStartedAt()));
		} catch (ParseException e) {
			todo.setStartedAt(null);
		}*/
		
		return todo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	public String getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(String endedAt) {
		this.endedAt = endedAt;
	}
	
	
	
}
