package com.training.OTTDataService;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.training.OTTDataService.service.EmailService;

@EnableScheduling
@SpringBootApplication
@ComponentScan("com.training.OTTDataService")
public class OttDataServiceApplication {

	@Autowired
	EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(OttDataServiceApplication.class, args);
		//System.out.print("Hello world!");

	}
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws MessagingException {
		emailService.sendSimpleEmail("rohitgharge2@gmail.com",
				"Email sent from Spring boot project",
				"Hi, " +
						"\n Sample mail on project Start-Up" +
				"\n\n Thanks & Regards \n Rohit Gharge");

	}



}
