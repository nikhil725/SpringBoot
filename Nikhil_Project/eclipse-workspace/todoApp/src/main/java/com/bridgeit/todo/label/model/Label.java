package com.bridgeit.todo.label.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Label {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column
	private int labelId;
	
	@Column
	private String name;

	public int getLabelId() {
		return labelId;
	}

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User user;
	
	@ManyToMany(mappedBy = "labels")
	@JsonIgnore
	private Set<Notes> notes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Notes> getNotes() {
		return notes;
	}

	public void setNotes(Set<Notes> notes) {
		this.notes = notes;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	
}
