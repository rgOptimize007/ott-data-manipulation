package com.training.OTTDataService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
public class UserData {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	@Column(name="user_name")
	@NonNull
	String userName;
	@Column(name="user_age")
	int userAge;
	@Column(name="user_city")
	@NonNull
	String userCity;
	@Column(name="service_opted")
	@NonNull
	String serviceOpted;
}
