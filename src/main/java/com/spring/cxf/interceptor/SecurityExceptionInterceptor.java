package com.spring.cxf.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.security.AccessDeniedException;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

public class SecurityExceptionInterceptor extends AbstractPhaseInterceptor<Message> {
	public SecurityExceptionInterceptor() {
		super(Phase.PRE_STREAM);
	}

	public void handleMessage(Message message) throws Fault {
		Fault fault = (Fault) message.getContent(Exception.class);
		if (fault != null) {
			Throwable ex = fault.getCause();
			if (ex instanceof SecurityException) {

				HttpServletResponse response = (HttpServletResponse) message.getExchange().getInMessage()
						.get(AbstractHTTPDestination.HTTP_RESPONSE);
				int status = ex instanceof AccessDeniedException ? 403 : 401;
				response.setStatus(status);
				
				try {
					response.getOutputStream().write(ex.getMessage().getBytes());
					response.getOutputStream().flush();
				} catch (IOException iex) {
				}

				message.getInterceptorChain().abort();
			}
		}
		

        MultivaluedMap<String, Object> headers = (MetadataMap<String, Object>) message.get(Message.PROTOCOL_HEADERS);

        if (headers == null) {
            headers = new MetadataMap<String, Object>();
        }             

        headers.add("tsec", new String("123456"));
        message.put(Message.PROTOCOL_HEADERS, headers);
	}
}
