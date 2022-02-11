package com.yourturn_fl.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yourturn_fl.service.RiderService;

@RestController
@RequestMapping("/rider")
public class RiderController {
	
	@Autowired
	private RiderService riderService;
	
	@GetMapping
	public ModelAndView test() {
		return new ModelAndView ("index");
	}

}
