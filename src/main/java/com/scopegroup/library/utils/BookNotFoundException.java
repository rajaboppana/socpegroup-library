/**
 * 
 */
package com.scopegroup.library.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Raja
 *
 * Scope Group test
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -46936627755201133L;

	public BookNotFoundException(String exception) {
		super(exception);
	}

}
