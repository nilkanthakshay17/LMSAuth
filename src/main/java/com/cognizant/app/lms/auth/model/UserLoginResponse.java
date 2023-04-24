package com.cognizant.app.lms.auth.model;

import java.util.Arrays;

public class UserLoginResponse {

	private String userId;
	
	private String userEmail;
		
	private String[] roles;
	
	private String token;
	
	public UserLoginResponse(UserResponseUserDetails response) {
		this.userId = response.getUserId();
		this.userEmail = response.getUsername();
		this.roles = response.getRoles().split(",");
	}
	
	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserLoginResponse [userId=" + userId + ", userEmail=" + userEmail + ", roles=" + Arrays.toString(roles)
				+ ", token=" + token + "]";
	}

	
}
