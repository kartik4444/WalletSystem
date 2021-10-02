package com.demo.fab.walletsystem.exceptions;

public class UserProfileViewException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserProfileViewException(String errorMessage) {
	super(errorMessage);
	}
	
	public UserProfileViewException(String errorMessage,Throwable e) {
		super(errorMessage,e);
		}
}
