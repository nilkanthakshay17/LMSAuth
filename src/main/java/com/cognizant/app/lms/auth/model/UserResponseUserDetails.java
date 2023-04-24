package com.cognizant.app.lms.auth.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserResponseUserDetails implements UserDetails {

	private String userId;
	
	private String userName;
	
	private String userEmail;

	private boolean userAdmin;
	
	private String encryptedPassword;
	
	private String roles;
	
	private List<GrantedAuthority> authorities;
	
	public UserResponseUserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public UserResponseUserDetails(UserResponseModel userResponseModel){
		this.userId = userResponseModel.getUserId();
		this.userName = userResponseModel.getUserName();
		this.userEmail = userResponseModel.getUserEmail();
		this.userAdmin = userResponseModel.isUserAdmin();
		this.roles = userResponseModel.getRoles();
		this.encryptedPassword = userResponseModel.getEncryptedPassword();
		this.authorities = Arrays.stream(userResponseModel.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.encryptedPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
