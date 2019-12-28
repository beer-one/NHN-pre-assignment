package com.guestbook.web.controller;

import java.util.Date;
import java.sql.Timestamp;

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

import com.guestbook.web.service.MemberService;
import com.guestbook.web.service.NoteService;
import com.guestbook.web.vo.Member;
import com.guestbook.web.vo.Note;

@Controller
public class NoteController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private NoteService noteService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/note/form", method = RequestMethod.GET)
	public String noteForm(HttpServletRequest request, HttpServletResponse response) {
		return "note/Form";
	}
	
	@RequestMapping(value = "/note", method = RequestMethod.POST)
	public String saveNote(HttpServletRequest request, Model model) {
		String title = request.getParameter("title");
		String context = request.getParameter("context");
		String password = request.getParameter("password");
		String email = "";
		
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("member");
		if(m == null)
			email = request.getParameter("email");
		else
			email = m.getEmail();
		
		System.out.println("email : " + email + ", password: " + password);
		
		// 이메일 체크 (서버 부분)
		if (!memberService.emailCheck(email)) {
			model.addAttribute("note", null);
			model.addAttribute("reason", "emailForm");
		}
		else if(memberService.isExist(email, password)) {
			Note note = new Note().setEmail(email)
								.setTitle(title)
								.setContext(context)
								.setCreatedDate(new Timestamp((new Date()).getTime()))
								.setCorrectedDate(new Timestamp((new Date()).getTime()));
			
			model.addAttribute("note", note);
			noteService.saveNote(note);
			
		} else {
			model.addAttribute("note", null);
			model.addAttribute("reason", "password");
		}
		
		return "note/Check";
	}
	
	@RequestMapping(value = "/note", method = RequestMethod.GET)
	public String showNotePage(HttpServletRequest request, Model model) {
		int no = Integer.parseInt(request.getParameter("no"));
		Note note = noteService.findNote(no);
		
		model.addAttribute("note", note);
		
		return "note/NotePage";
	}
	
	@RequestMapping(value = "/note/correct", method = RequestMethod.GET)
	public String showCorrectNotePage(HttpServletRequest request, Model model) {
		int no = Integer.parseInt(request.getParameter("no"));
		Note note = noteService.findNote(no);
		
		model.addAttribute("note", note);
		
		return "note/Correct";
	}
	
	@RequestMapping(value = "/note/correct", method = RequestMethod.POST)
	public String correctNote(HttpServletRequest request, Model model) {
		int no = Integer.parseInt(request.getParameter("no"));
		Note note = noteService.findNote(no);
		
		String password = request.getParameter("password");
		
		if(memberService.isExist(note.getEmail(), password)) {
			note = note.setTitle(request.getParameter("title"))
					.setContext(request.getParameter("context"));
			
			noteService.updateNote(note);
			model.addAttribute("success", true);
		} else {
			model.addAttribute("success", false);
		}
		
		return "note/CorrectCheck";
		
	}
	
	
	
}
