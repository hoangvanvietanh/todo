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
import com.green.spring.model.ToDoModel;
import com.green.spring.model.UserModel;
import com.green.spring.service.UserService;

@Controller
@RequestMapping("/sign-up")
public class SignUpController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String create(Model model) {
		UserModel user = new UserModel();
		model.addAttribute("user", user);
		model.addAttribute("mode", "create");
		return "sign-up";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleCreate(@ModelAttribute("email") String email,@ModelAttribute("pass") String pass,@ModelAttribute("re_pass") String rePass, BindingResult result, Model model,
			 RedirectAttributes redirectAttributes) throws ParseException {
		if (result.hasErrors()) {
			return "sign-up";
		}
		List<User> userAll = userService.findAll();
		for(User u: userAll)
		{
			if (!email.equals(u.getEmail()))
			{
				if(pass.equals(rePass))
				{
					User user = new User();
					user.setEmail(email);
					user.setPassword(pass);
					userService.createUser(user);
					return "redirect:/login";
				}
				else
				{
					model.addAttribute("message", "Check your password");
					return "redirect:/sign-up";
				}
				
			}
		}
		
		model.addAttribute("message", "Email already");
		return "redirect:/sign-up";
		
	}
}
