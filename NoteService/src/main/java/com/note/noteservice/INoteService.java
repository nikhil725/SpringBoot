package com.note.noteservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.note.model.Label;
import com.note.model.Note;
import com.note.model.NoteDto;
import com.note.model.NotePreferences;
import com.note.model.Status;

public interface INoteService {

	void saveNote(NoteDto noteDto, String userId);
	void updateNote(Note note);
	void updatenotePref(NotePreferences notePref);
	void deleteNote(int noteId);
	List<NoteDto> getNotes(String loggedInUser);
	void saveLabel(Label label, String loggedInUserId);
	void renameLabel(Label label, String loggedInUserId);
	List<Label> getLabels();
	void deleteLabel(int labelId);
	void addRemoveLabel(int noteId, int labelId);
	void saveLabelFromNote(Label label, int noteId, String loggedInUserId);
	void saveImage(MultipartFile image, int noteId);
	void deleteImage(int userId, int noteId, String key);
	void collaborat(String sharingUserEmail, int noteId, String loggedInUserEmail);
	void removeCollaboratUser();
	void pinOrUnpin(int notePrefId, boolean isPinned, String loggedInUserId);
	void archiveOrUnarchive(int notePrefId, Status status, String loggedInUserId);
	void trashOrRestore(int notePrefId, Status status, String loggedInUserId);
	
	
	
}
