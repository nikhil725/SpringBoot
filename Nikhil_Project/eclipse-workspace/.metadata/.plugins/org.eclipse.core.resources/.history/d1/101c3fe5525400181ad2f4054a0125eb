package com.bridgeit.todo.notes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bridgeit.todo.collaborator.model.Collaborator;
import com.bridgeit.todo.collaborator.model.CollaboratorRes;
import com.bridgeit.todo.label.model.Label;
import com.bridgeit.todo.label.model.LabelRes;

public class NoteRes {

	private int noteId;
	private String title;
	private String description;
	private Boolean trash;
	private Boolean archive;
	private Boolean pin;
	private String color;
	private Date reminder;
	private String collaboratorName;
	private List<LabelRes> labels = new ArrayList<>();
	private List<CollaboratorRes> collaborators = new ArrayList<>();
	private byte[] image;

	public NoteRes(Notes object) {

		this.noteId = object.getNoteId();
		this.title = object.getTitle();
		this.description = object.getDescription();
		this.trash = object.getTrash();
		this.archive = object.getArchive();
		this.pin = object.getPin();
		this.color = object.getColor();
		this.reminder = object.getReminder();
		this.collaboratorName =object.getCollaboratorName();
		this.image=object.getImage();
		
		for (Label label : object.getLabels()) {
			labels.add(new LabelRes(label));
		}
		
		for(Collaborator collaborator : object.getCollaborators()) {
			collaborators.add(new CollaboratorRes(collaborator));
		}

	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
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

	public Boolean getTrash() {
		return trash;
	}

	public void setTrash(Boolean trash) {
		this.trash = trash;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	public Boolean getPin() {
		return pin;
	}

	public void setPin(Boolean pin) {
		this.pin = pin;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<LabelRes> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelRes> labels) {
		this.labels = labels;
	}

	public List<CollaboratorRes> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<CollaboratorRes> collaborators) {
		this.collaborators = collaborators;
	}

	public String getCollaboratorName() {
		return collaboratorName;
	}

	public void setCollaboratorName(String collaboratorName) {
		this.collaboratorName = collaboratorName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	

}
