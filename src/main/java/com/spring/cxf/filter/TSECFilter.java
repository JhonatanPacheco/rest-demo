package com.spring.cxf.filter;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;

import com.spring.cxf.exception.BiosnettcsException;
import com.spring.cxf.exception.BiosnettcsResponseError;

 @Provider
public class TSECFilter implements RequestHandler {

	@Context
	private HttpHeaders headers;


	@Override
	public Response handleRequest(Message m, ClassResourceInfo resourceClass) {
		try {
			List<String> hium = this.headers.getRequestHeader("ium");
			if ((hium == null) || (hium.size() == 0)) {
				throw new BiosnettcsException("BM-IUM", "IUM value is not present", 430);
			}
		} catch (BiosnettcsException e) {
			return (Response.status(e.getStatus()).entity(BiosnettcsResponseError.fromBiosnettcsException(e)).build());
		} catch (Exception ex) {
			return (Response.status(500).entity(BiosnettcsResponseError.fromBiosnettcsException(ex)).build());
		}
		return null;
	}

}
