package com.spring.cxf.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.spring.cxf.exception.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<BiosnettcsException> {

	@Override
	public Response toResponse(BiosnettcsException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getErrorCode(), ex.getMessage(), ex.getStatus() );
		return Response.status(Status.fromStatusCode(ex.getStatus()))
				.entity(errorMessage)
				.build();
		
	}

}
