package com.training.OTTDataService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.OTTDataService.model.UserData;

public interface UserRepository extends JpaRepository<UserData, Integer> {

}
