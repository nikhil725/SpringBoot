package com.note.noteservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.note.dao.ICollaboratorDao;
import com.note.dao.ILabeDao;
import com.note.dao.INoteDao;
import com.note.dao.INotePrefDao;
import com.note.dao.IUserDao;
import com.note.model.Collaboration;
import com.note.model.Label;
import com.note.model.Note;
import com.note.model.NoteDto;
import com.note.model.NotePreferences;
import com.note.model.Status;
import com.note.model.User;
import com.note.utility.S3Service;

@Service
public class NoteServiceImp implements INoteService {

	@Autowired
	INoteDao noteDao;
	
	@Autowired
	ILabeDao labelDao;
	
	@Autowired
	INotePrefDao notePrefDao;
	
	@Autowired
	IUserDao userDao;
	
	@Autowired 
	ICollaboratorDao collaboratorDao;
	
	@Autowired
	S3Service s3Service;
	
	@Override
	public void saveNote(NoteDto noteDto, String userId) {
		
		Note note = noteDto.getNote();
		note.setUserId(userId);
		noteDao.save(note);
		
		NotePreferences notePref = noteDto.getNotePreferences();
		notePref.setUserId(userId);
		notePrefDao.save(notePref);
			
	}

	@Override
	public void updateNote(Note note) {
		Date updatedDate = new Date();
		note.setLastUpdated(updatedDate);
		noteDao.save(note);
	}

	@Override
	public void updatenotePref(NotePreferences notePref) {

		//noteDao.save(notePref);
	}

	@Override
	public void deleteNote(int noteId) {
		
		noteDao.deleteById(noteId);
	}

	@Override
	public List<NoteDto> getNotes(String loggedInUser) {
		
		NotePreferences notePref = notePrefDao.findByUserId(loggedInUser);
		
		return null;
	}

	@Override
	public void saveLabel(Label label, String loggedInUserId) {
		label.setUserId(loggedInUserId);
		labelDao.save(label);
	}

	@Override
	public void renameLabel(Label label, String loggedInUserId) {
		
		Label labelFromDB = labelDao.getOne(label.getLabelId());
		labelFromDB.setName(label.getName());
		labelDao.save(labelFromDB);
		
	}

	@Override
	public List<Label> getLabels() {
		return null;
	}

	@Override
	public void deleteLabel(int labelId) {
		
		labelDao.deleteById(labelId);
	}

	@Override
	public void addRemoveLabel(int noteId, int labelId) {

		Note note = noteDao.getOne(noteId);
		Label label = labelDao.getOne(labelId);
	}

	@Override
	public void saveLabelFromNote(Label label, int noteId, String loggedInUserId) {

		
	}

	@Override
	public void deleteImage(int userId, int noteId, String key) {
		
		Note note = noteDao.getOne(noteId);
		s3Service.deleteFileFromS3(key);
		note.setImageUrl(null);
	}

	@Override
	public void saveImage(MultipartFile image, int noteId) {
		
		Note note = noteDao.getOne(noteId);
		String imageUrl = s3Service.saveImageToS3(noteId, image);
		note.setImageUrl(imageUrl);
		noteDao.save(note);
	}

	@Override
	public void collaborat(String sharingUserEmail, int noteId, String loggedInUserEmail) {
		
		Collaboration collaboration = new Collaboration();
		Note note = noteDao.getOne(noteId);
		User loggedInUser = userDao.findByEmail(loggedInUserEmail);
		User sharedUser = userDao.findByEmail(sharingUserEmail);
		
		collaboration.setNote(note);
		collaboration.setShared_By_UserId(sharedUser.getUserId());
		collaboration.setShared_UserId(loggedInUser.getUserId());
	}

	@Override
	public void removeCollaboratUser() {
		
		
	}

	@Override
	public void trashOrRestore(int notePrefId, Status status, String loggedInUserId) {
		
		NotePreferences notePref = notePrefDao.getOne(notePrefId);
		notePref.setStatus(status);
		notePref.setUserId(loggedInUserId);
		notePrefDao.save(notePref);
		
	}

	@Override
	public void pinOrUnpin(int notePrefId, boolean isPinned, String loggedInUserId) {
		
		NotePreferences notePref = notePrefDao.getOne(notePrefId);
		notePref.setPin(isPinned);
		notePref.setUserId(loggedInUserId);
		notePrefDao.save(notePref);				
	}

	@Override
	public void archiveOrUnarchive(int notePrefId, Status status, String loggedInUserId) {
				
		NotePreferences notePref = notePrefDao.getOne(notePrefId);
		notePref.setStatus(status);
		notePref.setUserId(loggedInUserId);
		notePrefDao.save(notePref);
		
	}
	
	
}
