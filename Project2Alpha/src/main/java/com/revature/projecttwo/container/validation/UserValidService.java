package com.revature.projecttwo.container.validation;

import org.springframework.stereotype.Service;

@Service
public class UserValidService {

	public boolean checkPassword(String password) {
		return password != null;
	}
}
