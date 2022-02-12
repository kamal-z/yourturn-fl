package com.yourturn_fl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.yourturn_fl.context.SpringApplicationContext;
 

 
@SpringBootApplication
public class YourturnFlApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourturnFlApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	

	
	 

}
