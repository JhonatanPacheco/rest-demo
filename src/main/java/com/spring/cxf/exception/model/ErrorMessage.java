package com.spring.cxf.exception.model;

public class ErrorMessage {

	private String error_code;
	private String error_message;
	private int status;

	public ErrorMessage(String error_code, String error_message, int status) {
		super();
		this.error_code = error_code;
		this.error_message = error_message;
		this.status = status;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
