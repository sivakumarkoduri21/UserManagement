package com.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.entity.LoginForm;
import com.ashokit.service.UserAccountService;

@RestController
public class LoginController {

	@Autowired
	private UserAccountService accountService;

	@PostMapping("/login")
	public String login(LoginForm loginForm) {

		return accountService.loginCheck(loginForm.getEmail(), loginForm.getPassword());

	}
}
