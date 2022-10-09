package com.training.OTTDataService.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.OTTDataService.model.UserData;
import com.training.OTTDataService.repository.UserRepository;

@Service
public class UserDao {
	
	@Autowired
	UserRepository userRepo;
	
	public UserData insertData(UserData userData){
		return this.userRepo.save(userData);
	}

	public List<UserData> getAll(){
		return (List<UserData>) userRepo.findAll();
	}

/*	public UserData findById(int userId) {
		return (UserData)userRepo.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}*/
}
