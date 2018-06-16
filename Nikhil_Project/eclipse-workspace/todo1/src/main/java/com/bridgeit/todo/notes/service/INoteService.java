package com.bridgeit.todo.notes.service;

import java.util.List;

import com.bridgeit.todo.notes.model.NoteRes;
import com.bridgeit.todo.notes.model.Notes;


public interface INoteService {

	public int createNote(Notes notes, int id);
	public void updateNote(Notes notes, int id);
	public int deleteNote(int id);
	List<NoteRes> getNotes(int id);
	
}
