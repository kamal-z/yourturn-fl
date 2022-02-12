package com.yourturn_fl.request;

import org.springframework.web.multipart.MultipartFile;

 

public class UserRequest {
	
 
	private String password;
	
 
	private String email;
	
	private boolean admin;
	
	private String nickName;
	
	private MultipartFile file;
	
	
	public boolean getAdmin() {
		return admin;
	} 
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	 
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	 
	 
	
	 
	
	
	
	

}
