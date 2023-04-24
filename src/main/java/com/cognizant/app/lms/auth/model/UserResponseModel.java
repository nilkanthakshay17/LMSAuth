package com.cognizant.app.lms.auth.model;

public class UserResponseModel {

	private String userId;
	
	private String userName;
	
	private String userEmail;

	private boolean userAdmin;
	
	private String encryptedPassword;
	
	private String roles;
	
	public UserResponseModel() {
		// TODO Auto-generated constructor stub
	}
	
	public UserResponseModel(String userId, String userName, String userEmail, boolean userAdmin,
			String encryptedPassword, String roles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userAdmin = userAdmin;
		this.encryptedPassword = encryptedPassword;
		this.roles = roles;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
