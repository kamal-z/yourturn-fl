package com.yourturn_fl.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yourturn_fl.common.Status;
import com.yourturn_fl.common.Utils;
import com.yourturn_fl.entity.UserEntity;
import com.yourturn_fl.repository.UserRepository;
import com.yourturn_fl.request.UserRequest;
import com.yourturn_fl.response.UserResponse;
 
@Service
public class UserServiceImpl implements UserService {

	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private Utils utils;
	
	@Autowired
	 EntityManager entitymanager;
	

	 

	@Override
	public UserEntity createUser(UserRequest userRequest) {

		if (userRepository.findByEmail(userRequest.getEmail()) != null) {
			throw new RuntimeException("User " + userRequest.getEmail() + " already exists!");
		}

		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);

		userEntity.setUserId(utils.generateStringId(32));
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		userRepository.save(userEntity);
		authUserWithAuthManager(userEntity.getEmail(), userRequest.getPassword());
		return userEntity;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserEntity getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		return userEntity;
	}

	@Override
	public UserEntity getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}
		return userEntity;
	}

	@Override
	public void updateUser(String userId, UserRequest userRequest) throws IOException {
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		 
		
		userEntity.setNickName(userRequest.getNickName());
		
		userRepository.save(userEntity);

	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}
		userRepository.delete(userEntity);

	}

	@Override
	public Map<String,List<UserEntity>> getUsers() {
		List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
		List<UserEntity> ridersOnReaod = users.stream().filter(user -> user.getStatus().equals(Status.on_road)).collect(Collectors.toList());
		List<UserEntity> ridersInHub = users.stream().filter(user -> user.getStatus().equals(Status.in_hub)).collect(Collectors.toList());
		Collections.sort(ridersInHub, new UserComperator(ridersInHub));
		Map<String,List<UserEntity>> riders = new HashMap<String, List<UserEntity>>();
		riders.put("ridersInHub", ridersInHub);
		riders.put("ridersOnReaod", ridersOnReaod);
		return riders;
	}

	public void authUserWithAuthManager(String email, String password) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
		Authentication authentication = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Override
	public void changeStatusAndRanking(String userId, Status status) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		userEntity.setStatus(status);
		userEntity.setRanking(findTheLastUserBySatus(status) + 1);
		findTheLastUserBySatus(status);
		userRepository.save(userEntity);
		
	}
	
	public int findTheLastUserBySatus(Status status) {
		  Query query = entitymanager.createNamedQuery(UserEntity.FIND_MAX_RINKING_BY_STATUS);
		  query.setParameter("status", status);
		  int maxRanking =   query.getSingleResult() == null ? 0 : (int) query.getSingleResult();
		  return maxRanking;
	}

}
