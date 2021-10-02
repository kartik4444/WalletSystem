package com.demo.fab.walletsystem.exceptionhelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.fab.walletsystem.exceptions.NewUserCreationException;
import com.demo.fab.walletsystem.exceptions.TransactionCreationException;
import com.demo.fab.walletsystem.exceptions.UserAlreadyExistsException;
import com.demo.fab.walletsystem.exceptions.UserPassbookViewException;
import com.demo.fab.walletsystem.exceptions.UserProfileNotFoundException;
import com.demo.fab.walletsystem.exceptions.UserProfileUpdateException;
import com.demo.fab.walletsystem.exceptions.UserProfileViewException;
import com.demo.fab.walletsystem.exceptions.WalletException;

@ControllerAdvice
public class ExceptionHandlerClass {
	
	@ExceptionHandler( {UserAlreadyExistsException.class})
	    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
	        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.CONFLICT);
	    }
	
	@ExceptionHandler( {WalletException.class})
    public ResponseEntity<Object> handleWalletException(WalletException ex) {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler( {NewUserCreationException.class})
    public ResponseEntity<Object> handleNewUserCreationException(NewUserCreationException ex) {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler( {UserProfileUpdateException.class})
    public ResponseEntity<Object> handleUserProfileUpdateException(UserProfileUpdateException ex) {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler( {TransactionCreationException.class})
    public ResponseEntity<Object> handleTransactionCreationException(TransactionCreationException ex) {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler( {UserProfileNotFoundException.class})
    public ResponseEntity<Object> handleUserProfileNotFoundException(UserProfileNotFoundException ex) {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler( {UserProfileViewException.class})
    public ResponseEntity<Object> handleUserProfileViewException(UserProfileViewException ex) {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler( {UserPassbookViewException.class})
    public ResponseEntity<Object> handleUserPassbookViewException(UserPassbookViewException ex) {
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
