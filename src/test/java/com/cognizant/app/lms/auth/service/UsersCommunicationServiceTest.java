package com.cognizant.app.lms.auth.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.app.lms.auth.communication.UsersServiceFeignClient;
import com.cognizant.app.lms.auth.model.UserRequestModel;
import com.cognizant.app.lms.auth.model.UserResponseModel;

@SpringBootTest
class UsersCommunicationServiceTest {


	@MockBean
	UsersServiceFeignClient usersServiceFeignClient;
	
	@Autowired
	UsersCommunicationService usersCommunicationService;
	
	UserRequestModel userRequestModel;
	UserResponseModel userResponseModel;
	
	@BeforeEach
	public void setUp() {
	
		userRequestModel = new UserRequestModel(
				"Akshay Nilkanth",
				"nilkanthakshay17@gmail.com",
				"akshay@123",
				true,
				"ROLE_USER,ROLE_ADMIN"
				);
		
		userResponseModel = new UserResponseModel(
				"9999",
				"Akshay Nilkanth",
				"nilkanthakshay17@gmail.com",
				true,
				"encryptedpass",
				"ROLE_USER,ROLE_ADMIN"
				);
	
	}
	
	
	
	@Test
	public void testGetUserByUserId(){
		ResponseEntity<UserResponseModel> respEntity = ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
		
		when(usersServiceFeignClient.getUserById(eq("9999"))).thenReturn(respEntity);
		
		ResponseEntity<UserResponseModel> receivedRespEntity = usersCommunicationService.getUserById("9999");
		
		assertEquals(respEntity.getBody().getUserId(), receivedRespEntity.getBody().getUserId());
		assertEquals(respEntity.getBody().getUserName(), receivedRespEntity.getBody().getUserName());
		assertEquals(respEntity.getBody().getUserEmail(), receivedRespEntity.getBody().getUserEmail());
		assertEquals(respEntity.getBody().getRoles(), receivedRespEntity.getBody().getRoles());
	}
	
	@Test
	public void testGetAllUsers(){
		List<UserResponseModel> allUsers = new ArrayList<>();
		allUsers.add(userResponseModel);
		allUsers.add(userResponseModel);
		ResponseEntity<List<UserResponseModel>> respEntity = ResponseEntity.status(HttpStatus.OK).body(allUsers);
		
		when(usersServiceFeignClient.getAllUsers()).thenReturn(respEntity);
		
		ResponseEntity<List<UserResponseModel>> receivedRespEntity = usersCommunicationService.getAllUsers();
		
		assertNotNull(receivedRespEntity);
	}
	


	@Test
	public void testGetUserByEmail(){
		ResponseEntity<UserResponseModel> respEntity = ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
		
		when(usersServiceFeignClient.getUserByEmailId(eq("nilkanthakshay17@gmail.com"))).thenReturn(respEntity);
		
		ResponseEntity<UserResponseModel> receivedRespEntity = usersCommunicationService.getuserByEmail("nilkanthakshay17@gmail.com");
		
		assertEquals(respEntity.getBody().getUserId(), receivedRespEntity.getBody().getUserId());
		assertEquals(respEntity.getBody().getUserName(), receivedRespEntity.getBody().getUserName());
		assertEquals(respEntity.getBody().getUserEmail(), receivedRespEntity.getBody().getUserEmail());
		assertEquals(respEntity.getBody().getRoles(), receivedRespEntity.getBody().getRoles());
	}
	
	
	@Test
	public void testLoaduserBuUserName(){
		ResponseEntity<UserResponseModel> respEntity = ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
		
		when(usersServiceFeignClient.getUserByEmailId(eq("nilkanthakshay17@gmail.com"))).thenReturn(respEntity);
		
		UserDetails receivedUserDetails = usersCommunicationService.loadUserByUsername("nilkanthakshay17@gmail.com");
		
		assertEquals(receivedUserDetails.getUsername(), respEntity.getBody().getUserEmail());
	}
	

}
