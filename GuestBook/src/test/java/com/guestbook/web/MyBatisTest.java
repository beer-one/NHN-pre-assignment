package com.guestbook.web;


import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guestbook.web.dao.MemberDao;
import com.guestbook.web.dao.NoteDao;
import com.guestbook.web.service.NoteService;
import com.guestbook.web.vo.Member;
import com.guestbook.web.vo.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MyBatisTest {
	
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private NoteDao notedao;
	
	@Autowired
	private NoteService noteService;
	
	/*@Before
	public void memberAddTest() throws Exception {
		Member member = new Member().setEmail("aaaa@test.com")
				.setPassword("aaaa")
				.setName("aaaa");
		
		dao.addMember(member);
		member = member.setEmail("bbbb@test.com")
				.setPassword("bbbb")
				.setName("bbbb");
		dao.addMember(member);
		member = member.setEmail("cccc@test.com")
				.setPassword("cccc")
				.setName("cccc");
		dao.addMember(member);
		member = member.setEmail("dddd@test.com")
				.setPassword("dddd")
				.setName("dddd");
		dao.addMember(member);
	
		List<Member> members = dao.getAllMembers();
		
		assertEquals("aaaa", members.get(0).getName());
		assertEquals("bbbb", members.get(1).getName());
		assertEquals("cccc", members.get(2).getName());
		assertEquals("dddd", members.get(3).getName());
	}*/
	
	
	@Test
	public void memberFindTest() throws Exception {
		Map<String, String> params = new HashMap();
		params.put("email", "aaaa@test.com");
		params.put("password", "aaaa");
		
		Member m = dao.findByEmailAndPassword(params);
		
		assertEquals("aaaa@test.com", m.getEmail());
		
		params.replace("password", "bbbb");
		
		m = dao.findByEmailAndPassword(params);
		
		assertEquals(null, m);
		
		params.replace("email", "bbb@test.com");
		
		m = dao.findByEmailAndPassword(params);
		
		assertEquals(null, m);
		
	}
	
	@Test
	public void findEmailTest() throws Exception {
		String email = dao.findByEmail("aaaa@test.com");
		
		assertEquals("aaaa@test.com", email);
		
		email = dao.findByEmail("a@test.com");
		
		assertEquals(null, email);
	}
	/*
	@After
	public void deleteMemberTest() throws Exception {
		List<Member> members = dao.getAllMembers();
		
		for(Member m : members)
			dao.deleteMember(m);
		
		for(Member m : members) {
			String email = dao.findByEmail(m.getEmail());
			assertEquals(null, email);
		}
	}*/
	
	@Test
	public void selectAllNotes() {
		List<Note> notes = noteService.getAllNotes();
		
		for (Note n : notes) {
			System.out.println("title: " + n.getTitle() + ", context: " + n.getContext() + 
					", createdDate: " + n.getCreatedDate() + ", email: " + n.getEmail());
		}
	}
	
	@Test
	public void addNote() {
		Timestamp t = new Timestamp(new Date().getTime());
		
		System.out.println(t);
	}
	
	@Test
	public void emailFormCheckTest() {
		String email = "floidea@test.com";
		
		boolean result = checkEmail(email);
		
		assertEquals(true, result);
		
		
		email = "asb11@test@test.com";
		result = checkEmail(email);
		
		assertEquals(false, result);
	}
	public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
			"^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+"
	);

	private boolean checkEmail(String email) {
	    return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}


	

}
