package com.ashokit.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.entity.UserAccount;
import com.ashokit.service.UserAccountService;

@RestController
@RequestMapping("/api/userAccount")
public class UserRegisterController {

	@Autowired
	private UserAccountService accountService;

	@GetMapping("/countries")
	public Map<Integer, String> getAllCountries() {

		return accountService.getAllCountries();
	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getAllStates(@PathVariable Integer countryId) {
		return accountService.getStates(countryId);
	}

	@GetMapping("/cityes/{stateId}")
	public Map<Integer, String> getAllCityes(Integer stateId) {
		return accountService.getCityes(stateId);
	}

	@GetMapping("/emailCheck/{email}")
	public String emailCheck(@PathVariable String email) {

		boolean isUnique = accountService.emailIsUnique(email);
		if (isUnique) {

			return "UNIQUE";
		}
		return "DUPLICATE";
	}

	@PostMapping(value = ".registation", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> saveUser(@RequestBody UserAccount account) {

		boolean isSaved = accountService.saveUser(account);
		if (isSaved) {
			return new ResponseEntity<>("Saved", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
	}
}
