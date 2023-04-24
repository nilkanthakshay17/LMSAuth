package com.cognizant.app.lms.auth.model;

import com.cognizant.app.lms.auth.annotation.UserEmail;
import com.cognizant.app.lms.auth.annotation.UserFieldNotNull;
import com.cognizant.app.lms.auth.annotation.UserPassword;

public class UserLoginRequest {

	@UserFieldNotNull
	@UserEmail
	private String userEmail;
	
	@UserFieldNotNull
	@UserPassword
	private String password;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
