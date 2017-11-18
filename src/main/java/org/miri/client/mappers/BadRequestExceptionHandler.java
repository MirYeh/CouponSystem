package org.miri.client.mappers;

import javax.servlet.http.HttpServletRequest;

import org.miri.exceptions.CompanyRemovalException;
import org.miri.exceptions.UserInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handles a {@code UserInputException} and {@code CompanyRemovalException} when thrown.
 * @author Miri Yehezkel
 * @see UserInputException
 * @see CompanyRemovalException
 */
@RestControllerAdvice
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value= {UserInputException.class, CompanyRemovalException.class})
	public ResponseEntity<ErrorInfo> handleException(RuntimeException ex, HttpServletRequest request) {
		int statusCode = HttpStatus.BAD_REQUEST.value();
		ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), statusCode, request.getRequestURL().toString());
		return ResponseEntity.status(statusCode).body(errorInfo);
	}
	
}
