package com.cognizant.app.lms.auth.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.cognizant.app.lms.auth.annotation.UserPassword;

@Component
public class UserPasswordValidator implements ConstraintValidator<UserPassword, String> {

	@Override
	public boolean isValid(String passwordValue, ConstraintValidatorContext context) {
		if (null == passwordValue || "" == passwordValue) 
			return false;
		else {
			Pattern passwordPattern = Pattern.compile("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!&]).{8,50})");

			Matcher passwordMatcher = passwordPattern.matcher(passwordValue);

			return passwordMatcher.matches();
		}
	}

	@Override
	public void initialize(UserPassword constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

}
