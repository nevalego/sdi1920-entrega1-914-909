package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

public class LoginValidator implements Validator {

	@Autowired
	private UsersService usersService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.login.email.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.login.password.empty");
		User testUser = usersService.getUserByEmail(user.getEmail());
		if (testUser != null) {
			if(!testUser.getPassword().equals(user.getPassword())) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.login.password.notMatch");
			}
		}else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.login.email.notExist");
		}
		
	}

}
