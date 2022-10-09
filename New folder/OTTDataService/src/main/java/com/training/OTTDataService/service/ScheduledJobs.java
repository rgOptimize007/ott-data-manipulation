package com.training.OTTDataService.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.training.OTTDataService.dao.UserDao;

@Component
public class ScheduledJobs {
	
	@Autowired
	UserDao userDao;
	
	@Scheduled(fixedDelay = 20000 , initialDelay = 10000)
	public void scheduledCron(){
		
		Date now = new Date();
		long currentUserCount = userDao.getAll().stream().count();
		System.out.println("Current user count is : " + currentUserCount +" " + now);
		
	}
	

}
