package com.training.OTTDataService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleEmail(String receiver,String subject,String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("rohitgharge2@gmail.com");
		message.setTo(receiver);
		message.setText(body);
		message.setSubject(subject);
		mailSender.send(message);
		System.out.println("Mail Sent successfully!");
	}
}
