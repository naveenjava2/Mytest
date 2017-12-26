package com.service.FileSearchDemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.service.search.bean.SearchErrorResponse;
import com.service.search.exception.WordNotFoundException;

 
@ControllerAdvice   
public class RestServiceExceptionHandler {
	  
    @ExceptionHandler(WordNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<SearchErrorResponse> handleWordNotFoundException(WordNotFoundException ex) {
    	SearchErrorResponse errorResponse = new SearchErrorResponse();
        errorResponse.setErrorCode(404);
        errorResponse.setErrorMessage(ex.getMessage());
        return new ResponseEntity<SearchErrorResponse>(errorResponse, HttpStatus.OK);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<SearchErrorResponse> handleGenericException(Exception ex) {
    	SearchErrorResponse errorResponse = new SearchErrorResponse();
        errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setErrorMessage("There is some techncal issue");
        return new ResponseEntity<SearchErrorResponse>(errorResponse, HttpStatus.OK);
    }
 
}
