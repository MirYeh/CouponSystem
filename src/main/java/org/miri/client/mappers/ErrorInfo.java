package org.miri.client.mappers;

import java.util.Date;

/**
 * Represents an error json returned when an application exception is thrown.
 * @author Miri Yehezkel
 * @see DataNotFoundExceptionHandler
 * @see BadRequestExceptionHandler
 */
public class ErrorInfo {
	private Date timestamp;
	private String message;
	private int statusCode;
	private String uri;
	
	public ErrorInfo() {
		timestamp = new Date();
	}
	
	public ErrorInfo(String message, int statusCode, String uri) {
		this();
		this.message = message;
		this.statusCode = statusCode;
		this.uri = uri;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
