package com.bridgeit.todo.collaborator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.user.model.User;

@Entity
@Table(name="Collaborator")
public class Collaborator {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private int collaboratorId;

	@ManyToOne
	@JoinColumn(name="ownerId")
	private User ownerId;
	
	@ManyToOne
	@JoinColumn(name="sharedUserId")
	private User sharedUserId;
	
	@ManyToOne
	@JoinColumn(name="noteId")
	private Notes noteId;
	
	public int getCollaboratorId() {
		return collaboratorId;
	}

	public void setCollaboratorId(int collaboratorId) {
		this.collaboratorId = collaboratorId;
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

	public Notes getNoteId() {
		return noteId;
	}

	public void setNoteId(Notes noteId) {
		this.noteId = noteId;
	}
}