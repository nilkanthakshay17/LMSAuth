package com.cognizant.app.lms.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import org.springframework.core.annotation.Order;

import com.cognizant.app.lms.auth.validation.UserFieldNotNullValidator;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserFieldNotNullValidator.class)
@Order(1)
public @interface UserFieldNotNull {

	public String message() default "Field can not be null or empty";
	
	Class<?>[] groups() default{};

	Class<? extends Payload>[] payload() default {}; 
}
