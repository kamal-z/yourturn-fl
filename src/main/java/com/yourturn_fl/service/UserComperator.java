package com.yourturn_fl.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.yourturn_fl.entity.UserEntity;

public class UserComperator implements Comparator<UserEntity>{
	
	List<UserEntity> users = new ArrayList<UserEntity>();
	
	public UserComperator(List<UserEntity> users) {
		this.users = users;
	}

	@Override
	public int compare(UserEntity actuell, UserEntity next) {
		if (actuell.getRanking() > next.getRanking()) {
			return 1;
		} else if ((actuell.getRanking() < next.getRanking())) {
			return -1;
		} else {
			return 0;
		}
	}

}
