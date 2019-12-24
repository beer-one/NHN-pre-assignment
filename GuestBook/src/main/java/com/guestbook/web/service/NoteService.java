package com.guestbook.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.guestbook.web.dao.MemberDao;
import com.guestbook.web.dao.NoteDao;
import com.guestbook.web.vo.Note;

public class NoteService {
	
	@Autowired
	private NoteDao noteDao;
	
	public List<Note> getAllNotes() {
		return noteDao.getAllNotes();
	}
	
	public void saveNote(Note note) {
		noteDao.addNote(note);
	}
	
	public Note findNote(int no) {
		return noteDao.findByNo(no);
	}
	
	public void updateNote(Note note) {
		noteDao.updateNote(note);
	}
}
