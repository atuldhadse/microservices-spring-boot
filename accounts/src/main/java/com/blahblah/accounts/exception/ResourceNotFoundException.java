package com.blahblah.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8537331983718075409L;

	public ResourceNotFoundException(String resource, String field, String value) {
		super(String.format("%s does not exists for %s with value %s", resource, field, value));
	}

}
