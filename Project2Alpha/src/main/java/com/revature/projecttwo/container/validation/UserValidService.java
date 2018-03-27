package com.revature.projecttwo.container.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.service.UserService;

@Service
public class UserValidService {

	@Autowired
	private UserService userService;

	public boolean checkEmailUnique(String email) {
		if (userService.getUser(email) == null)
			return true;
		return false;
	}

	/**
	 * Checks user isn't null, has non-empty email and password attributes
	 * 
	 * @param user
	 * @return
	 */
	public boolean checkUserHasEmailPassword(Resident user) {
		return user != null && checkUserHasEmail(user) && checkUserHasPassword(user);
	}

	public boolean checkUserHasEmail(Resident user) {
		return user != null && user.getEmail() != null && !user.getEmail().isEmpty();
	}

	public boolean checkUserHasPassword(Resident user) {
		return user != null && user.getPassword() != null && !user.getPassword().isEmpty();
	}

}
