package com.bridgeit.todo.label.service;

import java.util.List;

import com.bridgeit.todo.label.model.Label;
import com.bridgeit.todo.label.model.LabelRes;

public interface ILabelService {
	
	public String createLabel(Label label, int userID);
	List<LabelRes> getLabels(int userId);
	public void addLabel(int noteId, int labelId);
//	public void deleteLabel(int labelId, String token);
	public void deleteLabelFromNote(int noteId, int labelId);
	void deleteLabel(int labelId);
	
	
}
