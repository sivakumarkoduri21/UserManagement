package com.ashokit.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public boolean sendAccountUnlockEmail(String subject, String body, String to) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
		     
		    MimeMessageHelper helper = new MimeMessageHelper(message);
		    
		   // helper.setFrom("noreply@baeldung.com");
		    helper.setTo(to);
		    helper.setSubject(subject);
		    helper.setText(body,true);
		    mailSender.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
}