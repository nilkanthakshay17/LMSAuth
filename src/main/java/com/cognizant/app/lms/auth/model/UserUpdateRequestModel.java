package com.cognizant.app.lms.auth.model;

import com.cognizant.app.lms.auth.annotation.UserEmail;
import com.cognizant.app.lms.auth.annotation.UserPassword;

public class UserUpdateRequestModel {

	private String userName;
	
	@UserEmail
	private String userEmail;
	
	private boolean userAdmin;
	
	@UserPassword
	private String password;
	
	private String roles;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public boolean isUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(boolean userAdmin) {
		this.userAdmin = userAdmin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
}
