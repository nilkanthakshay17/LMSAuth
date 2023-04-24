package com.cognizant.app.lms.auth.communication;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.app.lms.auth.model.UserRequestModel;
import com.cognizant.app.lms.auth.model.UserResponseModel;


@FeignClient(name = "lmsusers")
public interface UsersServiceFeignClient {

	@PostMapping("/user")
	public ResponseEntity<UserResponseModel> registerUser(@RequestBody UserRequestModel userRequest);

	@GetMapping("/user/{id}")
	public ResponseEntity<UserResponseModel> getUserById(@PathVariable(name = "id")String id );

	@GetMapping("/user/email/{email}")
	public ResponseEntity<UserResponseModel> getUserByEmailId(@PathVariable(name = "email")String email);

	@GetMapping("/user")
	public ResponseEntity<List<UserResponseModel>> getAllUsers();
}
