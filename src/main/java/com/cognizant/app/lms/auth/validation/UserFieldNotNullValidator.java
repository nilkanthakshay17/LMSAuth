package com.cognizant.app.lms.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import com.cognizant.app.lms.auth.annotation.UserFieldNotNull;

@Component
public class UserFieldNotNullValidator implements ConstraintValidator<UserFieldNotNull	, String>{
	
	@Override
	public boolean isValid(String fieldValue, ConstraintValidatorContext context) {

		if(null == fieldValue || fieldValue == "")
			return false;
		return true;
	}

	@Override
	public void initialize(UserFieldNotNull constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}
}
