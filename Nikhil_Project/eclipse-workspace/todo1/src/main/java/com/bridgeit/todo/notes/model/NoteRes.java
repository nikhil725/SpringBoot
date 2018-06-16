package com.bridgeit.todo.notes.model;

public class NoteRes {

	private int noteId;
	private String title;
	private String description;

	public NoteRes(Notes object) {
		
		this.noteId= object.getNoteId();
		this.title = object.getTitle();
		this.description=object.getDescription();
		
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
