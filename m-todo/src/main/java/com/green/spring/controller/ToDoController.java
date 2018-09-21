package com.green.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.spring.entity.ToDo;
import com.green.spring.entity.User;
import com.green.spring.model.ToDoModel;
import com.green.spring.model.UserModel;
import com.green.spring.service.HomeService;
import com.green.spring.service.UserService;
import com.mysql.cj.Query;

import utils.Page;

@Controller
@RequestMapping("/todo")
@SessionAttributes("user")
public class ToDoController {
	@Autowired
	private HomeService homeService;
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(name="numberPage", defaultValue="1") int page,@ModelAttribute("userLogin") UserModel user, BindingResult result, Model model,
			@ModelAttribute("email") String email, RedirectAttributes redirectAttributes, HttpServletRequest req) {
		int id = userService.findByEmail("vietanh").getId();
		List<ToDo> todo = homeService.findByuser(id, page);
		//Page<ToDo > todo = homeService.getPage(id, page);
		double num= homeService.getNumberPage(id);
		model.addAttribute("num", num);
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

		// validate inputed contact info and convert to entity
		if (result.hasErrors()) {
			return "create";
		}
		// redirectAttributes.addFlashAttribute("email", email);
		// save contact info
		User user = userService.findByEmail("vietanh");
		ToDo t = todo.toToDo();
		t.setUser(user);
		homeService.createToDo(t);

		// back to contact list page
		return "redirect:/todo";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam(name = "id") int id, @RequestParam(name = "action") String action, Model model)
			throws ParseException {

		ToDo c = homeService.findToDo(id);
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
			homeService.deleteToDo(homeService.findToDo(id));
			return "redirect:/todo";
		}
		return "view";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String handleUpdate(@RequestParam(name = "id") int id, @ModelAttribute("todo") ToDo todo,
			BindingResult result, Model model) throws ParseException {

		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now1 = LocalDateTime.now();
		String time1 = dtf1.format(now1);

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf1.parse(todo.getStartDate());
		Date date2 = sdf1.parse(time1);
		todo.setCreatedAt(time1);

		User user = userService.findByEmail("vietanh");
		todo.setUser(user);
		if (date1.compareTo(date2) > 0) {
			todo.setStatus("New1");
		} else {
			todo.setStatus("New2");
		}

		homeService.updateToDo(todo);

		// validate inputed contact info and convert to entity
		if (result.hasErrors()) {
			return "home";
		}

		// save contact info

		// back to contact list page
		return "redirect:/todo";
	}
 
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(@RequestParam(name = "id") int id,  Model model)
			throws ParseException {

		ToDo c = homeService.findToDo(id);
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

		ToDo c = homeService.findToDo(id);
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
			BindingResult result, Model model) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);

		ToDo toDo = homeService.findToDo(id);
		toDo.setStartedAt(time);
		toDo.setStatus("In-progress");
		homeService.updateToDo(toDo);

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public String handleCancel(@RequestParam(name = "id") int id, @ModelAttribute("todo") ToDo todo,
			BindingResult result, Model model) throws ParseException {
		ToDo toDo = homeService.findToDo(id);
		toDo.setStatus("Canceled");
		homeService.updateToDo(toDo);

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo";
	}

	@RequestMapping(value = "/end", method = RequestMethod.POST)
	public String handleEnd(@RequestParam(name = "id") int id, @ModelAttribute("todo") ToDo todo, BindingResult result,
			Model model) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String time = dtf.format(now);

		ToDo toDo = homeService.findToDo(id);
		toDo.setEndedAt(time);
		toDo.setStatus("Done");
		homeService.updateToDo(toDo);

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String handleDelete(@RequestParam(name = "id") int id, @ModelAttribute("todo") ToDo todo,
			 BindingResult result, Model model) throws ParseException {

		homeService.deleteToDo(homeService.findToDo(id));

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String handleEdit(@RequestParam(name = "id") int id, @ModelAttribute("todo") ToDo todo,
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
		User user = userService.findByEmail("vietanh");
		todo.setUser(user);
		homeService.updateToDo(todo);

		if (result.hasErrors()) {
			return "home";
		}
		return "redirect:/todo";
	}

}