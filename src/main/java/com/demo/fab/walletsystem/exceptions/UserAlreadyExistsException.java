package com.demo.fab.walletsystem.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String errorMessage) {
	super(errorMessage);
	}
	
	public UserAlreadyExistsException(String errorMessage,Throwable e) {
		super(errorMessage,e);
		}
}
