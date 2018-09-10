package com.green.spring.controller;

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
			BindingResult result, Model model) {
		
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
	public String update(@RequestParam(name="id") int id,@RequestParam(name="action") String action, Model model) {
		
		System.out.println("hhhshhhhhhhhhhhhhhAc: " +action);
		ToDo c = homeService.findToDo(id);
		if (c == null) {
			return "redirect:/todo";
		}
		System.out.println("Vietttttttttttttttttttttttttttttttttttttttttttttttttttttt"+id);
		ToDoModel toDoModel = new ToDoModel();
		toDoModel.formtoDo(c);
		
		model.addAttribute("contact", toDoModel);
		model.addAttribute("mode", "update");
		
		return "contact-detail";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String handleUpdate(@RequestParam(name="id") int id,
			@ModelAttribute("todo") ToDo todo, @RequestParam(name="action") String action,
			BindingResult result, Model model) {
		if(action.equals("start"))
		{
			
		}
		else if(action.equals("cancel"))
		{
			
		}
		else if(action.equals("view"))
		{
			
		}
		else
		{
			
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
