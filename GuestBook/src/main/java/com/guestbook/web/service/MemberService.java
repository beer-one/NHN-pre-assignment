package com.guestbook.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guestbook.web.dao.MemberDao;
import com.guestbook.web.exception.LoginFailException;
import com.guestbook.web.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public Member login(String email, String password) throws LoginFailException {
		Map<String, String> params = new HashMap();
		params.put("email", email);
		params.put("password", password);
		Member m = memberDao.findByEmailAndPassword(params);
		
		if(m == null) throw new LoginFailException("존재하지 않는 이메일이거나 비밀번호가 일치하지 않습니다.");
		return m;
	}
	
	public boolean isExist(String email, String password) {
		Map<String, String> params = new HashMap();
		params.put("email", email);
		params.put("password", password);
		Member m = memberDao.findByEmailAndPassword(params);
		
		return m != null;
	}
	
	public boolean isDuplicateEmail(String email) {
		return memberDao.findByEmail(email) != null;
	}
	
	public void signup(String name, String email, String password) {
		Member m = new Member().setName(name)
				.setEmail(email)
				.setPassword(password);
		
		memberDao.addMember(m);
	}

}
