package com.green.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;
	
	@GetMapping
	public String index() {
		return "redirect:/login";
	}
	
	@GetMapping(value = "/login")
	public String login(ModelMap model) {
		// check login status
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Ten La:::::::::::::::::::::::::" + authentication.getName());
		if (authenticationTrustResolver.isAnonymous(authentication)) {
			System.out.println("vao if");
			return "login";
		}
		return "redirect:/user";
		//return "redirect:/messages";
		//return "redirect:/friends";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout=true";
    }
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        return "denied";
    }
}