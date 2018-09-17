package com.green.spring.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.spring.entity.ToDo;
import com.green.spring.entity.User;
import com.green.spring.model.UserModel;
import com.green.spring.service.HomeService;
import com.green.spring.service.UserService;
import com.mysql.cj.Session;

@Controller
@RequestMapping("/login")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private HomeService homeService;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public String list(Model model)
	{
		List<User> user = userService.findAll();
		model.addAttribute("user", user);
		return "login";
	}*/
	@RequestMapping(method = RequestMethod.GET)
	public String create(Model model) {
		UserModel user = new UserModel();
		model.addAttribute("login", user);
		model.addAttribute("mode", "login");
		return "login";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String handleCreate(@ModelAttribute("login") UserModel user, 
			BindingResult result, Model model,RedirectAttributes redirectAttributes) throws ParseException {
		
		// validate inputed contact info and convert to entity
		if (result.hasErrors()) {
			return "create";
		}
		
		// save contact info
		String email =user.getEmail();
		String password = user.getPassword();
		Boolean checkLogin = userService.checkLogin(email, password);
	    redirectAttributes.addFlashAttribute("email", email);

		if(checkLogin==true)
		{
			//System.out.println("Id la: " + id);
			
			return "redirect:/todo";
			//return "redirect:/todo";
		}
		// back to contact list page
		return "redirect:/login";
	}
}
