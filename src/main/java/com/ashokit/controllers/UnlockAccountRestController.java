package com.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.entity.UnlockAccount;
import com.ashokit.service.UserAccountService;

@RestController
public class UnlockAccountRestController {
	@Autowired
	UserAccountService accountService;

	@PostMapping("/unlockUserAccount")
	public String unlockUserAccount(UnlockAccount account) {
		if (accountService.isTempPwdVaild(account.getEmail(), account.getTempPwd())) {
			accountService.unlockAccount(account.getEmail(), account.getNewPwd());
			return "Account unlock! Please proceed with login";
		} else
			return "please enter valid Temporary password";
	}
}
