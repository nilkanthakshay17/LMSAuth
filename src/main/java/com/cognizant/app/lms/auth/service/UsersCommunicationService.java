package com.cognizant.app.lms.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.app.lms.auth.communication.UsersServiceFeignClient;
import com.cognizant.app.lms.auth.model.UserRequestModel;
import com.cognizant.app.lms.auth.model.UserResponseModel;
import com.cognizant.app.lms.auth.model.UserResponseUserDetails;

@Service
public class UsersCommunicationService implements UserDetailsService{

	@Autowired
	private UsersServiceFeignClient usersServiceFeignClient;
	
	public ResponseEntity<UserResponseModel> registerUser(UserRequestModel userRequest){
		return usersServiceFeignClient.registerUser(userRequest);
	}
	
	public ResponseEntity<List<UserResponseModel>> getAllUsers(){
		return usersServiceFeignClient.getAllUsers();
	}
	
	public ResponseEntity<UserResponseModel> getUserById(String userId){
		return usersServiceFeignClient.getUserById(userId);
	}
	
	public ResponseEntity<UserResponseModel> getuserByEmail(String email){
		return usersServiceFeignClient.getUserByEmailId(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResponseEntity<UserResponseModel> userResponse= usersServiceFeignClient.getUserByEmailId(username);
		System.out.println("Inside loadUserByUsername");
		System.out.println(userResponse.getBody().getUserEmail());
		System.out.println(userResponse.getBody().getRoles());
		
		UserResponseModel userResponseModel = userResponse.getBody();
		UserResponseUserDetails userDetails = new UserResponseUserDetails(userResponseModel);
		
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.getAuthorities());
		System.out.println(userDetails.getPassword());
		if(userDetails!=null) {
			return userDetails;
		}
		else{
			throw new UsernameNotFoundException("User Not Found");
		}
	}
	
}
