package org.miri.client.mappers;

import javax.servlet.http.HttpServletRequest;

import org.miri.exceptions.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handles a {@code DataNotFoundException} when thrown.
 * @author Miri Yehezkel
 * @see DataNotFoundException
 */
@RestControllerAdvice
public class DataNotFoundExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleException(DataNotFoundException ex, HttpServletRequest request) {
		int statusCode = HttpStatus.NOT_FOUND.value();
		ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), statusCode, request.getRequestURL().toString());
		return ResponseEntity.status(statusCode).body(errorInfo);
	}
	
	
}
