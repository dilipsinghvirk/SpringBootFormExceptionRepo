package com.formsub.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	public boolean validateUser(String userid, String password) {
		return userid.equalsIgnoreCase("dsv") && password.equalsIgnoreCase("dsv");
	}
}
