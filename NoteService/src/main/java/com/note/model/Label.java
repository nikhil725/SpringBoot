package com.note.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Label {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int LabelId;
	
	@Column
	private String name;
	
	@Column
	private String userId;
	
	@ManyToMany(mappedBy = "labels")
	private Set<NotePreferences> notePref;

	

	public int getLabelId() {
		return LabelId;
	}

	public void setLabelId(int labelId) {
		LabelId = labelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
