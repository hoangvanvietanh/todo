package com.green.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.spring.entity.Messages;
import com.green.spring.entity.User;
import com.green.spring.service.MessagesServices;
import com.green.spring.service.ToDoServices;
import com.green.spring.service.UserService;

@Controller
@SessionAttributes("idFriend")
@RequestMapping("/messages")
public class MessagesController {
	@Autowired
	private UserService userService;

	@Autowired
	private MessagesServices messService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(@ModelAttribute("idFriend") int idF, Model model) {

		User user = userService.findByEmail(userService.getEmailUser());
		int id = user.getId();
		String name = user.getName();
		
		List<Messages> mes = messService.findByUser(id, idF);
		model.addAttribute("mes", mes);
		model.addAttribute("name", name);
		return "messages";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String sendMess(@ModelAttribute("mess") String mess,@ModelAttribute("idFriend") int idF ,Model model) {
		Messages messenger = new Messages();
		messenger.setUser1(userService.findByEmail(userService.getEmailUser()));
		messenger.setUser2(userService.findUser(idF));
		messenger.setMessages(mess);
		messService.createMess(messenger);
		
		return "redirect:/messages";
	}
}
