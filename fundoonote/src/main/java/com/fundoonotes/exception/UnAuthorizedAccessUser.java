package com.fundoonotes.exception;

public class UnAuthorizedAccessUser extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnAuthorizedAccessUser() {
		super("login denied");
	}
}
