package com.cognizant.app.lms.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import org.springframework.core.annotation.Order;

import com.cognizant.app.lms.auth.validation.UserPasswordValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER })
@Constraint(validatedBy = {UserPasswordValidator.class})
@Order(2)
public @interface UserPassword {

	public String message() default "Password should be minimum 8 characters, should containt atleast 1 digit, 1 uppercase letter and 1 special character";
	
	Class<?>[] groups() default{};

	Class<? extends Payload>[] payload() default {}; 
}
