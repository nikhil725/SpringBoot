package com.fundoonotes.noteservice;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fundoonotes.userservice.User;
import com.fundoonotes.userservice.UserServiceImpl;

@Service
public class NoteServiceImpl implements INoteService {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	JmsTemplate template;
	
	@Override
	public int createNote(Note note, int id) {
		
		User user = userService.getUserById(id);
		note.setUser(user);
		template.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage(note);
				return message;
			}
		});

		noteRepository.save(note);
		return 0;
	}

	@Override
	public int updateNote(Note note, int userId) {
		
		User user = userService.getUserById(userId);
		note.setUser(user);
		noteRepository.save(note);
		return 0;
	}

	@Override
	public void deleteNote(int id) {
		
	noteRepository.deleteById(id);	
	
	}
	@Override
	public List<NoteResDto> getNotes(int id) {
	
		User user = userService.getUserById(id);
		List<Note> notes = noteRepository.findNotesByUserId(user);
		 List<NoteResDto> list = new ArrayList<>();
		for(Note note :notes) {
			NoteResDto obj = new NoteResDto(note);
			list.add(obj);
		}		
		return list;
	}

	@Override
	public void saveImage(MultipartFile fileUpload, int noteId) throws IOException {	
		Note note = noteRepository.getOne(noteId);
		System.out.println("note detail.. "+note.getTitle()+"..."+note.getDescription());
		note.setImage(fileUpload.getBytes());
		System.out.println("image=>"+note.getImage());
		noteRepository.save(note);
	}

	@Override
	public Note getNoteByNoteId(int id) {
		
		 return noteRepository.getOne(id);
	}

	@Override
	public void deleteImage(int noteId) {	
		Note note = noteRepository.getOne(noteId);
		note.setImage(null);
		noteRepository.save(note);
	}

}