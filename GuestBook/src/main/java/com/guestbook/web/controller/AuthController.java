package com.guestbook.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guestbook.web.exception.LoginFailException;
import com.guestbook.web.service.MemberService;
import com.guestbook.web.vo.Member;

@Controller
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/auth/signup", method = RequestMethod.GET)
	public String signupForm(HttpServletRequest request, HttpServletResponse response) {
		
		return "auth/Signup";
	}
	
	@RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(memberService.isDuplicateEmail(email)) 
			model.addAttribute("result", "email");
		
		else if(password.equals(request.getParameter("password-check"))) {
			memberService.signup(name, email, password);
			model.addAttribute("result", "success");	
		} else 
			model.addAttribute("result", "password");
			
		
		return "auth/SignupResult";
	}
	
	@RequestMapping(value = "/auth/login", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request, HttpServletResponse response) {
		return "auth/Login";
	}
	
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Member member = memberService.login(email, password);
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			
			return "redirect:/";
			
		}  catch (LoginFailException e) {
			return "auth/LoginFail";
		}
	}
	
	@RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		session.invalidate();
		return "redirect:/";
	}
	
	
	
}
