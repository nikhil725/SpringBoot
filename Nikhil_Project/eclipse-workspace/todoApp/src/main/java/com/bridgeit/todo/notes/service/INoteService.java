package com.bridgeit.todo.notes.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bridgeit.todo.notes.model.NoteRes;
import com.bridgeit.todo.notes.model.Notes;


public interface INoteService {
	
	public int createNote(Notes notes, int id);
	public void updateNote(Notes notes, int id);
	public int deleteNote(int noteId);
	List<NoteRes> getNotes(int id);
	void saveImage(MultipartFile fileUpload, int noteId) throws IOException;
	void deleteImage(int noteId);
}
