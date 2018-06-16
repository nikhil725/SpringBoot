package com.fundoonotes.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super("Error in database operation");
	}
}
