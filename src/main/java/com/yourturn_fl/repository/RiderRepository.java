package com.yourturn_fl.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yourturn_fl.entity.Rider;


@Repository
public class RiderRepository {
	
	private static List<Rider> riders = new ArrayList<>(Arrays.asList(new Rider("kamal", "zk", "Verfügbar"), new Rider("amine", "aminous", "Verfügbar")));
	
	public List<Rider> getAllRider() {
		return riders;
	}

}
