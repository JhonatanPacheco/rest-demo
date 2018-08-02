package com.spring.cxf.rest.user.impl;

import org.springframework.stereotype.Service;

import com.spring.cxf.exception.BiosnettcsException;
import com.spring.cxf.rest.user.UserManagerService;
import com.spring.cxf.rest.user.dto.UserRequest;
import com.spring.cxf.rest.user.dto.UserResponse;

@Service("userManagerService")
public class UserManagerServiceImp implements UserManagerService {
	
	
	@Override
	public Object fetchUserById(UserRequest request) throws BiosnettcsException {
		if(request.getUser()== null) {
			throw new BiosnettcsException("n-222", "User es requerido", 403);
		}
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
