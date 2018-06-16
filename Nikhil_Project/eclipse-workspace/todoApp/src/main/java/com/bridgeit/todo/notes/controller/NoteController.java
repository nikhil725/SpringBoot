package com.bridgeit.todo.notes.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bridgeit.todo.notes.model.NoteRes;
import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.notes.service.INoteService;
import com.bridgeit.todo.util.JsoupDemo;
import com.bridgeit.todo.util.Token;
import com.bridgeit.todo.util.UrlInfo;

@RestController
public class NoteController {


	@Autowired
	INoteService noteService;

		@RequestMapping(value="createNotes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notes> creatNotes(@RequestBody Notes notes,HttpServletRequest req){
		
		System.out.println("In side create Notes");
		
		int id = Token.getId(req.getHeader("Authorization"));
	//	User user=(User) req.getSession().getAttribute("nameid");
	//	System.out.println("idddd"+user.getId());
		noteService.createNote(notes, id);
			
		return new ResponseEntity<Notes>(notes ,HttpStatus.OK);
	}
	
	@RequestMapping(value="updateNotes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateNotes(@RequestBody Notes notes, HttpServletRequest
			 request){
		
		System.out.println(notes.getDescription()+"....."+notes.getTitle()+"....."+notes.getTrash());
		
		int id = Token.getId(request.getHeader("Authorization"));
		System.out.println("ID is: "+id);
		noteService.updateNote(notes, id);
		System.out.println("id is"+id);
		return new ResponseEntity<String>("Notes updated...",HttpStatus.OK);

	}

	@RequestMapping(value="deleteNotes/{noteId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteNotes(@PathVariable int noteId, HttpServletRequest req){
		
		
	//	int userId=(int) req.getAttribute("Authorization");
	//	System.out.println("userId "+userId);
		int userId = Token.getId(req.getHeader("Authorization"));
		System.out.println("user ID : "+userId);
		noteService.deleteNote(noteId);	
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
	
	
	@RequestMapping(value="/getUrl", method = RequestMethod.POST)
	public List<UrlInfo> getUrlInfo(@RequestBody List<String> urls,HttpServletRequest request) throws URISyntaxException, IOException{
		
		JsoupDemo jsoupDemo = new JsoupDemo();
		UrlInfo urlInfo = null;
		List<UrlInfo> urlData = new ArrayList<>();
		
		for(String url : urls) {
			System.out.println(urls);
			
			urlInfo = jsoupDemo.getUrlData(url);
			urlData.add(urlInfo);
		}
		return urlData;	
	}
	
	@RequestMapping(value = "uploadimage",  method = RequestMethod.POST, headers= {"content-type=multipart/*"})
	public ResponseEntity<String> FileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile fileUpload, @RequestParam int noteId)
	throws Exception {
	System.out.println("file name -- "+fileUpload.getOriginalFilename());
	noteService.saveImage(fileUpload, noteId);
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "deleteimage/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteImage(@PathVariable("noteId") int noteId){
		System.out.println("noteId is.. "+noteId);
		noteService.deleteImage(noteId);
		return null;
	}
}
