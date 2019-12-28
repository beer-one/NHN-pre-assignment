package com.guestbook.web;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guestbook.web.dao.MemberDao;
import com.guestbook.web.exception.LoginFailException;
import com.guestbook.web.service.MemberService;
import com.guestbook.web.vo.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberDao memberDao;
	
	/*
	 * 회원가입 테스트 겸 테스트용 데이터를 넣기 위한 테스트입니다.
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
	 * 로그인할 때 테스트입니다.
	 * 로그인 성공 시 회원정보가 반환되고 실패 시 예외가 발생합니다. 
	 */
	@Test
	public void Test_loginTest() {
		// 로그인 성공 테스
		try {
			Member member = memberService.login("aaaa@test.com", "aaaa");
			assertEquals("aaaa", member.getName());
			
			member = memberService.login("bbbb@test.com", "bbbb");
			assertEquals("bbbb", member.getName());
			
			member = memberService.login("cccc@test.com", "cccc");
			assertEquals("cccc", member.getName());
			
			member = memberService.login("dddd@test.com", "dddd");
			assertEquals("dddd", member.getName());
		} catch (LoginFailException e) { }
		
		// 로그인 실패 테스트
		boolean result;
		try {
			memberService.login("aaaa@test.com", "aaaaa");
			result = true;
		} catch (LoginFailException e) {
			result = false;
		}
		assertEquals(false, result);
		
		try {
			memberService.login("aaaaa@test.com", "aaaa");
			result = true;
		} catch (LoginFailException e) {
			result = false;
		}
		assertEquals(false, result);
	}
	
	/*
	 * 이메일 형식이 올바른지 체크하는 테스트입니다.
	 */
	@Test
	public void Test_emailFormCheckTest() {
		boolean check = memberService.emailCheck("aaaa@test.com");
		assertEquals(true, check);
		
		check = memberService.emailCheck("aaa123@test1.com");
		assertEquals(true, check);
		
		check = memberService.emailCheck("my.working.email@test.com");
		assertEquals(true, check);
		
		check = memberService.emailCheck("aaa@test@com");
		assertEquals(false, check);
		
		check = memberService.emailCheck("aaaa1.test.com");
		assertEquals(false, check);
		
		check = memberService.emailCheck("가나@다라마.바사");
		assertEquals(false, check);
	}
	
	/*
	 * 회원가입을 할 때, 중복된 이메일이 있는지 테스트하는 구간입니다.
	 */
	@Test
	public void Test_duplicatedEmailCheckTest() {
		boolean check = memberService.isDuplicateEmail("aaaa@test.com");
		assertEquals(true, check);
		
		check = memberService.isDuplicateEmail("bbbb@test.com");
		assertEquals(true, check);
		
		check = memberService.isDuplicateEmail("cccc@test.com");
		assertEquals(true, check);
		
		check = memberService.isDuplicateEmail("dddd@test.com");
		assertEquals(true, check);
		
		check = memberService.isDuplicateEmail("eeee@test.com");
		assertEquals(false, check);
		
		check = memberService.isDuplicateEmail("ffff@test.com");
		assertEquals(false, check);
		
		check = memberService.isDuplicateEmail("gggg@test.com");
		assertEquals(false, check);
	}
	
	/*
	 * 테스트 데이터를 데이터베이스에서 지우는 역할을 합니다.
	 */
	@Test
	public void final_deleteMemberTest() {
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
