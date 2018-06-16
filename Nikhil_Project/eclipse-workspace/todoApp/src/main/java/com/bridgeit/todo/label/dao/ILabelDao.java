package com.bridgeit.todo.label.dao;

import java.util.List;

import com.bridgeit.todo.label.model.Label;
import com.bridgeit.todo.user.model.User;

public interface ILabelDao {

	String createlabel(Label label);
	List<Label> getLabels(User user);
	void addLableOnNote(int noteId, int labelId);
	Label getLabelById(int id);
	void deleteLabel(int labelId);
	void deleteLabelOnNote(int labelId);
}
