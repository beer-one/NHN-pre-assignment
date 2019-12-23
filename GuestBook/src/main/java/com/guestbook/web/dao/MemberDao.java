package com.guestbook.web.dao;

import java.util.List;
import java.util.Map;

import com.guestbook.web.vo.Member;

public interface MemberDao {
	List<Member> getAllMembers();
	Member findByEmailAndPassword(Map<String, String> params);
	String findByEmail(String email);
	String findEmailByNo(int no);
	void addMember(Member member);
	void deleteMember(Member member);
}
