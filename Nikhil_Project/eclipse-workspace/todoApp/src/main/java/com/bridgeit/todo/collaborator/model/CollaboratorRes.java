package com.bridgeit.todo.collaborator.model;

import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.user.model.User;

public class CollaboratorRes {

	
	private int collaboratorId;
	private Notes noteId;
	private User ownerId;
	private User sharedUserId;
	
	
	public CollaboratorRes(Collaborator object) {
	
		this.collaboratorId = object.getCollaboratorId();
		this.noteId = object.getNoteId();
		this.ownerId = object.getOwnerId();
		this.sharedUserId = object.getSharedUserId();
		
	}
	public int getCollaboratorId() {
		return collaboratorId;
	}
	public void setCollaboratorId(int collaboratorId) {
		this.collaboratorId = collaboratorId;
	}
	public Notes getNotes() {
		return noteId;
	}
	public void setNotes(Notes noteId) {
		this.noteId = noteId;
	}
	public User getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}
	public User getSharedUserId() {
		return sharedUserId;
	}
	public void setSharedUserId(User sharedUserId) {
		this.sharedUserId = sharedUserId;
	}
	
	
}
