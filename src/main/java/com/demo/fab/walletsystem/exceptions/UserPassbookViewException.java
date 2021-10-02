package com.demo.fab.walletsystem.exceptions;

public class UserPassbookViewException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserPassbookViewException(String errorMessage) {
	super(errorMessage);
	}
	
	public UserPassbookViewException(String errorMessage,Throwable e) {
		super(errorMessage,e);
		}
}