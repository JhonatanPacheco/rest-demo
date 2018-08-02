package com.spring.cxf.exception;

import com.spring.cxf.exception.model.ErrorMessage;

public class BiosnettcsResponseError {

	
	public static ErrorMessage fromBiosnettcsException(Throwable ex) {
		
		if(ex instanceof BiosnettcsException) {
			BiosnettcsException e = (BiosnettcsException) ex;
			return new ErrorMessage(e.getErrorCode(),e.getErrorMessage(),e.getStatus());
		}else {
			return new ErrorMessage("Error Fatal",ex.getMessage(),500);
		}
		
	}
	
}
