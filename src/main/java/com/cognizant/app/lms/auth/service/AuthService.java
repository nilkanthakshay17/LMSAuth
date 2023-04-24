package com.cognizant.app.lms.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private JwtService jwtService;
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}
	
	public boolean validateToken(String token) {
		return jwtService.validateToken(token, null);
	}
}
