package com.appsdeveloperblog.photoapp.api.accountmanagementservice.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountmanagement")
public class AccountManagementController {
	
	
	@GetMapping("/status/check")
	public String getAccount() {
		return "in accounts";
	}

}
