package com.demo.fab.walletsystem.exceptions;

public class TransactionCreationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionCreationException(String errorMessage) {
	super(errorMessage);
	}
	
	public TransactionCreationException(String errorMessage,Throwable e) {
		super(errorMessage,e);
		}
}

