package com.fundoonotes.noteservice;


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

import com.fundoonotes.userservice.User;

@Entity
@Table(name="Label")
public class Label {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int labeld;
	
	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToMany(mappedBy = "labels")
	private Set<Note> note;
	
	public int getLabeld() {
		return labeld;
	}

	public void setLabeld(int labeld) {
		this.labeld = labeld;
	}

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

	public Set<Note> getNote() {
		return note;
	}

	public void setNote(Set<Note> note) {
		this.note = note;
	}
	
	
	
}
