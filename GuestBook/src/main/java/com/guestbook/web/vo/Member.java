package com.guestbook.web.vo;

public class Member {
	private String name;
	private String email;
	private String password;
	
	public String getName() { return name; }
	
	public Member setName(String name) { this.name = name; return this; }
	
	public String getPassword() { return password; }
	
	public Member setPassword(String password) { this.password = password; return this; }
	
	public String getEmail() { return email; }
	
	public Member setEmail(String email) { this.email = email; return this; }
}
