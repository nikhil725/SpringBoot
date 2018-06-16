package com.bridgeit.todo.notes.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.todo.notes.dao.INoteDao;
import com.bridgeit.todo.notes.model.NoteRes;
import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.user.dao.IUserDao;
import com.bridgeit.todo.user.model.User;

public class NoteServiceImpl implements INoteService{
	
	@Autowired
	INoteDao noteDao;
	
	@Autowired
	IUserDao userDao;
	
	@Transactional
	public int createNote(Notes notes, int id) {
		System.out.println("In side create note dao");
		User user = userDao.getUserById(id);
		Date date = new Date();
		
		notes.setUser(user);
		noteDao.addNotes(notes);
		
				return 0;
	}

	@Transactional
	public void updateNote(Notes notes, int id) {
		
		noteDao.updateNote(notes, id);
						
	}

	@Transactional
	public int deleteNote(int id) {			
		noteDao.deleteNote(id);
		return 0;
				
	}

	
	@Transactional
	public List<NoteRes> getNotes(int id) {
		System.out.println("in service");
	//	return noteDao.getNotesbyId(id);
		User user = userDao.getUserById(id);
		 List<Notes> notes =noteDao.getNotes(user);	
		
		 List<NoteRes> resDTO = new ArrayList<NoteRes>();
		 
		 for(Notes object : notes) 
		 {
			 NoteRes dto= new NoteRes(object);
			 resDTO.add(dto);
		 }
		 return resDTO;
		}
	
	
	/*public List<NoteRes> getNotes(int id) {
		System.out.println("in service");
	//	return noteDao.getNotesbyId(id);
		User user = userDao.getUserById(id);
		 List<Notes> notes =noteDao.getNotes(user);	
		
		 List<NoteRes> resDTO = new ArrayList<>();
		 
		 for(Notes object : notes) 
		 {
			 NoteRes dto= new NoteRes(object);
			 resDTO.add(dto);
		 }
		 return resDTO;
		}*/
				
	}
