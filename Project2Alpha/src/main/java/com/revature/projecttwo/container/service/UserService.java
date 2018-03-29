package com.revature.projecttwo.container.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.projecttwo.container.beans.Resident;
import com.revature.projecttwo.container.dtos.UserDto;
import com.revature.projecttwo.container.repo.UserRepo;
import com.revature.projecttwo.container.validation.UserValidService;
import com.revature.projecttwo.email.EmailServiceImpl;

@Service
public class UserService {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserValidService userValidation;
	@Autowired
	private EmailServiceImpl emailService;
	@Autowired
	private PasswordService passwordService;

	public List<UserDto> getUsers(String firstName, String lastName) {
		logger.info("Getting all users matching:\n\t" + firstName + " " + lastName);
		List<UserDto> usersDtos = new ArrayList<>();
		// method reference add method call
		List<Resident> users = userRepo.findByFirstNameAndLastNameIgnoreCase(firstName, lastName);

		// turn into DTOs to restrict info returned
		for (Resident u : users) {
			usersDtos.add(new UserDto(u.getId(), u.getFirstName(), u.getLastName()));
		}
		return usersDtos;
	}

	public List<UserDto> getUsersDtosMatching(String name) {
		logger.info("Getting all users matching name:\n\t" + name);
		List<UserDto> usersDtos = new ArrayList<>();

		// regex it
		name = '%' + name + '%';

		List<Resident> users = userRepo.findByFirstNameLikeOrLastNameLikeIgnoreCase(name, name);

		// turn into DTOs to restrict info returned
		for (Resident u : users) {
			usersDtos.add(new UserDto(u.getId(), u.getFirstName(), u.getLastName()));
		}

		return usersDtos;
	}

	public Resident getUser(Integer id) {
		logger.info("Found User in DB:\n\t" + id);
		Optional<Resident> user = userRepo.findById(id);

		Resident userObj = user.get();
		userObj.setPassword("");

		return userObj;
	}

	public Resident getUser(String email, String password) {
		logger.info("Found User in DB:\n\t" + email + " " + password);

		Resident user = userRepo.getByEmail(email);

		// check password encypt match
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			logger.info("Password matches email");

			// remove password field
			user.setPassword("");

			return user;
		} else {
			logger.error("Password does not match email");
			return null;
		}
	}

	/**
	 * Gets user by email
	 * 
	 * @param email
	 * @return
	 */
	public Resident getUser(String email) {
		// 1. check if email is empty
		if (email == null || email.isEmpty()) {
			logger.error("Email is Empty:\n\t" + email);
			return null;
		}

		Resident user = userRepo.getByEmail(email);

		logger.info("Found User in DB:\n\t" + email);

		return user;

	}

	/**
	 * Saves a new user account to database
	 * 
	 * @param user
	 * @return
	 */
	public boolean registerNewUserAccount(Resident user) {
		// 1. check user obj exists and has required fields
		if (!userValidation.checkUserHasEmailPassword(user)) {
			logger.error("User field(s) empty\n\t" + user.getEmail());
			return false;
		}

		// 2. check email unique
		if (!userValidation.checkEmailUnique(user.getEmail())) {
			logger.warn("Email already taken\n\t" + user.getEmail());
			return false;
		}

		logger.info("Saving User to DB:\n\t" + user);

		// encode the user password before storing it into db
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		logger.debug("Password Encryption = " + user.getPassword());

		return userRepo.save(user) != null;

	}

	/**
	 * Updates any information passed for user
	 * 
	 * @param user
	 */
	public Resident updateUser(Resident user, int id) {

		// 1. check user with ID exists
		Resident userFound = userRepo.getById(id);

		if (userFound == null) {
			logger.error("No user with id exists:\n\t" + id);
			return null;
		}
		// 1.5 check if there is anything to update
		if (user == null) {
			logger.info("No fields to update:\n\t" + user);
			userFound.setPassword("");
			return userFound;
		}

		logger.info("Updating User\n\t" + user + "\nin DB with:\n\t" + userFound);

		// 2. Set any changed fields in user db obj
		// first, last, dob, gender, phone
		if (user.getFirstName() != null) {
			userFound.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			userFound.setLastName(user.getLastName());
		}
		if (user.getDob() != null) {
			userFound.setDob(user.getDob());
		}
		if (user.getGender() != null) {
			userFound.setGender(user.getGender());
		}
		if (user.getPhoneNumber() != null) {
			userFound.setPhoneNumber(user.getPhoneNumber());
		}

		userFound = userRepo.save(userFound);

		userFound.setPassword("");
		return userFound;
	}

	public boolean updateUserPassword(Resident user) {
		// 1. check if password exists
		if (!userValidation.checkUserHasPassword(user)) {
			logger.error("No User Password:\n\t" + user);
			return false;
		}
		// 2. Encrypt password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// 3. Update Password
		return userRepo.save(user) != null;
	}

	public boolean resetPassword(Resident user) {
		if (!userValidation.checkUserHasEmail(user)) {
			logger.error("No User email\n\t" + user);
			return false;
		}
		logger.info("Resetting user password\n\t" + user);

		// send email to reset
		String newPass = passwordService.generatePassword();
		emailService.sendReset(user.getEmail(), newPass);

		// set user object to new password
		user.setPassword(newPass);

		return updateUserPassword(user);

	}

	public boolean changePassword(Resident user) {

		if (!userValidation.checkUserHasEmail(user)) {
			logger.error("No User email\n\t" + user);
			return false;
		}
		Resident userFound = userRepo.getByEmail(user.getEmail());
		logger.info("Changing user password\n\t" + user.getPassword());
		userFound.setPassword(user.getPassword());

		return updateUserPassword(userFound);

	}

	public void deleteUser(Integer id) {
		logger.info("Deleting User to DB:+\n\t" + id);
		userRepo.deleteById(id);
	}

	public void updateUserImage(String path, int userId) {
		logger.info("Updating user picture in DB:+\n\t" + userId + " " + path);
		Resident user = userRepo.getById(userId);
		user.setProfileUrl(path);
		userRepo.save(user);
	}
}
