package com.yourturn_fl.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.yourturn_fl.entity.UserEntity;
import com.yourturn_fl.request.UserRequest;

import java.io.IOException;
import java.util.List;


public interface UserService extends UserDetailsService{
	
	UserEntity createUser(UserRequest userRequest);
	
	UserEntity getUser(String email);
	
	UserEntity getUserByUserId(String userId);
	
	void updateUser(String userId,UserRequest userRequest) throws IOException;
	
	void deleteUser(String userId);
	
	void goRider(String userId);

	List<UserEntity> getUsers();

}
