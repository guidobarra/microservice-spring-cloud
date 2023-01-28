package com.guba.spring.microservice.service.impl;

import com.guba.spring.microservice.shared.Utils;
import com.guba.spring.microservice.service.UserService;
import com.guba.spring.microservice.web.model.request.UserDetailsRequestModel;
import com.guba.spring.microservice.web.model.response.UserRest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private static final Map<String, UserRest> USERS = new HashMap<>();
	private final Utils utils;
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId = utils.generateUserId();
		returnValue.setUserId(userId);
		
		USERS.put(userId, returnValue);
		
		return returnValue;
		
	}

}
