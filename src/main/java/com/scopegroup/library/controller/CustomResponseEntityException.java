/**
 * 
 */
package com.scopegroup.library.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.scopegroup.library.utils.BookNotFoundException;
import com.scopegroup.library.utils.ExceptionResponse;

/**
 * @author Raja
 *
 * Scope Group test
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityException extends ResponseEntityExceptionHandler  {
	
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
	@ExceptionHandler(BookNotFoundException.class)
	  public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(BookNotFoundException ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }

}
