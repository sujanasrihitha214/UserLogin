package com.appsdeveloperblog.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblog.app.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {

	Map<String, UserRest> users;
	
	Utils utils;
	
	
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}




	public UserServiceImpl() {
	}




	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();

		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setEmailId(userDetails.getEmail());
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);

		if (users == null) {
			users = new HashMap<>();
			users.put(userId, returnValue);
		}

		return returnValue;
	}

}
