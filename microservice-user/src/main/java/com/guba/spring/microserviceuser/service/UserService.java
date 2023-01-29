package com.guba.spring.microserviceuser.service;

import com.guba.spring.microserviceuser.web.model.request.UserDetailsRequestModel;
import com.guba.spring.microserviceuser.web.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
