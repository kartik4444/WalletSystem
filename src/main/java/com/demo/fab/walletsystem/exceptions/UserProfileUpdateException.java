package com.demo.fab.walletsystem.exceptions;

public class UserProfileUpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserProfileUpdateException(String errorMessage) {
	super(errorMessage);
	}
	
	public UserProfileUpdateException(String errorMessage,Throwable e) {
		super(errorMessage,e);
		}
}
