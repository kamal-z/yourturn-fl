package com.yourturn_fl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourturn_fl.entity.Rider;
import com.yourturn_fl.repository.RiderRepository;



@Service
public class RiderService {
	
	@Autowired
	private RiderRepository riderRepository;
	
	public List<Rider> getAllRiders() {
		return riderRepository.getAllRider();
	}

}
