package com.guestbook.web.dao;

import java.util.List;

import com.guestbook.web.vo.Note;

public interface NoteDao {
	List<Note> getAllNotes();
	void addNote(Note note);
}
