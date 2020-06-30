package com.example.demo.service;

import java.security.Principal;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	

	
	public void sendEmail(Principal principal,String message,boolean auth){
		SimpleMailMessage mail = new SimpleMailMessage();
		if(!auth) {
			mail.setTo("bamrahprabhjyot@gmail.com");
			System.out.println("true");
		}
		mail.setFrom("bamrahprabhjyot@gmail.com");
		mail.setSubject("Test");
		mail.setText(message);
		javaMailSender.send(mail);
		System.out.println("tested");
	}




	
}
