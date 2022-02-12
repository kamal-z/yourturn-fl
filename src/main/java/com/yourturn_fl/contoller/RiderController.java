package com.yourturn_fl.contoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yourturn_fl.entity.UserEntity;
import com.yourturn_fl.service.UserService;
import com.yourturn_fl.service.UserServiceImpl;

@RestController
@RequestMapping("/")
public class RiderController {
	
 @Autowired
 private UserService userservice;
	
	@GetMapping
	public ModelAndView getAllRiders() {
		Map<String, List<UserEntity>> riders = new HashMap<String, List<UserEntity>>();
		riders.put("riders", userservice.getUsers());
		userservice.getUsers();
		return new ModelAndView("index", riders);
	}
	
	@PostMapping
	public ModelAndView go(@RequestParam String id) {
		  userservice.goRider(id);
		  return getAllRiders();
	}

}
