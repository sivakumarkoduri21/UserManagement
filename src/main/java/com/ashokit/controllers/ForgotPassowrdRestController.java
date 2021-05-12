package com.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.service.UserAccountService;

@RestController
public class ForgotPassowrdRestController {
	@Autowired
	private UserAccountService accountService;
	
@GetMapping("/forgotPassword/{email}")
	public String forgotpassword(@PathVariable String email) {
    if(accountService.recoverPassword(email)!=null) {
    	
    //return accountService.recoverPassword(email);
    return "Sent Your Password Your Email";
    }
	return "The user Does't Exit!";
	}
}
