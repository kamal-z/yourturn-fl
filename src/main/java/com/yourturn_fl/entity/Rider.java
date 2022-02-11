package com.yourturn_fl.entity;

public class Rider {
	
	private String name; 

	private String nickName;

	private String status;
	
	

	public Rider(String name, String nickName, String status) {
		super();
		this.name = name;
		this.nickName = nickName;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
