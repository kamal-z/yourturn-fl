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

import com.yourturn_fl.common.Status;
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
		return new ModelAndView("index", userservice.getUsers());
	}
	
	@PostMapping
	public ModelAndView go(@RequestParam String id) {
		  userservice.changeStatusAndRanking(id, Status.on_road);
		  return getAllRiders();
	}
	
	@PostMapping
	@RequestMapping("/return")
	public ModelAndView retrunToHub(@RequestParam String id) {
		  userservice.changeStatusAndRanking(id, Status.in_hub);
		  return getAllRiders();
	}

}
