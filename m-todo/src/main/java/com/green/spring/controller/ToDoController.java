package com.green.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.spring.entity.ToDo;
import com.green.spring.model.ToDoModel;
import com.green.spring.service.HomeService;

@Controller
@RequestMapping("/todo")
public class ToDoController {
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<ToDo> todo = homeService.findAll();
		for(ToDo t: todo)
		{
		
		}
		model.addAttribute("todo", todo);
		return "home";
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		ToDoModel todo = new ToDoModel();
		model.addAttribute("todo", todo);
		model.addAttribute("mode", "create");
		return "create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String handleCreate(@ModelAttribute("todo") ToDoModel todo, 
			BindingResult result, Model model) throws ParseException {
		
		// validate inputed contact info and convert to entity
		if (result.hasErrors()) {
			return "create";
		}
		
		// save contact info
		ToDo t = todo.toToDo();
		homeService.createToDo(t);
		
		// back to contact list page
		return "redirect:/todo";
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam(name="id") int id, Model model) {
		
		ToDo c = homeService.findToDo(id);
		if (c == null) {
			return "redirect:/todo";
		}
		ToDoModel toDoModel = new ToDoModel();
		toDoModel.formtoDo(c);
		
		model.addAttribute("todo", toDoModel);
		model.addAttribute("mode", "update");

		return "edit";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String handleUpdate(@RequestParam(name="id") int id,
			@ModelAttribute("todo") ToDo todo, @RequestParam(name="action") String action,
			BindingResult result, Model model) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);
		
		if(action.equals("start"))
		{
			ToDo toDo = homeService.findToDo(id);
			toDo.setStartedAt(time);
			toDo.setStatus("In-progress");
			homeService.updateToDo(toDo);
		}
		else if(action.equals("cancel"))
		{
			ToDo toDo = homeService.findToDo(id);
			toDo.setStatus("Canceled");
			homeService.updateToDo(toDo);
		}
		else if(action.equals("end"))
		{
			ToDo toDo = homeService.findToDo(id);
			toDo.setEndedAt(time);
			toDo.setStatus("Done");
			homeService.updateToDo(toDo);
		}
		else if(action.equals("view"))
		{
			
		}
		else if(action.equals("edit"))
		{
			homeService.updateToDo(todo);
		}
		else
		{
			homeService.deleteToDo(homeService.findToDo(id));
		}
		// validate inputed contact info and convert to entity
		if (result.hasErrors()) {
			return "home";
		}
		
		// save contact info
		
		// back to contact list page
		return "redirect:/todo";
	}
	
}
