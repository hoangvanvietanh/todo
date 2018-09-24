package com.green.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.spring.entity.ToDo;
import com.green.spring.entity.User;
import com.green.spring.model.ToDoModel;
import com.green.spring.model.UserModel;
import com.green.spring.service.ToDoServices;
import com.green.spring.service.UserService;

@Controller
@SessionAttributes("email")
@RequestMapping("/todo")
public class ToDoController {
	@Autowired
	private ToDoServices toDoServices;
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(name="numberPage", defaultValue="1") int page,@ModelAttribute("email") String email, BindingResult result, Model model,
			@ModelAttribute("act") String act,RedirectAttributes redirectAttributes,HttpServletRequest req) {
		System.out.println("Hello viet anh no co vao ne::::::::::::::::::::::::::::::::::::::::::::");
		
		if(email.equals("null"))
		{
			return "redirect:/login"; 
		}
		
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now1 = LocalDateTime.now();
		String time = dtf1.format(now1);
		
		System.out.println("User::::::::::::::::::::::::::::::::::;" +email);
		int id = userService.findByEmail(email).getId();
		List<ToDo> todo = toDoServices.findByuser(id, page);
		for(ToDo t:todo)
		{
			if(t.getStartDate().compareTo(time)<=0&&t.getStatus().equals("New1"))
			{
				t.setStatus("New2");
				toDoServices.updateToDo(t);
			}
		}
		int num= (int)toDoServices.getNumberPage(id);
		int i = 0;
		if(page>1)
		{
			i =(int)(10*page)-10;
		}
		model.addAttribute("time", time);
		model.addAttribute("page", page);
		model.addAttribute("page2", page);
		model.addAttribute("page3", page);
		model.addAttribute("i", i);
		model.addAttribute("num", num);
		model.addAttribute("num2", num);
		model.addAttribute("todo", todo);
		model.addAttribute("id", id);
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
	public String handleCreate(@ModelAttribute("todo") ToDoModel todo, BindingResult result, Model model,
			@ModelAttribute("email") String email, RedirectAttributes redirectAttributes) throws ParseException {
		if (result.hasErrors()) {
			return "create";
		}
		User user = userService.findByEmail(email);
		ToDo t = todo.toToDo();
		t.setUser(user);
		toDoServices.createToDo(t);

		return "redirect:/todo";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam(name = "id") int id, @RequestParam(name = "action") String action, Model model)
			throws ParseException {

		ToDo c = toDoServices.findToDo(id);
		if (c == null) {
			return "redirect:/todo";
		}
		ToDoModel toDoModel = new ToDoModel();
		toDoModel.formtoDo(c);

		model.addAttribute("todo", toDoModel);
		model.addAttribute("c", c);
		model.addAttribute("mode", "update");

		if (action.equals("edit")) {
			return "edit";
		} else if (action.equals("delete")) {
			toDoServices.deleteToDo(toDoServices.findToDo(id));
			return "redirect:/todo";
		}
		return "view";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String handleUpdate(@RequestParam(name = "id") int id,@ModelAttribute("email") String email, @ModelAttribute("todo") ToDo todo,
			BindingResult result, Model model) throws ParseException {

		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now1 = LocalDateTime.now();
		String time1 = dtf1.format(now1);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf1.parse(todo.getStartDate());
		Date date2 = sdf1.parse(time1);
		todo.setCreatedAt(time1);

		User user = userService.findByEmail(email);
		todo.setUser(user);
		if (date1.compareTo(date2) > 0) {
			todo.setStatus("New1");
		} else {
			todo.setStatus("New2");
		}

		toDoServices.updateToDo(todo);

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo";
	}
 
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam(name = "id") int id,  Model model)
			throws ParseException {

		ToDo c = toDoServices.findToDo(id);
		if (c == null) {
			return "redirect:/todo";
		}
		ToDoModel toDoModel = new ToDoModel();
		toDoModel.formtoDo(c);

		model.addAttribute("todo", toDoModel);
		model.addAttribute("c", c);
		model.addAttribute("mode", "update");

		return "view";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(name = "id") int id, Model model)
			throws ParseException {

		ToDo c = toDoServices.findToDo(id);
		if (c == null) {
			return "redirect:/todo";
		}
		ToDoModel toDoModel = new ToDoModel();
		toDoModel.formtoDo(c);

		model.addAttribute("todo", toDoModel);
		model.addAttribute("c", c);
		model.addAttribute("mode", "update");

		
			return "edit";
	}
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public String handleStart(@RequestParam(name = "id") int id, @ModelAttribute("todo") ToDo todo,
			BindingResult result, @RequestParam(name="number") int page,Model model) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);
		ToDo toDo = toDoServices.findToDo(id);
		toDo.setStartedAt(time);
		toDo.setStatus("In-progress");
		toDoServices.updateToDo(toDo);

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo?id="+id+"&numberPage="+page;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public String handleCancel(@RequestParam(name = "id") int id, @RequestParam(name="number") int page,@ModelAttribute("todo") ToDo todo,
			BindingResult result, Model model) throws ParseException {
		ToDo toDo = toDoServices.findToDo(id);
		toDo.setStatus("Canceled");
		toDoServices.updateToDo(toDo);

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo?id="+id+"&numberPage="+page;
	}

	@RequestMapping(value = "/end", method = RequestMethod.POST)
	public String handleEnd(@RequestParam(name = "id") int id, @RequestParam(name="number") int page,@ModelAttribute("todo") ToDo todo, BindingResult result,
			Model model) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);

		ToDo toDo = toDoServices.findToDo(id);
		toDo.setEndedAt(time);
		toDo.setStatus("Done");
		toDoServices.updateToDo(toDo);

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo?id="+id+"&numberPage="+page;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String handleDelete(@RequestParam(name = "id") int id,@RequestParam(name="number") int page, @ModelAttribute("todo") ToDo todo,
			 BindingResult result, Model model) throws ParseException {

		toDoServices.deleteToDo(toDoServices.findToDo(id));

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo?id="+id+"&numberPage="+page;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String handleEdit(@RequestParam(name = "id") int id, @ModelAttribute("email") String email,@RequestParam(name="number") int page,@ModelAttribute("todo") ToDo todo,
			 BindingResult result, Model model) throws ParseException {
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now1 = LocalDateTime.now();
		String time1 = dtf1.format(now1);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf1.parse(todo.getStartDate());
		Date date2 = sdf1.parse(time1);
		todo.setCreatedAt(time1);

		if (date1.compareTo(date2) > 0) {
			todo.setStatus("New1");
		} else {
			todo.setStatus("New2");
		}
		User user = userService.findByEmail(email);
		todo.setUser(user);
		toDoServices.updateToDo(todo);

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo?id="+id+"&numberPage="+page;
	}

}