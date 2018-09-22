package com.green.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.spring.entity.User;
import com.green.spring.model.UserModel;
import com.green.spring.service.UserService;

@Controller
@SessionAttributes("email")
public class LoginController {
	@Autowired
	private UserService userService;

	/*
	 * Add user in model attribute
	 */
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String index(Model model) {
		UserModel userModel = new UserModel();
		model.addAttribute("userLogin", userModel);
		model.addAttribute("mode", "login");
		return "login";
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("userLogin") UserModel userLogin, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		String email = userLogin.getEmail();
		String password = userLogin.getPassword();
		Boolean checkLogin = userService.checkLogin(email, password);
		//redirectAttributes.addFlashAttribute("email", email);

		if (checkLogin == true) {

			User users = userService.findByEmail(email);
			model.put("email", users.getEmail());
			return "redirect:/user";
		} else {
			model.addAttribute("message", "Login failed. Try again.");
			return "login";
		}
	}
}