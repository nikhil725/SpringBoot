package com.bridgeit.todo.notes.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.print.DocFlavor.BYTE_ARRAY;

import com.bridgeit.todo.collaborator.model.Collaborator;
import com.bridgeit.todo.label.model.Label;
import com.bridgeit.todo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Notes")
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int noteId;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Trash")
	private Boolean trash = false;
	
	@Column(name="Archive")
	private Boolean archive = false;
	
	@Column(name="Pin")
	private Boolean pin = false;
	
	@Column
	private String color;
	
	@Column
	private Date reminder;
	
	@Lob
	@Column
	private byte[]image;
	
	private String collaboratorName;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id")
	private User user;

	@ManyToMany
	@JoinTable(name ="NoteLabel", joinColumns=@JoinColumn(name="noteId"), inverseJoinColumns=@JoinColumn(name="labelId"))
	private Set<Label> labels;
	
	@JsonIgnore
	@OneToMany(mappedBy="noteId")
	private Set<Collaborator> collaborators;

	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public Boolean getTrash() {
		return trash;
	}

	public void setTrash(Boolean trash) {
		this.trash = trash;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
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

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public Set<Collaborator> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(Set<Collaborator> collaborators) {
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
