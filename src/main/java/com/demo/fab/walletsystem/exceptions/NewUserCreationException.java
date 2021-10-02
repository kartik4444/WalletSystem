package com.demo.fab.walletsystem.exceptions;


public class NewUserCreationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewUserCreationException(String errorMessage) {
	super(errorMessage);
	}
	
	public NewUserCreationException(String errorMessage,Throwable e) {
		super(errorMessage,e);
		}
}
