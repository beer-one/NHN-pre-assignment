package com.guestbook.web;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guestbook.web.dao.MemberDao;
import com.guestbook.web.dao.NoteDao;
import com.guestbook.web.service.MemberService;
import com.guestbook.web.service.NoteService;
import com.guestbook.web.vo.Member;
import com.guestbook.web.vo.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class NoteTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	NoteService noteService;
	
	@Autowired
	NoteDao noteDao;
	
	/*
	 * 테스트용 데이터를 데이터베이스에 넣는 역할을 합니다.
	 */
	@Test
	public void Init_signUpTest() {
		memberService.signup("aaaa", "aaaa@test.com", "aaaa");
		memberService.signup("bbbb", "bbbb@test.com", "bbbb");
		memberService.signup("cccc", "cccc@test.com", "cccc");
		memberService.signup("dddd", "dddd@test.com", "dddd");
		
		boolean exist = memberService.isExist("aaaa@test.com", "aaaa");
		assertEquals(true, exist);
		
		exist = memberService.isExist("bbbb@test.com", "bbbb");
		assertEquals(true, exist);
		
		exist = memberService.isExist("cccc@test.com", "ccc");
		assertEquals(false, exist);
		
		exist = memberService.isExist("ddd@test.com", "dddd");
		assertEquals(false, exist);
		
		exist = memberService.isExist("cccc@test.com", "cccc");
		assertEquals(true, exist);
		
		exist = memberService.isExist("dddd@test.com", "dddd");
		assertEquals(true, exist);
	}
	
	/*
	 * 방명록 글을 데이터베이스에 넣는 테스트입니다.
	 * 1초 간격으로 데이터를 추가하고 마지막에 추가된 데이터가 먼저 출력하는 것을 테스트하였습니다.
	 */
	@Test
	public void Test_addNoteTest() throws Exception {
		Note note = new Note().setEmail("aaaa@test.com")
				.setTitle("aaaaTitle")
				.setContext("aaaaContext")
				.setCreatedDate(new Timestamp((new Date()).getTime()))
				.setCorrectedDate(new Timestamp((new Date()).getTime()));
		noteService.saveNote(note);

		Thread.sleep(2000);
		
		note = new Note().setEmail("bbbb@test.com")
				.setTitle("bbbbTitle")
				.setContext("bbbbContext")
				.setCreatedDate(new Timestamp((new Date()).getTime()))
				.setCorrectedDate(new Timestamp((new Date()).getTime()));
		noteService.saveNote(note);
		
		Thread.sleep(2000);
		
		note = new Note().setEmail("cccc@test.com")
				.setTitle("ccccTitle")
				.setContext("ccccContext")
				.setCreatedDate(new Timestamp((new Date()).getTime()))
				.setCorrectedDate(new Timestamp((new Date()).getTime()));
		noteService.saveNote(note);
		
		Thread.sleep(1000);
		
		note = new Note().setEmail("dddd@test.com")
				.setTitle("ddddTitle")
				.setContext("ddddContext")
				.setCreatedDate(new Timestamp((new Date()).getTime()))
				.setCorrectedDate(new Timestamp((new Date()).getTime()));
		noteService.saveNote(note);
		
		List<Note> notes = noteDao.getAllNotes();
		
		assertEquals("ddddTitle", notes.get(0).getTitle());
		assertEquals("ccccTitle", notes.get(1).getTitle());
		assertEquals("bbbbTitle", notes.get(2).getTitle());
		assertEquals("aaaaTitle", notes.get(3).getTitle());
	}
	
	/*
	 * 방명록 글을 수정하는 테스트입니다.
	 * 1초 후 글을 수정한 다음, 글의 제목과 글의 본문과 수정시간이 변경되는지 테스트합니다.
	 */
	@Test
	public void Test_correctNoteTest() throws Exception {
		List<Note> notes = noteDao.getAllNotes();
		Thread.sleep(1000);
		for (Note n : notes) {
			n = n.setTitle(n.getTitle() + "_correct");
			n = n.setContext(n.getContext() + "_correct");
			
			noteService.updateNote(n);
			
			Note corrected = noteDao.findByNo(n.getNo());
			
			assertEquals(n.getTitle(), corrected.getTitle());
			assertEquals(n.getContext(), corrected.getContext());
			boolean compareResult = corrected.getCreatedDate().getTime() < corrected.getCorrectedDate().getTime();
			assertEquals(true, compareResult);
		}
	}
	
	/*
	 * 테스트 데이터를 데이터베이스에서 지우는 역할을 합니다.
	 */
	@Test
	public void final_deleteTest() {
		List<Note> notes = noteDao.getAllNotes();
		
		for (Note n : notes) {
			noteDao.deleteNote(n);
		}
		
		for (Note n : notes) {
			Note note = noteDao.findByNo(n.getNo());
			assertEquals(null, note);
		}
		
		Member[] members = new Member[4];
		
		members[0] = new Member().setEmail("aaaa@test.com").setPassword("aaaa");
		members[1] = new Member().setEmail("bbbb@test.com").setPassword("bbbb");
		members[2] = new Member().setEmail("cccc@test.com").setPassword("cccc");
		members[3] = new Member().setEmail("dddd@test.com").setPassword("dddd");
		
		for(int i = 0; i < 4; i++) {
			memberDao.deleteMember(members[i]);
		}
		
		boolean exist;
		for(int i = 0; i < 4; i++) {
			exist = memberService.isExist(members[i].getEmail(), members[i].getPassword());
			
			assertEquals(false, exist);
		}
		
	}

}
