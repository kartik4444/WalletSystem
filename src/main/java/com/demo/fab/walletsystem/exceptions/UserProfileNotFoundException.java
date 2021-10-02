package com.demo.fab.walletsystem.exceptions;

public class UserProfileNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserProfileNotFoundException(String errorMessage) {
	super(errorMessage);
	}
	
	public UserProfileNotFoundException(String errorMessage,Throwable e) {
		super(errorMessage,e);
		}
}
