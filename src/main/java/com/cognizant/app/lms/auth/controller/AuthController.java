package com.cognizant.app.lms.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.lms.auth.model.UserLoginRequest;
import com.cognizant.app.lms.auth.model.UserLoginResponse;
import com.cognizant.app.lms.auth.model.UserRequestModel;
import com.cognizant.app.lms.auth.model.UserResponseModel;
import com.cognizant.app.lms.auth.model.UserResponseUserDetails;
import com.cognizant.app.lms.auth.service.AuthService;
import com.cognizant.app.lms.auth.service.UsersCommunicationService;



@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	private UsersCommunicationService usersCommunicationService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/status")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String getStatus() {
		return "Working...";
	}
	
	@PostMapping("/user/register")
	public ResponseEntity<UserResponseModel> registerUser(@RequestBody UserRequestModel userRequest) {
		return usersCommunicationService.registerUser(userRequest);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<UserResponseModel>> getAllUsers() {
		return usersCommunicationService.getAllUsers();
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserResponseModel> getUserById(@PathVariable(name = "userId")String userId) {
		return usersCommunicationService.getUserById(userId);
	}
	
	@GetMapping("/user/email/{email}")
	public ResponseEntity<UserResponseModel> getUserByEmail(@PathVariable(name = "email")String email) {
		return usersCommunicationService.getuserByEmail(email);
	}
	
	
	@PostMapping("/user/login")
	public ResponseEntity<UserLoginResponse> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
		
		Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUserEmail(), userLoginRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			UserResponseUserDetails userDetails = (UserResponseUserDetails) authentication.getPrincipal();
			UserLoginResponse userLoginResponse = new UserLoginResponse(userDetails);
			String token= authService.generateToken(authentication.getName());
			userLoginResponse.setToken(token);
			System.out.println(userLoginResponse);
			
			return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
		}
		else {
			throw new UsernameNotFoundException("User Not Valid");
		}
	}
	
	@GetMapping("/user/validateToken")
	public String validateToken(@RequestParam(name = "token")String token) {
		if(authService.validateToken(token))
			return "Token Validated";
		else
			return "Token Invalid";
	}
}
