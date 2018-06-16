package com.bridgeit.todo.notes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.notes.model.NoteRes;
import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.notes.service.INoteService;
import com.bridgeit.todo.user.model.User;
import com.bridgeit.todo.util.Token;

@RestController
public class NoteController {


	@Autowired
	INoteService noteService;

	@RequestMapping(value="createNotes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> creatNotes(@RequestBody Notes notes,HttpServletRequest req){
		
		System.out.println("In side create Notes");
		
		int id = Token.getId(req.getHeader("Authorization"));
	//	User user=(User) req.getSession().getAttribute("nameid");
	//	System.out.println("idddd"+user.getId());
		noteService.createNote(notes, id);
		return new ResponseEntity<String>("Notes saved...",HttpStatus.OK);
	}
	
	@RequestMapping(value="updateNotes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateNotes(@RequestBody Notes notes, HttpServletRequest
			 request){
		
		int id = Token.getId(request.getHeader("Authorization"));
		noteService.updateNote(notes, id);
		System.out.println("id is"+id);
		return new ResponseEntity<String>("Notes updated...",HttpStatus.OK);

	}

	@RequestMapping(value="/deleteNotes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNotes(@PathVariable("id") int id, @RequestBody Notes notes){

		noteService.deleteNote(id);	
		return new ResponseEntity<String>("Notes deleted...",HttpStatus.OK);

	}
	
	@RequestMapping(value="/getNotes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getNotes(HttpServletRequest request){
		
		
		try {
			System.out.println("in get controller");
			int id = Token.getId(request.getHeader("Authorization"));
			List<NoteRes> resDTO = noteService.getNotes(id);
			
			if (resDTO.size() != 0) {
				System.out.println("in side note controller");
				return new ResponseEntity<List>(resDTO,HttpStatus.OK);
				
			} else {
				
				return new ResponseEntity<List<Notes>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<Notes>>(HttpStatus.CONFLICT);
		}
	}
	

}
