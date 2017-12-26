package com.service.search.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WordNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private String errorMessage;
	
	 public WordNotFoundException() {
	  super();
	 }

	public WordNotFoundException(String errorMessage){
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
	    return errorMessage;
	 }
}
