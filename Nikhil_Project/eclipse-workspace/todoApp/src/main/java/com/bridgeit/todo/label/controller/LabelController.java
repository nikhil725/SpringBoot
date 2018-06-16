package com.bridgeit.todo.label.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.label.model.Label;
import com.bridgeit.todo.label.model.LabelRes;
import com.bridgeit.todo.label.service.ILabelService;
import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.util.Token;

@RestController
public class LabelController {

	
	@Autowired
	ILabelService labelService;
	
	@RequestMapping(value="createLabels", method = RequestMethod.POST)
	public ResponseEntity<String> createLabels(@RequestBody Label label, HttpServletRequest request){
		System.out.println("**** Inside create label #####****");
		
		System.out.println("label name "+label.getName());
		System.out.println("label is ");
		int userId = Token.getId(request.getHeader("Authorization"));
		labelService.createLabel(label, userId);
		return new ResponseEntity<>("label add", HttpStatus.OK);
		
	}
	
	@RequestMapping(value="getLabels", method = RequestMethod.GET)
	public ResponseEntity<?> getLabels(HttpServletRequest request){
	
		System.out.println("In side get label controller...");
	
		int userId = Token.getId(request.getHeader("Authorization"));
		System.out.println("userId is: "+userId);

		
		List<LabelRes> labels = labelService.getLabels(userId);
		
		if (labels.size() != 0) {
			
			return new ResponseEntity<List>(labels,HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<List<Notes>>(HttpStatus.NO_CONTENT);
		}
	}
		
	@RequestMapping(value = "deleteLabel/{labelId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteLabel(@PathVariable("labelId") int labelId, HttpServletRequest request) {
		System.out.println("labelId.. "+labelId);
		String token = request.getHeader("Authorization");
		try {
			labelService.deleteLabel(labelId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value="addLabelOnNotes/{noteId}/{labelId}/{operation}", method= RequestMethod.PUT)
	public ResponseEntity<?> addRemoveLabel(@PathVariable("noteId") int noteId, @PathVariable("labelId") int labelId,
			@PathVariable("operation") boolean operation){
		
		if(operation) {
			
			labelService.addLabel(noteId, labelId);
			
		}else if(!operation) {	
			
			labelService.deleteLabelFromNote(noteId, labelId);
		}
		return null;
	}
		
/*	@RequestMapping(value = "/addLabelToNote/{noteId}/{labelId}/{operation}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addLabelToNote(@PathVariable("noteId") int noteId, @PathVariable("labelId") int labelId,
			@PathVariable("operation") String operation, HttpServletRequest request) {
		boolean operation1 = Boolean.valueOf(operation);
		// boolean operation1=false;
		if (operation1 == true) {
			noteService.addLabel(noteId, labelId);
			Response response = new Response();
			response.setMsg("note update with label");
			response.setStatus(1);

			logger.info("note update with label successfully");
			// return new ResponseEntity<Response>(response, HttpStatus.OK);

		} else if (operation1 == false) {
			noteService.deleteLabelFromNote(noteId, labelId);
			Response response = new Response();
			response.setMsg("label deleted successfully");
			response.setStatus(1);

			logger.info("label deleted successfully");
			// return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			System.out.println("invalid api");
		}

	}
	*/
	
	
}
