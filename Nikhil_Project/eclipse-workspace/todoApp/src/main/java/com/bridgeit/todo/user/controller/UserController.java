package com.bridgeit.todo.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.user.model.User;
import com.bridgeit.todo.user.service.IUserService;
import com.bridgeit.todo.util.Token;

@RestController
public class UserController {

	@Autowired
	IUserService userService;

	/**
	 *  This API is used to new user registration
	 * 
	 * @param user
	 * @param req
	 * @param resp
	 * @return
	 */
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody User user, HttpServletRequest req, HttpServletResponse resp) {

		System.out.println("In side register...");

		if (userService.isEmailIdPresent(user.getEmail())) 
		{	
			return new ResponseEntity<String>("this email id already register...try with another",HttpStatus.CONFLICT);

		} else {

			if(userService.addUser(user, req)!=null)
			{	
				return new ResponseEntity<String>("User Registration fail..", HttpStatus.CONFLICT);
			}

			//user.setName("xyz");

			return new ResponseEntity<String>("User Registered..", HttpStatus.OK);
		}
	}


	/**
	 * This API is used for user Authorization
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

		UserResponse res =  new UserResponse();

		String token = userService.validateUser(user);
		System.out.println("successfull");
		System.out.println("token 1  "+token);

		if(token != null)
		{		
			System.out.println(" login Successfull");

			response.setHeader("Authorization", token);
			res.setStatusCode(200);
			res.setMessage("Login Successfull");

			return new ResponseEntity<UserResponse>(res, HttpStatus.OK);	
		}
		return new ResponseEntity<String>("Invalid username or password...", HttpStatus.NOT_FOUND);

	}

	/**
	 * This API is used to get particular user by Id.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getUserById", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(HttpServletRequest request) {

		try {
			int id = Token.getId(request.getHeader("Authorization"));
			System.out.println("In getuser");
			User userInformtion = userService.getUserById(id);

			System.out.println(userInformtion.getName()+".."+userInformtion.getEmail()+"...."+userInformtion.getPassword());
			return new ResponseEntity<User>(userInformtion, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
	}

	/**
	 * This API is used to get the user record corresponding to emailId.
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "getUserByEmailId", method = RequestMethod.POST)
	public ResponseEntity<String> getUserByEmail(@RequestBody User user) {


		try {
			System.out.println("In getuser");
			User userInformtion = userService.getUserByEmaiId(user.getEmail());
			System.out.println(userInformtion.getName()+".."+userInformtion.getEmail()+"...."+userInformtion.getPassword());
			return new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}

	
	/**
	 * This API is used to activate the user
	 * 
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/activateUser/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<Void> activate(@PathVariable("token") String token,  HttpServletRequest request,
			HttpServletResponse response)  {

		userService.activateUser(token, request); 
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

	/**
	 * This API for forgot password
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@RequestBody User user, HttpServletRequest request) {

		UserResponse res =  new UserResponse();

		try {

			if (userService.forgotPassword(user, request) == true) 
			{
				res.setStatusCode(200);

				return new ResponseEntity<UserResponse>(res, HttpStatus.OK);
			} else {

				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}


	/**
	 * This API is used to send reset password link
	 * 
	 * @param token
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/resetPasswordLink/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<Void> resetPasswordLink(@PathVariable("token") String token, HttpServletResponse response) throws IOException  {

		System.out.println("In side reset password link "+token);
		response.sendRedirect("http://localhost:4200/resetpassword?token=" + token);
		return null;
	}

	/**
	 * This API is used to reset password
	 * 
	 * @param request
	 * @param user
	 * @return 
	 */
//	@RequestMapping(value = "/resetPassword/{token:.+}", method = RequestMethod.POST)
//	public ResponseEntity<Void> resetPasswowrd(@PathVariable("token") String token, HttpServletRequest request, @RequestBody User user)  {
//
//		System.out.println("In side reset password");
//	int id = Token.getId(request.getHeader("Authorization"));
//	String newPassword = user.getPassword();		System.out.println("new password: "+newPassword);
//		userService.resetPassword(request, newPassword, id);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//
//	}
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<?> resetPasswowrd(@RequestParam("token") String token, HttpServletRequest request, @RequestBody User user)  {

		UserResponse userResponse =  new UserResponse();
		System.out.println("In side reset password");
	    int id = Token.getId(token);
	    String newPassword = user.getPassword();		
	    System.out.println("new password: "+newPassword);
		User user2 = userService.resetPassword(request, newPassword, id);
		if(user2!=null) {
			System.out.println(" password reset Successfull");
			userResponse.setStatusCode(200);
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
}
