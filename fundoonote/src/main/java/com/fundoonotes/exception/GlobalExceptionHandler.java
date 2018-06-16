package com.fundoonotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

	CustomResponse response = new CustomResponse();

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustomResponse> DatabaseException(DatabaseException e) {
		response.setMessage("Database Exception");
		response.setStatusCode(104);
		return new ResponseEntity<CustomResponse>(HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RegistrationValidationException.class)
	public ResponseEntity<CustomResponse> RegException(RegistrationValidationException e) {
		response.setMessage("Registration validation Exception");
		response.setStatusCode(409);
		return new ResponseEntity<CustomResponse>(HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UnAuthorizedAccessUser.class)
	public ResponseEntity<CustomResponse> UnauthAccess(UnAuthorizedAccessUser e) {
		response.setMessage("UnAuthorised Access");
		response.setStatusCode(417);
		return new ResponseEntity<CustomResponse>(response, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<CustomResponse> EmailExist(EmailAlreadyExistsException e) {
		response.setMessage("Email already exist");
		response.setStatusCode(403);
		return new ResponseEntity<CustomResponse>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(EmailIdNotExists.class)
	public ResponseEntity<CustomResponse> EmailNotExist(EmailIdNotExists e) {
		response.setMessage("Email not exist");
		response.setStatusCode(400);
		return new ResponseEntity<CustomResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyToken.class)
	public ResponseEntity<CustomResponse> EmailNotExist(EmptyToken e) {
		response.setMessage("Token is empty, Request cannot be processed ");
		response.setStatusCode(400);
		return new ResponseEntity<CustomResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IncorrectEmailException.class)
	public ResponseEntity<CustomResponse> IncorrectEmail() {
		response.setMessage("Incorrect EmailId");
		response.setStatusCode(403);
		return new ResponseEntity<CustomResponse>(response, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<CustomResponse> InvalidCred() {
		response.setMessage("Invalid cedentials");
		response.setStatusCode(403);
		return new ResponseEntity<CustomResponse>(response, HttpStatus.CONFLICT);

	}

	/*@ExceptionHandler(UnAuthorizedAccessUser.class)
	public ResponseEntity<CustomResponse> UnathorizedAccess() {
		response.setMessage("Unaurthorized access..");
		response.setStatusCode(403);
		return new ResponseEntity<CustomResponse>(response, HttpStatus.CONFLICT);
	}*/
	
}
