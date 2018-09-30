package com.green.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.green.spring.entity.Friends;
import com.green.spring.service.FriendsServices;
import com.green.spring.service.UserService;

@Controller
@SessionAttributes("idFriend")
@RequestMapping("/friends")
public class FriendsController {
	@Autowired
	private UserService userServices;
	
	@Autowired
	private FriendsServices friendsServices;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list( Model model) {
		List<Friends> friend = friendsServices.findByIdUser(userServices.findByEmail(userServices.getEmailUser()).getId());
		model.addAttribute("friend", friend);
		return "messages";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String selectFriends(@ModelAttribute("idFriends") int id,ModelMap model) {
		model.put("idFriend", id);
		return "redirect:/messages";
	}
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addFriends(@ModelAttribute("mess") String mess,@ModelAttribute("idFriends") int id,Model model) {
		Friends friends = new Friends();
		friends.setUser1(userServices.findByEmail(userServices.getEmailUser()));
		friends.setUser2(userServices.findUser(id));
		friendsServices.addFriends(friends);
		
		return "redirect:/friends";
	}
}
