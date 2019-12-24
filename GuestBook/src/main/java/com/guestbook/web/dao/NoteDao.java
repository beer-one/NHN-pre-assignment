package com.guestbook.web.dao;

import java.util.List;

import com.guestbook.web.vo.Note;

public interface NoteDao {
	List<Note> getAllNotes();
	Note findByNo(int no);
	void addNote(Note note);
	void updateNote(Note note);
}
