package com.cognizant.app.lms.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import com.cognizant.app.lms.auth.service.AuthService;

@SpringBootTest
class LmsAuthApplicationTests {

	@Test
	void contextLoads() {
	}

	
	@Bean
	public AuthService authService() {
		return new AuthService();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
