package com.spring.cxf.security;

import com.spring.cxf.rest.user.dto.User;

public class TokeSecurity {

	private String tsec;
	private int estado;
	private User user;

	public String getTsec() {
		return tsec;
	}

	public void setTsec(String tsec) {
		this.tsec = tsec;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
