package com.cognizant.app.lms.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cognizant.app.lms.auth.service.AuthService;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class LmsAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsAuthApplication.class, args);
		System.out.println("LMS Auth Service Started");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
