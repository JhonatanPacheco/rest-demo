package com.spring.cxf.interceptor;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.security.AuthenticationException;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationInterceptor extends AbstractPhaseInterceptor<Message> {
	
	Logger log  = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	
	public AuthenticationInterceptor() {
		super(Phase.MARSHAL);
	}

	public void handleMessage(Message message) throws Fault {
		
		MultivaluedMap<String, Object> headers = (MetadataMap<String, Object>) message.get(Message.PROTOCOL_HEADERS);
		
		String token = (String) headers.get("tsec").get(0);

		if (token != null) {
			AppSecurityContext ctx = checkTicket(token);
			if (ctx == null)
				throw new AuthenticationException("Failed to authenticate user");
			else
				message.put(SecurityContext.class, ctx);
		}
	}

	private AppSecurityContext checkTicket(String token) {
		// TODO: Check ticket against DB
		if (token.equals("123456")) {
			String roles = "Admin";
			return new AppSecurityContext("Administrator", roles);
		} else if (token.equals("987654")) {
			String roles = "User";
			return new AppSecurityContext("End User", roles);
		}

		return null;
	}

	/*
	 * Application Security Classes
	 */

	private class AppPrincipal implements Principal {

		String name;

		public AppPrincipal(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private class AppSecurityContext implements SecurityContext {

		Principal user;
		String roles;

		public AppSecurityContext(String userName, String roles) {
			user = new AppPrincipal(userName);
			this.roles = roles;
		}

		public Principal getUserPrincipal() {
			return user;
		}

		public boolean isUserInRole(String s) {
			return roles.contains(s);
		}

	}

}
