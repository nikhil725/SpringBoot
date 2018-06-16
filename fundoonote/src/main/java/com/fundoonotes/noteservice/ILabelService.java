package com.fundoonotes.noteservice;

import java.util.List;

public interface ILabelService {

	void createLabel(Label label, int userId);
	void deleteLabel(int labelId);
	List<LabelResDto> getLabels(int userId);
	void updateLabel(Label label, int userId);
	void addLabelOnNote(int noteId, int labelId);
	void deleteLabelFromNote(int noteId, int labelId);
}
