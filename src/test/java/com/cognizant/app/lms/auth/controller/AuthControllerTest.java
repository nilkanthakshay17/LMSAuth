package com.cognizant.app.lms.auth.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.app.lms.auth.communication.UsersServiceFeignClient;
import com.cognizant.app.lms.auth.model.UserLoginRequest;
import com.cognizant.app.lms.auth.model.UserLoginResponse;
import com.cognizant.app.lms.auth.model.UserResponseModel;
import com.cognizant.app.lms.auth.model.UserResponseUserDetails;
import com.cognizant.app.lms.auth.service.AuthService;
import com.cognizant.app.lms.auth.service.JwtService;
import com.cognizant.app.lms.auth.service.UsersCommunicationService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	JwtService jwtService;
	
	@MockBean
	AuthService authService;

	@MockBean
	private AuthenticationManager authenticationManager;
	
	@MockBean
	UsersCommunicationService usersCommunicationService;
	

	@Autowired
	ObjectMapper objectMapper;
	
	UserResponseModel userResponseModel;
	UserLoginRequest userLoginRequest;
	private  UserResponseUserDetails dummy;
	
	@BeforeEach
	public void setUp() {
		userResponseModel = new UserResponseModel(
				"9999",
				"Akshay Nilkanth",
				"nilkanthakshay17@gmail.com",
				true,
				"encryptedpass",
				"ROLE_USER,ROLE_ADMIN"
				);
		
		
		userLoginRequest = new UserLoginRequest();
		userLoginRequest.setUserEmail("nilkanthakshay17@gmail.com");
		userLoginRequest.setPassword("Shadyaxee@123");
		
		dummy = new UserResponseUserDetails();
		dummy.setUserEmail("nilkanthakshay17@gmail.com");
		dummy.setUserId("999");
		dummy.setRoles("ROLE_USER,ROLE_ADMIN");
	}
	
	@Test
	public void testGetUserByUserId() throws Exception{
		
		String getUserByUserIdURI = "/auth/user/9999";
		
		ResponseEntity<UserResponseModel> respEntity = ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
		
		when(usersCommunicationService.getUserById(eq("9999"))).thenReturn(respEntity);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(getUserByUserIdURI)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		
		UserResponseModel responseResult = mapFromJson(mvcResult.getResponse().getContentAsString(), UserResponseModel.class);
		
		assertNotNull(responseResult);	
		
	}
	
	@Test
	public void testUseLogin() throws Exception{
		String loginURI = "/auth/user/login";
		String inputJson = mapToJson(userLoginRequest);

		Authentication authentication = mock(Authentication.class);
		authentication.setAuthenticated(true);
		when(authentication.isAuthenticated()).thenReturn(true);

		when(authenticationManager.authenticate(any())).thenReturn(authentication); // Failing here
		when(authentication.getPrincipal()).thenReturn(dummy);
		when(authService.generateToken(any())).thenReturn("JWT");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(loginURI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson))
				.andReturn();
		
		String loginResponse = mvcResult.getResponse().getContentAsString();
		assertNotNull(loginResponse);
		logger.info("Login Response: {}",loginResponse);
	}
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

}
