package com.spring.cxf.rest.user.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spring.cxf.rest.user.UserManagerService;
import com.spring.cxf.rest.user.dto.UserRequest;
import com.spring.cxf.rest.user.dto.UserResponse;

@Service("userManagerService")
public class UserManagerServiceImp implements UserManagerService {
	Logger log = Logger.getLogger(UserManagerServiceImp.class);
	@Override
	public Object fetchUserById(UserRequest request) {
		log.info("Inicio fetchUserById() ");
		return new UserResponse();
	}

	@Override
	public UserResponse fetchAllUsers(UserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse insertUser(UserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse updateUser(UserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse deleteUser(UserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
