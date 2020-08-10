package com.appsdeveloperblog.photoapp.api.users.ui.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.photoapp.api.users.service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.ui.model.CreateRequestUserModel;
import com.appsdeveloperblog.photoapp.api.users.ui.model.CreateUSerResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	UsersService userService;

	@GetMapping("/status/check")
	public String status() {

		return "working";
	}

	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
				produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} 
	)
	public ResponseEntity<CreateUSerResponseModel> createUser(@Valid @RequestBody CreateRequestUserModel userDetails) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);	
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
		CreateUSerResponseModel returnValue = modelMapper.map(createdUser,CreateUSerResponseModel.class);

		return  ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

	}

}
