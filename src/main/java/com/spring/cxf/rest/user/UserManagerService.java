package com.spring.cxf.rest.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.spring.cxf.rest.user.dto.UserRequest;
import com.spring.cxf.rest.user.dto.UserResponse;

@Path("/user")
@Consumes("application/json")
@Produces("application/json")
public interface UserManagerService {
	
	@POST
	@Path("/fetchUserById/")
	public Object fetchUserById(UserRequest request);

	@POST
	@Path("/fetchAllUsers/")
	public UserResponse fetchAllUsers(UserRequest request);

	@POST
	@Path("/insertUser/")
	public UserResponse insertUser(UserRequest request);

	@POST
	@Path("/updateUser/")
	public UserResponse updateUser(UserRequest request);

	@POST
	@Path("/deleteUser/")
	public UserResponse deleteUser(UserRequest request);

}
