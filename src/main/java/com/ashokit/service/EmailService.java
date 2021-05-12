package com.ashokit.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
	
	public boolean sendAccountUnlockEmail(String subject,String body,String to);

}
