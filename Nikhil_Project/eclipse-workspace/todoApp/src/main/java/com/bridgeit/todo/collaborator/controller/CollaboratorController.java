package com.bridgeit.todo.collaborator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.collaborator.model.Collaborator;
import com.bridgeit.todo.collaborator.service.ICollaboratorService;
import com.bridgeit.todo.util.Token;

@RestController
public class CollaboratorController {

	@Autowired
	private ICollaboratorService collaboratorService;

	/*@RequestMapping(value = "/addcollaborator", method = RequestMethod.POST)
	public ResponseEntity<Void> createCollaborator(@RequestBody Collaborator collaborator, HttpServletRequest request) {
	
		System.out.println("Inside add collaborator");
		int id = Token.getId(request.getHeader("Authorization"));
		try {
			collaboratorService.addCollaborator(collaborator, id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}*/
	
	
	@RequestMapping(value = "/addcollaborator", method = RequestMethod.POST)
	public ResponseEntity<Void> createCollaborator(@RequestParam String sharedUserEmail, @RequestParam int noteId, @RequestHeader("Authorization") String token) {
	
		System.out.println("Inside add collaborator");
		int id = Token.getId(token);
		try {
			collaboratorService.addCollaborator(sharedUserEmail, noteId, id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/removecollaborator", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeCollaborator(@RequestParam String sharedUserEmail, @RequestParam int noteId){
		
		try {
			collaboratorService.removeCollaborator(sharedUserEmail, noteId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
}
