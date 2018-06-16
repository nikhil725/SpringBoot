package com.bridgeit.todo.collaborator.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.todo.collaborator.dao.ICollaboratorDao;
import com.bridgeit.todo.collaborator.model.Collaborator;
import com.bridgeit.todo.notes.dao.INoteDao;
import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.user.dao.IUserDao;
import com.bridgeit.todo.user.model.User;

public class CollaboratorServiceImpl implements ICollaboratorService{

	@Autowired
	ICollaboratorDao collaboratorDao;
	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	INoteDao noteDao;
	
	@Transactional
	@Override
	public void addCollaborator(String sharedUseremail, int noteId, int userId) {
		
		Collaborator collaborator = new Collaborator();
		//User user = userDao.getUserByEmaiId(sharedUseremail);
		collaborator.setOwnerId(userDao.getUserById(userId));
		collaborator.setSharedUserId(userDao.getUserByEmaiId(sharedUseremail));
		Notes notes = noteDao.getNotebyNoteId(noteId);
		collaborator.setNoteId(notes);
		collaboratorDao.addCollaborator(collaborator);
		
		
		
	}
	@Transactional
	@Override
	public void removeCollaborator(String sharedUseremail, int noteId) {
		
		User user = userDao.getUserByEmaiId(sharedUseremail);
		Notes notes = noteDao.getNotebyNoteId(noteId);
		collaboratorDao.removeCollaborator( user, notes);
		
		
		
		
	}

	
	
	
	

}