package com.cognizant.app.lms.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cognizant.app.lms.auth.model.UserResponseUserDetails;

@SpringBootTest
class AuthServiceTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JwtService jwtService;
	
	String token;
	
	@Test
	@Order(1)
	public void testGenerateToken() {
		String token = jwtService.generateToken("nilkanthakshay17@gmail.com");
		logger.info("Token: {}",token);
		assertNotNull(token);
	}
	
	@Test
	@Order(2)
	public void testValidateToken() {
		String token = jwtService.generateToken("nilkanthakshay17@gmail.com");
		
		UserResponseUserDetails userDetails = new UserResponseUserDetails();
		userDetails.setUserEmail("nilkanthakshay17@gmail.com");
		boolean isTokenValid = jwtService.validateToken(token,userDetails);
		
		assertTrue(isTokenValid);
	}
}
