package com.guba.spring.microservice.service;

import com.guba.spring.microservice.web.model.request.UserDetailsRequestModel;
import com.guba.spring.microservice.web.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
