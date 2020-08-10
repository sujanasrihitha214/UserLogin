package com.appsdeveloperblog.app.ws.userservice;

import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

public interface UserService {
	
	UserRest createUser(UserDetailsRequestModel userDetails);

}
