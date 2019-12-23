package com.guestbook.web.vo;

import java.sql.Timestamp;

public class Note {
	private String email;
	private String title;
	private String context;
	private Timestamp createdDate;
	private Timestamp correctedDate;
	
 	public String getEmail() {
		return email;
	}
	public Note setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Note setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getContext() {
		return context;
	}
	public Note setContext(String context) {
		this.context = context;
		return this;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public Note setCreatedDate(Timestamp createDate) {
		this.createdDate = createDate;
		return this;
	}
	public Timestamp getCorrectedDate() {
		return correctedDate;
	}
	public Note setCorrectedDate(Timestamp correctedDate) {
		this.correctedDate = correctedDate;
		return this;
	}

}
