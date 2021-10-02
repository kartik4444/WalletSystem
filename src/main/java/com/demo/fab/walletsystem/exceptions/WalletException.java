package com.demo.fab.walletsystem.exceptions;

public class WalletException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WalletException(String errorMessage) {
	super(errorMessage);
	}
	
	public WalletException(String errorMessage,Throwable e) {
		super(errorMessage,e);
		}
}
