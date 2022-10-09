package com.training.OTTDataService.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.OTTDataService.dao.UserDao;
import com.training.OTTDataService.model.UserData;
import com.training.OTTDataService.service.EmailService;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;

	@Autowired
	EmailService emailService;

	//menu mapping
	@GetMapping("/menu")
	public String testMethod(){

		return "        MENU           " +
				"\n # getAllUsers" + 
				"\n # getUserCount" +
				"\n # getLimitedUsers" +
				"\n # getMaxUser" +
				"\n # getUserListOnMailByService" + 
				"\n # filterByCriteria(provide data for criteria and filter value)" +
				"\n    1) id " +
				"\n    2) name " +
				"\n    3) age " +
				"\n    4) city " +
				"\n    5) service " +
				"\n # sortByCriteria(provide data for criteria)" +
				"\n    1) id " +
				"\n    2) name " +
				"\n    3) age " +
				"\n    4) city " +
				"\n    5) service ";
	}



	//create user record 
	@PostMapping("/user")
	public UserData createUserData(@RequestBody UserData userData){
		return userDao.insertData(userData);
	}

	//get all user records
	@GetMapping("/getAllUsers")
	public List<UserData> getAll(){

		return userDao.getAll();
	}

	//get user count
	@GetMapping("/getUserCount")
	public int getUserCount(){

		return userDao.getAll().size();
	}

	//get number of users eqal to count 
	@GetMapping("/getLimitedUsers/{count}")
	public List<UserData> getUserCount(@PathVariable int count){

		return userDao.getAll().stream().limit(count).collect(Collectors.toList());
	}

	@GetMapping("/getUserListOnMailByService/{service}")
	public String getMailForUsersOfParticularService(@PathVariable String service) {

		List<String> listOfUsers = userDao.getAll().stream()
				.filter(user -> user.getServiceOpted().equalsIgnoreCase(service))
				.map(user -> user.getUserName()).collect(Collectors.toList());

		emailService.sendSimpleEmail("rohitgharge2@gmail.com","Users for service : " 
				+ service, listOfUsers.toString());

		return "mail sent on id : rohitgharge2@gmail.com";
	}

	@GetMapping("/getMaxUser/{criteria}")
	public UserData getMaxUser(@PathVariable String criteria){

		UserData userData;
		switch(criteria){
		case "id" : 
			userData = userDao.getAll().stream().max((user1,user2) -> {
				if(user1.getUserId() > user2.getUserId())
					return 1;
				else 
					return -1;
			}).get();
			break;
		case "age":
			userData = userDao.getAll().stream().max((user1,user2) -> {
				if(user1.getUserAge() > user2.getUserAge())
					return 1;
				else 
					return -1;
			}).get();
			break;
		default : return null;
		}
		return userData;
	}


	//get specific user record
	@GetMapping("/filterByCriteria/{criteria}/{filterValue}")
	public List<UserData> getUserByCriteria(@PathVariable (value = "criteria") String criteria,
			@PathVariable (value = "filterValue") String value) {

		List<UserData> userData;
		switch(criteria){
		case "id" : 
			userData = userDao.getAll().stream()
			.filter(user -> user.getUserId() == Integer.parseInt(value))
			.collect(Collectors.toList());
			break;
		case "name":
			userData = userDao.getAll().stream()
			.filter(user -> user.getUserName().equals(value)).collect(Collectors.toList());
			break;
		case "age":
			userData =  userDao.getAll().stream()
			.filter(user -> user.getUserAge() == Integer.parseInt(value)).collect(Collectors.toList());
			break;
		case "city":
			userData =  userDao.getAll().stream()
			.filter(user -> user.getUserCity().equals(value)).collect(Collectors.toList());
			break;
		case "service":
			userData = userDao.getAll().stream()
			.filter(user -> user.getServiceOpted().equals(value)).collect(Collectors.toList());
			break;
		default : return null;
		}
		return userData;
	}

	//get sorted user record
	@GetMapping("/sortByCriteria/{criteria}")
	public List<UserData> getUserByCriteria(@PathVariable (value = "criteria") String criteria) {

		List<UserData> listOfUserData;
		switch(criteria){
		case "id" : 
			listOfUserData = userDao.getAll().stream()
			.sorted((user1,user2) -> 
			{
				if(user1.getUserId() < user2.getUserId()) 
					return 1;
				else return -1;
			}).collect(Collectors.toList());
			break;
		case "name":
			listOfUserData = userDao.getAll().stream()
			.sorted((user1,user2) -> 
			{
				return user1.getUserName().compareTo(user2.getUserName());
			}).collect(Collectors.toList());
			break;
		case "age":
			listOfUserData = userDao.getAll().stream()
			.sorted((user1,user2) -> 
			{
				if(user1.getUserAge() < user2.getUserAge()) 
					return 1;
				else return -1;
			}).collect(Collectors.toList());
			break;
		case "city":
			listOfUserData = userDao.getAll().stream()
			.sorted((user1,user2) -> 
			{
				return user1.getUserCity().compareTo(user2.getUserCity());
			}).collect(Collectors.toList());
			break;
		case "service":
			listOfUserData = userDao.getAll().stream()
			.sorted((user1,user2) -> 
			{
				return user1.getServiceOpted().compareTo(user2.getServiceOpted());
			}).collect(Collectors.toList());
			break;
		default : return null;
		}
		return listOfUserData;
	}
}
