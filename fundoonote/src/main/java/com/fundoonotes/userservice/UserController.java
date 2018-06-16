package com.fundoonotes.userservice;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.exception.RegistrationValidationException;
import com.fundoonotes.exception.UnAuthorizedAccessUser;
import com.fundoonotes.utility.CustomResponse;
import com.fundoonotes.utility.RegisterErrors;
import com.fundoonotes.utility.TokenUtils;
import com.fundoonotes.utility.UserValidator;

@RestController
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private UserValidator userValidator;
	
	//regisetr
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@Validated @RequestBody UserDto userDto, BindingResult bindingResult,
			HttpServletRequest request) throws Exception {

		userValidator.validate(userDto, bindingResult);
		List<FieldError> errors = bindingResult.getFieldErrors();

		RegisterErrors response = new RegisterErrors();
		CustomResponse customRes = new CustomResponse();
		if (bindingResult.hasErrors()) {
			throw new RegistrationValidationException();
		}

		String url = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));
		userService.register(userDto,url);

		response.setMsg("user register successfully");
		response.setStatus(200);

		logger.info("This is info message");

		return new ResponseEntity<RegisterErrors>(response, HttpStatus.CREATED);

	}
	//login
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response) {

		CustomResponse customRes = new CustomResponse();
		String token = userService.login(userDto);
		System.out.println("this is your tooekn:"+token);
		if (token != null) {
			response.setHeader("Authorization", token);
			customRes.setMessage("user login successfully");
			customRes.setStatusCode(100);

			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.OK);
		} else {

			throw new UnAuthorizedAccessUser();
		}
	}
    //confirm registration
	@RequestMapping(value = "/RegistrationConfirm/{randomId}", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> isActiveUser(@PathVariable("randomId") String randomId) {

		CustomResponse customRes = new CustomResponse();

		if (userService.userActivation(randomId)==1) {

			customRes.setMessage("user activation done successfully");
			customRes.setStatusCode(200);
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.CREATED);
		} else {

			customRes.setMessage("activation fail");
			customRes.setStatusCode(409);
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.CONFLICT);
		}

	}
	//forgot password
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> forgotPassword(@RequestBody UserDto userDto, HttpServletRequest request) {
		CustomResponse customRes = new CustomResponse();
		try {
			System.out.println(userDto.getEmail());
			String url = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));
			if (userService.forgetPassword(userDto.getEmail(), url))

			{
				customRes.setMessage("forgot password");
				customRes.setStatusCode(100);
				return new ResponseEntity<CustomResponse>(customRes, HttpStatus.OK);
			} else {
				return new ResponseEntity<CustomResponse>(HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CustomResponse>(HttpStatus.NO_CONTENT);
		}
	}
	//reset password
	@RequestMapping(value = "/resetpassword/{jwtToken}", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> resetPassword(@RequestBody UserDto userDto,
			@PathVariable("jwtToken") String jwtToken) {

		CustomResponse customRes = new CustomResponse();
		int id = TokenUtils.verifyToken(jwtToken);
		User user = userService.getUserById(id);
		userDto.setEmail(user.getEmail());

		if (userService.resetPassword(userDto)==1) {
			customRes.setMessage("Reset Password Sucessfully........");
			customRes.setStatusCode(100);
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.OK);

		} else {
			customRes.setMessage("Password Not Updated.......");
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.BAD_REQUEST);
		}
	}
	
	//loggeduser
	@RequestMapping(value = "/user/loggeduser", method = RequestMethod.GET)
	public ResponseEntity<?> getLoggeddUser(@RequestAttribute(name = "userId") int userId) {
		CustomResponse customRes = new CustomResponse();
		User user = userService.getUserById(userId);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		customRes.setMessage("no logged user");
		customRes.setStatusCode(409);
		return new ResponseEntity<CustomResponse>(customRes, HttpStatus.CONFLICT);
	}	
}
